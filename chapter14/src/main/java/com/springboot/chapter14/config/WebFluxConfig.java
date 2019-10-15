package com.springboot.chapter14.config;

import com.springboot.chapter14.UserValidator.UserValidator;
import com.springboot.chapter14.enumeration.SexEnum;
import com.springboot.chapter14.pojo.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {
//    @Override
//    public void addFormatters(FormatterRegistry registry)
//    {
//        registry.addConverter(stringToUserConverter());
//    }
    @Override
    public Validator getValidator()
    {
        return  new UserValidator();
    }

    public Converter<String, User> stringToUserConverter()
    {
        Converter<String,User> converter = new Converter<String, User>() {
            @Override
            public User convert(String src) {
                String strArr[] = src.split("-");
                User user = new User();
                Long id = Long.valueOf(strArr[0]);
                user.setId(id);
                user.setUserName(strArr[1]);
                int sexCode = Integer.valueOf(strArr[2]);
                SexEnum sex = SexEnum.getSexEnum(sexCode);
                user.setSex(sex);
                return user;
            }
        };
        return converter;
    }
}
