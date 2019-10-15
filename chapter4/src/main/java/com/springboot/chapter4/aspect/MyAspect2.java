package com.springboot.chapter4.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class MyAspect2 {
    @Pointcut("execution(* com.springboot.chapter4.service.impl.UserServiceImpl.manyAspects(..))")
    public void manyAspects()
    {

    }

    @Before("manyAspects()")
    public void before()
    {
        System.out.println("Myaspect2 before ...........");
    }

    @After("manyAspects()")
    public void after()
    {
        System.out.println("MyAspect2 after ..........");
    }

    @AfterReturning("manyAspects()")
    public void afterReturning()
    {
        System.out.println("MyAspect2 afterReturning");
    }
}
