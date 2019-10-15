package com.springboot.chapter16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CloseController {
    @RequestMapping("/test")
    public String getTest()
    {
        return "close";
    }
}
