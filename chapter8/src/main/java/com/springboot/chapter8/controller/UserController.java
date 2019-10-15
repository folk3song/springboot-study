package com.springboot.chapter8.controller;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.springboot.chapter8.pojo.User;
import com.springboot.chapter8.repository.UserRepository;
import com.springboot.chapter8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/page")
    public String page()
    {
        return "user";
    }

    @RequestMapping("/save")
    @ResponseBody
    public User saveUser(@RequestBody User user)
    {
        userService.saveUser(user);
        System.out.println("调用保存对象");
        return user;
    }

    @RequestMapping("/get")
    @ResponseBody
    public User getUser(Long id)
    {
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping("/find")
    @ResponseBody
    public List<User> addUser(String userName,String note,Integer skip,Integer limit)
    {
        List<User> userList = userService.findUser(userName,note,skip,limit);
        return userList;
    }

    @RequestMapping("/update")
    @ResponseBody
    public UpdateResult updateResult(Long id,String userName,String note)
    {
        return userService.updateUser(id,userName,note);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DeleteResult deleteResult(Long id)
    {
        return userService.deleteUser(id);
    }

    @RequestMapping("/byName")
    @ResponseBody
    public List<User> findByName(String userName)
    {
        return userRepository.findByUserNameLike(userName);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User get(@PathVariable("id") Long id)
    {
        return userService.getUser(id);
    }





}
