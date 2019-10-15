package com.springboot.chapter4.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Aspect
@Order(3)
public class MyAspect1 {
    @Pointcut("execution(* com.springboot.chapter4.service.impl.UserServiceImpl.manyAspects(..))")
    public void manyAspects()
    {

    }

    @Before("manyAspects()")
    public void before()
    {
        System.out.println("Myaspect1 before ...........");
    }

    @After("manyAspects()")
    public void after()
    {
        System.out.println("MyAspect1 after ..........");
    }

    @AfterReturning("manyAspects()")
    public void afterReturning()
    {
        System.out.println("MyAspect1 afterReturning");
    }
}
