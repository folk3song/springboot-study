package com.springboot.chapter6.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ConfigurationProperties(prefix="spring.datasource")
public class ApplicationConfig {


    private String url;
    private String password;
    private String username;


    @Bean(name="myDataSource")
    public DataSource getDataSource()
    {
        Properties props = new Properties();
        props.setProperty("url",url);
        props.setProperty("username",username);
        props.setProperty("password",password);
        DataSource dataSource = null;
        try{
            dataSource = BasicDataSourceFactory.createDataSource(props);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return dataSource;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
