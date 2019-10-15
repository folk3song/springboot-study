package com.springboot.chapter13.service.impl;

import com.springboot.chapter13.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async
    public void generateReport() {
        System.out.println("报表线程名称："+Thread.currentThread().getName());

    }
}
