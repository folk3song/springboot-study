package com.springboot.chapter8.repository.impl;

import com.springboot.chapter8.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public User findUserByIdOrUserName(Long id,String userName)
    {
        Criteria criteriaId = Criteria.where("id").is(id);
        Criteria criteriaUserName = Criteria.where("userName").is(userName);
        Criteria criteria = new Criteria();

        criteria.orOperator(criteriaId,criteriaUserName);
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query,User.class);
    }
}
