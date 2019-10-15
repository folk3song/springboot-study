package com.springboot.chapter12.service.impl;

import com.springboot.chapter12.dao.SecurityDao;
import com.springboot.chapter12.pojo.DatabaseRole;
import com.springboot.chapter12.pojo.DatabaseUser;
import com.springboot.chapter12.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private SecurityDao securityDao = null;

    @Transactional
    @Cacheable(value="redisCache",key = "'redis_user_'+#userName")
    @Override
    public DatabaseUser getUserByName(String userName) {
        return securityDao.getUserByName(userName);
    }

    @Transactional
    @Override
    public List<DatabaseRole> findRolesByUserName(String userName) {
        return securityDao.findRolesByUserName(userName);
    }
}
