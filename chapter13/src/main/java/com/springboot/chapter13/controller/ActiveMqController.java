package com.springboot.chapter13.controller;

import com.springboot.chapter13.pojo.User;
import com.springboot.chapter13.service.ActiveMqService;
import com.springboot.chapter13.service.ActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/activemq")
public class ActiveMqController {
    @Autowired
    private ActiveUserService activeUserService;

    @Autowired
    private ActiveMqService activeMqService;

    @ResponseBody
    @GetMapping("/msg")
    public Map<String,Object> msg(String message)
    {
        activeMqService.sendMsg(message);
        return result(true,message);
    }

    @ResponseBody
    @GetMapping("/user")
    public Map<String,Object> sendUser(Long id,String userName,String note)
    {
        User user = new User(id,userName,note);
        activeUserService.sendUser(user);
        return result(true,user);
    }

    private Map<String,Object> result(Boolean success,Object message)
    {
        Map<String,Object> result = new HashMap<>();
        result.put("success",success);
        result.put("message",message);
        return result;
    }
}
