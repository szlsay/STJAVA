package com.itheima.controller;

import com.itheima.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping("/rule")
    public String rule(){
        ruleService.rule();
        return "OK";
    }
}
