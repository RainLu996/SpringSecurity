package com.lujun61.inmemory.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class ApplicationBeanFactory {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //创建 PasswordEncoder 的实现类， 实现类是加密算法：根据项目具体需求，选择不同的加密算法
        return new BCryptPasswordEncoder();
    }
}
