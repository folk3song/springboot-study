package com.springboot.chapter14.repository;

import com.springboot.chapter14.pojo.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User,Long> {
     Flux<User> findByUserNameLikeAndNoteLike(String userName,String note);
}
