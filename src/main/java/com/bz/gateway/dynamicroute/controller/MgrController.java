package com.bz.gateway.dynamicroute.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mgr")
public class MgrController {

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("mgr")
    public String mgr(){
        return "mgr";
    }

} 