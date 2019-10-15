package com.springboot.chapter6.controller;

import com.springboot.chapter6.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class DataSourceController {

    @Autowired
    JdbcService jdbcService = null;

    @RequestMapping("/datasource")
    public String datasource()
    {
        int i = jdbcService.inserUser("jane","aaa");
        System.out.println(i);
        return "ok";

    }

}
