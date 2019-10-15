package com.springboot.chapter1;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Chapter1Main {
    @RequestMapping("/test")
    @ResponseBody
    public Map<String,String> test()
    {
        Map<String,String> map = new HashMap<String,String>();
        map.put("key","value1");
        return map;
    }
}
