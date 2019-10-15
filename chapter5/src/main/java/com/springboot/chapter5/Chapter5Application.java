package com.springboot.chapter5;

import com.springboot.chapter5.dao.MyBatisUserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.springboot.chapter5.dao")
@EntityScan(basePackages = "com.springboot.chapter5.pojo")
@MapperScan(
        basePackages = "com.springboot.chapter5.*",
        sqlSessionFactoryRef = "sqlSessionFactory",
        sqlSessionTemplateRef = "sqlSessionTemplate",
        annotationClass = Repository.class
)
public class Chapter5Application {

//    @Autowired
//    SqlSessionFactory sqlSessionFactory = null;

//    @Bean
//    public MapperFactoryBean<MyBatisUserDao> initMyBatisUserDao()
//    {
//        MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<>();
//        bean.setMapperInterface(MyBatisUserDao.class);
//        bean.setSqlSessionFactory(sqlSessionFactory);
//        return bean;
//    }

//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer()
//    {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("com.springboot.chapter5.*");
//        mapperScannerConfigurer.setAnnotationClass(Repository.class);
//        return mapperScannerConfigurer;
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter5Application.class, args);
    }

}
