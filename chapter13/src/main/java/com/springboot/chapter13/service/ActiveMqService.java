package com.springboot.chapter13.service;

public interface ActiveMqService {
    public void sendMsg(String message);
    public void receiveMsg(String message);
}
