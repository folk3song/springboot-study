package com.springboot.chapter9.controller;

import com.springboot.chapter9.UserService.UserService;
import com.springboot.chapter9.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(names = {"user"},types = Long.class)
@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private UserService userService = null;

    @GetMapping("/test")
    public String test(@SessionAttribute("id") Long id, Model model)
    {
        model.addAttribute("id_new",id);
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "session/test";
    }
}
