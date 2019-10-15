package com.springboot.chapter4.intercept;

import org.aopalliance.intercept.Invocation;

import java.lang.reflect.InvocationTargetException;

public class MyInterceptor implements Interceptor {

    @Override
    public boolean before() {

        System.out.println("before.............");
        return true;
    }

    @Override
    public void after() {
        System.out.println("after.................");

    }

    @Override
    public Object around(com.springboot.chapter4.invoke.Invocation invocation) throws Throwable {
        System.out.println("around before .........");
        Object obj = invocation.proceed();
        System.out.println("around after..........");
        return obj;
    }


    @Override
    public void afterReturning() {
        System.out.println("after returning...........");

    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing..............");

    }

    @Override
    public boolean useAround() {
        return true;
    }
}
