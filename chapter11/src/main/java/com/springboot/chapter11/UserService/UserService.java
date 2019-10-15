package com.springboot.chapter11.UserService;

import com.springboot.chapter11.pojo.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    User insertUser(User user);
    User updateUserName(Long id, String userName);

    List<User> findUsers(String userName, String note);
    int deleteUser(Long id);
}
