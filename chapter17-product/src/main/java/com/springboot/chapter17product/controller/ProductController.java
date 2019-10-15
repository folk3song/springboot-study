package com.springboot.chapter17product.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springboot.chapter17product.service.UserService;
import com.springboot.chapter17user.pojo.UserPo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private RestTemplate restTemplate = null;

    @Autowired
    private UserService userService = null;

    @GetMapping("/ribbon")
    public UserPo testRibbon()
    {
        UserPo user = null;
        for (int i = 0;i<10;i++)
        {
            user = restTemplate.getForObject("http://USER/user/"+(i+1),UserPo.class);
        }
        return  user;
    }

    @GetMapping("/feign")
    public UserPo testFeign()
    {
        UserPo user = null;
        for(int i = 0;i <10 ; i++)
        {
            Long id = (long)(i+1);
            user = userService.getUser(id);
        }
        return  user;
    }

    @GetMapping("/feign2")
    public Map<String,Object> testFeign2()
    {
        Map<String,Object> result = null;
        UserPo user = null;

        for(int i = 1;i <10;i++)
        {
            Long id = (long) i;
            user = new UserPo();
            user.setId(id);
            int level = i % 3 + 1;
            user.setUserName("user_name_"+id);
            user.setLevel(level);
            user.setNote("note_"+i);
            result = userService.addUser(user);
        }
        return result;

    }

    @GetMapping("/feign3")
    public Map<String,Object> testFeign3()
    {
        Map<String,Object> result = null;
        for(int i = 0;i<10;i++)
        {
            Long id = (long)(i+1);
            String userName = "user_name_"+id;
            result = userService.updateName(userName,id);
        }
        return result;
    }

    @GetMapping("/circuitBreaker1")
    @HystrixCommand(fallbackMethod = "error",
    commandProperties = {
            @HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "3000"
            )
    })
    public String circuitBreaker1()
    {
        return restTemplate.getForObject("http://USER/timeout",String.class);
    }

    @GetMapping("/circuitBreaker2")
    @HystrixCommand(fallbackMethod = "error")
    public String circuitBreaker2()
    {
        return  userService.testTimeout();
    }

    public String error()
    {
        return "超时出错。";
    }





}
