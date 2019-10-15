package com.springboot.chapter13.receive;

import com.springboot.chapter13.pojo.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageReceiver {
    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiveMsg(String msg)
    {
        System.out.println("接收消息:"+msg);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.user}"})
    public void receiveUser(User user)
    {
        System.out.println("接收到用户信息"+user);
    }
}
