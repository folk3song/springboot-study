//package com.springboot.chapter6.config;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    @Bean(name="mydatasource")
//    @Qualifier("mydatasource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource getDataSource()
//    {
//         return DataSourceBuilder.create().build();
//    }
//}
