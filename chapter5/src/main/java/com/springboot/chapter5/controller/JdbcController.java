package com.springboot.chapter5.controller;

import com.springboot.chapter5.enumeration.SexEnum;
import com.springboot.chapter5.pojo.User;
import com.springboot.chapter5.service.impl.JdbcTmplUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JdbcController {
    @Autowired
    JdbcTmplUserServiceImpl jdbcTmplUserService;

    @RequestMapping("/jdbc")
    @ResponseBody
    public String insert()
    {
        User user = new User();
        user.setNote("abc");
        SexEnum sex = SexEnum.getEnumById(1);
        user.setSex(sex);
        user.setUserName("mike");
        jdbcTmplUserService.insertUser(user);
        return "ok";
    }

    @RequestMapping("/query")
    @ResponseBody
    public User query()
    {
        User user = jdbcTmplUserService.getUser(1L);
        return user;


    }
}
