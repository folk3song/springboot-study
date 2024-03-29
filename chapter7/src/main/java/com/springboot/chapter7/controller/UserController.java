package com.springboot.chapter7.controller;


import com.springboot.chapter7.UserService.UserService;
import com.springboot.chapter7.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id)
    {
        return userService.getUser(id);
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public User insertUser(String userName,String note)
    {
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        userService.insertUser(user);
        return user;
    }

    @RequestMapping("/findUsers")
    @ResponseBody
    public List<User> findUsers(String userName,String note)
    {
        return userService.findUsers(userName,note);
    }

    @RequestMapping("/updateUserName")
    @ResponseBody
    public Map<String,Object> updateUserName(Long id,String userName)
    {
        User user = userService.updateUserName(id,userName);
        boolean flag = user != null;
        String message = flag ? "跟新成功":"跟新失败";

        return resultMap(flag,message);
    }


    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String,Object> deleteUser(Long id)
    {
        int result = userService.deleteUser(id);
        boolean flag = result == 1;
        String message = flag?"删除成功":"删除失败";
        return resultMap(flag,message);
    }

    private  Map<String,Object> resultMap(boolean success,String message)
    {
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("success",success);
        result.put("message",message);
        return  result;
    }




}
