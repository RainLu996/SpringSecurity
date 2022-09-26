package com.lujun61.inmemoryauthentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleFacadeController {

    @GetMapping("/hello")
    public String responseHelloWorld(){
        return "HelloSpringSecurity！";
    }

}
