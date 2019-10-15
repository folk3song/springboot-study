package com.springboot.chapter5.controller;

import com.springboot.chapter5.dao.JpaUserRepository;
import com.springboot.chapter5.pojo.User;
import com.springboot.chapter5.pojo.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/jpa")
public class JpaController {
    @Autowired
    private JpaUserRepository jpaUserRepository = null;

    @RequestMapping("/getUser")
    @ResponseBody
    public User1 getUser(Long id)
    {
        User1 user = jpaUserRepository.findById(id).get();
        return user;
    }


    @RequestMapping("/getUserById")
    @ResponseBody
    public User1 getUserById(Long id)
    {
        User1 user = jpaUserRepository.getUserById(id);
        return user;
    }

    @RequestMapping("/findByUserNameLike")
    @ResponseBody
    public List<User1> findByUserNameLike(String userName)
    {
        List<User1> userList = jpaUserRepository.findByUserNameLike("%"+userName+"%");
        return userList;
    }

    @RequestMapping("/findByUserNameLikeOrNoteLike")
    @ResponseBody
    public  List<User1> findByUserNameLikeOrNoteLike(String userName,String note)
    {
        String userNameLike = "%"+userName+"%";
        String noteLike = "%"+note+"%";

        List<User1> userList = jpaUserRepository.findByUserNameLikeOrNoteLike(userNameLike,noteLike);
        return userList;
    }



}
