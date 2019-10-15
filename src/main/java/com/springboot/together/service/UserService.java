package com.springboot.together.service;

public interface UserService {
    default void printUser()
    {
        System.out.println("hello user");
    }
}
