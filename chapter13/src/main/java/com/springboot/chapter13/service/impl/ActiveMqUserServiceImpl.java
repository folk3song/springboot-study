package com.springboot.chapter13.service.impl;

import com.springboot.chapter13.pojo.User;
import com.springboot.chapter13.service.ActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqUserServiceImpl implements ActiveUserService {
    @Autowired
    private JmsTemplate jmsTemplate = null;

    private static final String myDestination = "my-destination";
    @Override
    public void sendUser(User user) {
        System.out.println("发送消息"+user);
        jmsTemplate.convertAndSend(myDestination,user);
    }

    @Override
    @JmsListener(destination = myDestination)
    public void receiveUser(User user) {
        System.out.println("接收到消息"+user);
    }
}
