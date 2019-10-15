package com.springboot.chapter4.service1;

import com.springboot.chapter4.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImple {
    public void prinUser(User user)
    {
        if(user == null)
        {
            throw  new RuntimeException("参数不能为空");
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getNote());
    }
}
