package com.springboot.chapter14.UserValidator;

import com.springboot.chapter14.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(StringUtils.isEmpty(user.getUserName()))
        {
            errors.rejectValue("userName",null,"用户名不能为空");
        }
    }
}
