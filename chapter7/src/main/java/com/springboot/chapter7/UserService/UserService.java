package com.springboot.chapter7.UserService;

import com.springboot.chapter7.pojo.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    User insertUser(User user);
    User updateUserName(Long id,String userName);

    List<User> findUsers(String userName,String note);
    int deleteUser(Long id);
}
