package com.springboot.chapter12;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = "com.springboot.chapter12.dao",annotationClass = Repository.class)
@EnableCaching
public class Chapter12Application extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource = null;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${system.user.password.secret}")
    private String secret = null;

    protected void configure(AuthenticationManagerBuilder auth)throws Exception
    {
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(secret);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http)throws Exception
    {
        http.authorizeRequests().antMatchers("/security").hasAnyAuthority("COMMON","ADMIN")
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().permitAll()
                .and().anonymous()
                .and().formLogin()
                .and().httpBasic();
    }


//    String roleQuery = "select u.user_name,r.role_name from t_user u,t_user_role ur,t_role r where u.id=ur.user_id and r.id = ur.role_id and u.user_name = ?";
//
//    String pwdQuery = "select user_name,pwd,available from t_user where user_name = ?";
//
//    protected  void configure(AuthenticationManagerBuilder auth) throws  Exception
//    {
//        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(this.secret);
//        auth.jdbcAuthentication().passwordEncoder(passwordEncoder)
//                .dataSource(dataSource)
//                .usersByUsernameQuery(pwdQuery)
//                .authoritiesByUsernameQuery(roleQuery);
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)throws Exception
//    {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
//                .withUser("admin")
//                .password(passwordEncoder.encode("abc"))
//                .roles("USER","ADMIN")
//                .and()
//                .withUser("myuser")
//                .password(passwordEncoder.encode("123456"))
//                .roles("USER");
//    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter12Application.class, args);
    }

}
