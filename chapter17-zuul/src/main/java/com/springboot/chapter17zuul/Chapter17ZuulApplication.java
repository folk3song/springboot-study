package com.springboot.chapter17zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Chapter17ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter17ZuulApplication.class, args);
    }

}
