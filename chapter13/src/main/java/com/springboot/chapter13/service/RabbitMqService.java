package com.springboot.chapter13.service;

import com.springboot.chapter13.pojo.User;

public interface RabbitMqService {
    public void sendMsg(String msg);
    public void sendUser(User user);
}
