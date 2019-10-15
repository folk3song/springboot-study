package com.springboot.chapter6;

import com.springboot.chapter6.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter6ApplicationTests {
    @Resource
    DataSource dataSource;

    @Test
    public void contextLoads() {

        System.out.println("数据源是"+dataSource.getClass());
    }

}
