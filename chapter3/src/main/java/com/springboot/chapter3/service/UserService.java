package com.springboot.chapter3.service;

import com.springboot.chapter3.condition.DatabaseConditional;
import com.springboot.chapter3.pojo.User;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Properties;

@Service
public class UserService {
    public void printUser(User user)
    {
        System.out.println("编号:"+user.getId());
        System.out.println("姓名："+user.getUserName());
        System.out.println("记录："+user.getNote());
    }

    @Bean(name = "dataSource")
    @Conditional(DatabaseConditional.class)
    public DataSource getDataSource(
            @Value("${database.driveName}") String driver,
            @Value("${database.url}") String url,
            @Value("${database.username}") String username,
            @Value("${database.password}") String password
            )
    {


        Properties props = new Properties();
        props.setProperty("driver",driver);
        props.setProperty("url",url);
        props.setProperty("username",username);
        props.setProperty("password",password);
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(props);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  dataSource;
    }

    @Bean(name="datasource")
    @Profile("dev")
    public DataSource getDevDataSource()
    {
        Properties props = new Properties();
        props.setProperty("driver","com.mysql.jdbc.Driver");
        props.setProperty("url","jdbc:mysql://localhost:3306/dev_spring_boot");
        props.setProperty("username","root");
        props.setProperty("password","123456");
        DataSource dataSource = null;
        try{
            dataSource = BasicDataSourceFactory.createDataSource(props);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataSource;

    }

    @Bean(name="datasource")
    @Profile("dev")
    public DataSource getTestDataSource()
    {
        Properties props = new Properties();
        props.setProperty("driver","com.mysql.jdbc.Driver");
        props.setProperty("url","jdbc:mysql://localhost:3306/test_spring_boot");
        props.setProperty("username","root");
        props.setProperty("password","123456");
        DataSource dataSource = null;
        try{
            dataSource = BasicDataSourceFactory.createDataSource(props);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataSource;

    }
}
