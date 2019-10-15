package com.springboot.chapter8.repository;

import com.springboot.chapter8.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {

    List<User> findByUserNameLike(String userName);
}
