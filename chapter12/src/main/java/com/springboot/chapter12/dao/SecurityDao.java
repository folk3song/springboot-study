package com.springboot.chapter12.dao;

import com.springboot.chapter12.pojo.DatabaseRole;
import com.springboot.chapter12.pojo.DatabaseUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityDao {
     DatabaseUser getUserByName(String userName);
     List<DatabaseRole> findRolesByUserName(String userName);
}
