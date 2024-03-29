package com.springboot.chapter13.controller;

import com.springboot.chapter13.pojo.User;
import com.springboot.chapter13.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {
    @Autowired
    private RabbitMqService rabbitMqService = null;

    @GetMapping("/msg")
    public Map<String,Object> msg(String message)
    {
        rabbitMqService.sendMsg(message);
        return  resultMap("message",message);
    }

    @GetMapping("/user")
    public Map<String,Object> user(Long id,String userName,String note)
    {
        User user = new User(id,userName,note);
        rabbitMqService.sendUser(user);
        return resultMap("user",user);
    }

    private Map<String,Object> resultMap(String key,Object obj)
    {
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put(key,obj);
        return result;
    }
}
