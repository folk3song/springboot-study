package com.springboot.chapter171;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Chapter171Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter171Application.class, args);
    }

}
