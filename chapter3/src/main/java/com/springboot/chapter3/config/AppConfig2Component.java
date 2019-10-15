package com.springboot.chapter3.config;

import com.springboot.chapter3.pojo.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Configuration
@ComponentScan(basePackages ="com.springboot.chapter3.*",
        excludeFilters = {@ComponentScan.Filter(classes = {Service.class})})
//@ComponentScan("com.springboot.chapter3.pojo")
//@ComponentScan(basePackageClasses = {User.class})
public class AppConfig2Component {
}
