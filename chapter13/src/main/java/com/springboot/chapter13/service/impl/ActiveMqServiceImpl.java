package com.springboot.chapter13.service.impl;

import com.springboot.chapter13.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqServiceImpl implements ActiveMqService {
    @Autowired
    private JmsTemplate jmsTemplate = null;
    @Override
    public void sendMsg(String message) {
        System.out.println("发送消息"+message);
        jmsTemplate.convertAndSend(message);
    }

    @Override
    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void receiveMsg(String message) {
        System.out.println("接收到的消息"+message);

    }
}
