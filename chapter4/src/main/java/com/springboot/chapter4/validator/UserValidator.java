package com.springboot.chapter4.validator;

import com.springboot.chapter4.pojo.User;

public interface UserValidator {
    public boolean validate(User user);
}
