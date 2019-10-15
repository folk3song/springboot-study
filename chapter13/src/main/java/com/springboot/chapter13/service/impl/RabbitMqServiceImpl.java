package com.springboot.chapter13.service.impl;

import com.springboot.chapter13.pojo.User;
import com.springboot.chapter13.service.RabbitMqService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMqServiceImpl implements RabbitTemplate.ConfirmCallback, RabbitMqService {
    @Value("${rabbitmq.queue.msg}")
    private String msgRouting = null;

    @Value("${rabbitmq.queue.user}")
    private String userRouting = null;

    @Autowired
    private RabbitTemplate template;


    @Override
    public void sendMsg(String msg) {
        System.out.println("发送消息"+msg);
        template.setConfirmCallback(this);
        template.convertAndSend(msgRouting,msg);
    }

    @Override
    public void sendUser(User user) {
        System.out.println("发送用户消息"+user);
        template.setConfirmCallback(this);
        template.convertAndSend(userRouting,user);

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack)
        {
            System.out.println("消息成功消费");
        }else {
            System.out.println("消息消费失败"+cause);
        }
    }
}
