package com.springboot.chapter12.service.impl;

import com.springboot.chapter12.pojo.DatabaseRole;
import com.springboot.chapter12.pojo.DatabaseUser;
import com.springboot.chapter12.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRoleService userRoleService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) {
        DatabaseUser dbUser = userRoleService.getUserByName(userName);
        List<DatabaseRole> roleList = userRoleService.findRolesByUserName(userName);

        return changeToUser(dbUser,roleList);
    }


    private  UserDetails changeToUser(DatabaseUser dbUser, List<DatabaseRole> roleList) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (DatabaseRole role:roleList)
        {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorityList.add(authority);
        }
        UserDetails userDetails = new User(dbUser.getUserName(),dbUser.getPwd(),authorityList);
        return userDetails;
    }
}
