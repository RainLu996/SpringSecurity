package com.lujun61.dbuserauthentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleFacadeController {

    @GetMapping("/hello")
    public String responseHelloWorld(){
        return "HelloSpringSecurity！";
    }

    @GetMapping("/normal")
    @PreAuthorize("hasAnyRole('normal')")
    public String normalResponse(){
        return "Hello normalResponse！";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('admin')")
    public String adminResponse(){
        return "Hello adminResponse！";
    }

    @GetMapping("/normal-admin")
    @PreAuthorize("hasAnyRole('admin', 'normal')")
    public String normalAdminResponse(){
        return "Hello normalAdminResponse！";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('user')")
    public String userResponse(){
        return "Hello userResponse！";
    }
}
