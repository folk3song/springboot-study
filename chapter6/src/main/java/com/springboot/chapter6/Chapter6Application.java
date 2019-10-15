package com.springboot.chapter6;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

@SpringBootApplication
@MapperScan(
        basePackages = "com.springboot.chapter6",
        annotationClass = Repository.class
)
public class Chapter6Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter6Application.class, args);
    }

//    @Autowired
//    PlatformTransactionManager transactionManager = null;
//
//    @PostConstruct
//    public void viewTransactionManager()
//    {
//        System.out.println(transactionManager.getClass().getName());
//    }

}
