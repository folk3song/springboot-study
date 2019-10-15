package com.springboot.chapter5.dao;


import com.springboot.chapter5.pojo1.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBatisUserDao {
    public User getUser(Long id);
}
