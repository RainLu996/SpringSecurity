package com.lujun61.userrole.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // 指定项目默认欢迎资源文件
    @GetMapping("/")
    public String toIndexHtmlOne(){
        return "forward:/index.html";
    }

    @GetMapping("/index")
    public String toIndexHtmlTwo(){
        return "forward:/index.html";
    }
}
