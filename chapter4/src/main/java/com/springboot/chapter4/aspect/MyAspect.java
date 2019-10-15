package com.springboot.chapter4.aspect;

import com.springboot.chapter4.pojo.User;
import com.springboot.chapter4.validator.UserValidator;
import com.springboot.chapter4.validator.impl.UserValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;



@Aspect

public class MyAspect {
    @DeclareParents(
            value = "com.springboot.chapter4.service.impl.UserServiceImpl",
            defaultImpl = UserValidatorImpl.class
    )
    public UserValidator userValidator;

    @Pointcut("execution(* com.springboot.chapter4.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut()
    {

    }
    @Before("pointCut() && args(user)" )
    public void before(JoinPoint point, User user)
    {
        Object[] args = point.getArgs();
        System.out.println("before ......");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp)throws Throwable
    {
        System.out.println("around before............");
        jp.proceed();
        System.out.println("around after...........");
    }
    @After("pointCut()")
    public void after()
    {
        System.out.println("after .......");
    }
    @AfterReturning("pointCut()")
    public void afterReturning()
    {
        System.out.println("afterReturning ......");
    }
    @AfterThrowing("pointCut()")
    public void afterThrowing()
    {
        System.out.println("afterThrowing ......");
    }
}
