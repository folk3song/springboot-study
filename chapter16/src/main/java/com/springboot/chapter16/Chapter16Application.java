package com.springboot.chapter16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.ws.EndpointReference;

@SpringBootApplication
public class Chapter16Application extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("USER","ADMIN")
                .and()
                .withUser("myuser")
                .password(passwordEncoder.encode("abc"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception
    {
        String [] endPoints = {"audievents","beans"};

        http.requestMatcher(EndpointRequest.to(endPoints))
                .authorizeRequests().anyRequest()
                .hasRole("ADMIN")
                .and()
                .antMatcher("/close").authorizeRequests().anyRequest().hasRole("ADMIN")
                .and()
                .httpBasic();
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter16Application.class, args);
    }

}
