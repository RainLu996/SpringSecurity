package com.lujun61.inmemoryauthentication.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * @description 基于内存的用户信息配置-方式二：使用UserDetails的实现类创建对象，再注册进configure方法中
 * @author Jun Lu
 * @date 2022-09-24 10:33:36
 */
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    // 在configure方法中定义创建的用户信息
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.userDetailsService(userDetailsService);
    }
}
