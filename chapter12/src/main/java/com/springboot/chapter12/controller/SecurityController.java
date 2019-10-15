package com.springboot.chapter12.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @RequestMapping("/security")
    public String security()
    {
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("uvxyz");
        System.out.println(passwordEncoder.encode("123456"));
        return "security";
    }
}
