package com.springboot.chapter4;

import com.springboot.chapter4.aspect.MyAspect;
import com.springboot.chapter4.aspect.MyAspect1;
import com.springboot.chapter4.aspect.MyAspect2;
import com.springboot.chapter4.aspect.MyAspect3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.springboot.chapter4.*"})
public class Chapter4Application {

    @Bean(name="myAspect1")
    public MyAspect1 initMyAspect1()
    {
        return new MyAspect1();
    }
    @Bean(name="myAspect2")
    public MyAspect2 initMyAspect2()
    {
        return new MyAspect2();
    }
    @Bean(name="myAspect3")
    public MyAspect3 initMyAspect3()
    {
        return new MyAspect3();
    }
    public static void main(String[] args) {
        SpringApplication.run(Chapter4Application.class, args);
    }

}
