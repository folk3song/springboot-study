package com.springboot.chapter3.config;

import com.springboot.chapter3.pojo.ScopeBean;
import com.springboot.chapter3.pojo.User;
import com.springboot.chapter3.pojo.definition.BussinessPerson;
import com.springboot.chapter3.pojo.definition.Person;
import com.springboot.chapter3.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {
    private static Logger log = Logger.getLogger(IOCTest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig2Component.class);

//        ScopeBean scopeBean = ctx.getBean(ScopeBean.class);
//        ScopeBean scopeBean1 = ctx.getBean(ScopeBean.class);
//        System.out.println(scopeBean == scopeBean1);

        Person person = ctx.getBean(BussinessPerson.class);
        person.service();
//        User user = ctx.getBean(User.class);
//        UserService service = ctx.getBean(UserService.class);
//        log.info(user.getId());
//        log.info(user.getNote());
//        service.printUser(user);


    }
}
