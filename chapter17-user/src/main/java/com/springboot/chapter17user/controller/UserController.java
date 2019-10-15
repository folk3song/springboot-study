package com.springboot.chapter17user.controller;


import com.springboot.chapter17user.pojo.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@RestController
public class UserController {
    private Logger log = Logger.getLogger(this.getClass()+"");

    @Autowired
    private DiscoveryClient discoveryClient = null;

    @GetMapping("/user/{id}")
    public UserPo getUserPo(@PathVariable("id") Long id)
    {
        ServiceInstance service = discoveryClient.getInstances("USER").get(0);
        log.info("【"+service.getServiceId()+"】："+service.getHost()+":"+service.getPort());
        UserPo user = new UserPo();
        user.setId(id);
        int level = (int) (id%3+1);
        user.setLevel(level);
        user.setUserName("user_name_"+id);
        user.setNote("note_"+id);
        return user;
    }


    @PostMapping("/insert")
    public Map<String,Object> insertUser(@RequestBody UserPo user)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        map.put("message","插入用户信息【"+user.getUserName()+"】成功");
        return  map;
    }

    @PostMapping("/update/{userName}")
    public Map<String,Object> updateUsername(@PathVariable("userName") String userName,
                                             @RequestHeader("id") Long id )
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        map.put("message","跟新用户【"+id+"】名称【"+userName+"】成功");
        return map;
    }

    @GetMapping("/timeout")
    public String timeout()
    {
        long ms = (long) (3000L*Math.random());
        try{
            Thread.sleep(ms);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return "熔断测试";
    }


}
