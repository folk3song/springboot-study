package com.springboot.chapter4.controller;

import com.springboot.chapter4.pojo.User;
import com.springboot.chapter4.service.UserService;
import com.springboot.chapter4.service1.UserServiceImple;
import com.springboot.chapter4.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService = null;

    @Autowired
    private UserServiceImple userServiceImple = null;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(Long id,String userName,String note)
    {
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setNote(note);

        userService.printUser(user);

        return  user;
    }

    @RequestMapping("/vp")
    @ResponseBody
    public User validateAndprint(Long id,String userName,String note)
    {
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setNote(note);
        UserValidator userValidator = (UserValidator)userService;
        if(userValidator.validate(user)) {
            userService.printUser(user);
        }
        return  user;

    }

    @RequestMapping("/print1")
    @ResponseBody
    public User printUser2(Long id ,String userName,String note)
    {
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setNote(note);
        userServiceImple.prinUser(user);
        return user;
    }

    @RequestMapping("/manyAspects")
    @ResponseBody
    public String manyAspects()
    {
        userService.manyAspects();
        return "manyAspects";
    }

}
