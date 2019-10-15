package com.springboot.chapter13.service.impl;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws")
@Service
public class WebSocketServiceImpl {
    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet =
            new CopyOnWriteArraySet<>();


    private Session session;

    @OnOpen
    public void onOpen(Session session)
    {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新的连接加入，当前在线人数是"+getOnlineCount());
        try{
            sendMessage("有新连接加入！！！");
        }catch (IOException e)
        {
            System.out.println("IO异常");
        }
    }

    @OnClose
    public void onClose()
    {
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数是"+getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session)
    {
        System.out.println("来自客户端的消息"+message);
        for(WebSocketServiceImpl item:webSocketSet)
        {
            try{
//                String userName = item.getSession().getUserPrincipal().getName();
//                System.out.println(userName);
                item.sendMessage(message);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable error)
    {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    private void sendMessage(String message)throws  IOException
    {
        this.session.getBasicRemote().sendText(message);
    }

    private static synchronized  void addOnlineCount()
    {
        WebSocketServiceImpl.onlineCount++;
    }

    private static synchronized void subOnlineCount()
    {
        WebSocketServiceImpl.onlineCount--;
    }

    private static synchronized int getOnlineCount()
    {
        return onlineCount;
    }
}
