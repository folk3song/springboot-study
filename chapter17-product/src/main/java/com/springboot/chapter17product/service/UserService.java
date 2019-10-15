package com.springboot.chapter17product.service;

import com.springboot.chapter17user.pojo.UserPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("user")
public interface UserService {
    @GetMapping("/user/{id}")
    public UserPo getUser(@PathVariable("id") Long id);

    @PostMapping("/insert")
    public Map<String,Object> addUser(@RequestBody UserPo user);

    @PostMapping("/update/{userName}")
    public Map<String,Object> updateName(@PathVariable("userName") String userName,
                                         @RequestHeader("id") Long id);

    @GetMapping("/timeout")
    public String testTimeout();

}
