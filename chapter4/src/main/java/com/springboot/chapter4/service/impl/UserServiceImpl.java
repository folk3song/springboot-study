package com.springboot.chapter4.service.impl;

import com.springboot.chapter4.pojo.User;
import com.springboot.chapter4.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Override
    public void printUser(User user) {
        if(user == null)
        {
            throw new RuntimeException("检查用户参数是否为空");
        }
        System.out.println("id = "+user.getId());
        System.out.println("\tusername="+user.getUsername());
        System.out.println("\tnote="+user.getNote());
    }

    @Override
    public void manyAspects() {
        System.out.println("测试多个切面的顺序");
    }
}
