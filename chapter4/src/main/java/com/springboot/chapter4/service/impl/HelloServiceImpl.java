package com.springboot.chapter4.service.impl;

import com.springboot.chapter4.intercept.MyInterceptor;
import com.springboot.chapter4.pojo.ProxyBean;
import com.springboot.chapter4.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        if (name == null || name.trim() == "")
        {
            throw new RuntimeException("parameter is null");
        }
        System.out.println("hello"+name);
    }

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService,new MyInterceptor());
        proxy.sayHello("zhangsan");
        System.out.println("#############name is null @@@@@@@@@@@@@@@@@@@@@@");
        proxy.sayHello(null);
    }
}
