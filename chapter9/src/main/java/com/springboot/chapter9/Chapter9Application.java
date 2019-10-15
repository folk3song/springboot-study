package com.springboot.chapter9;

import com.springboot.chapter9.intercaptor.Interceptor1;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan(basePackages = "com.springboot.chapter9.dao",annotationClass = Repository.class)
@EnableCaching
public class Chapter9Application implements WebMvcConfigurer {

    public static void main(String[] args) {

        SpringApplication.run(Chapter9Application.class, args);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new Interceptor1());
        ir.addPathPatterns("/interceptor/*");
    }

}
