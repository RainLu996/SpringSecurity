package com.lujun61.userrole.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 这里如果使用super.configure中的方法，将不会使用当前配置的加密规则
        //super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("=======configure HttpSecurity========== ");
        http.authorizeRequests()
                // 指定哪些地址可以访问，和自定义登录有关的页面也需要指定
                .antMatchers("/index", "/login", "/", "/mylogin.html", "/error.html").permitAll()
                .antMatchers("/access/user/**").hasRole("USER")  //
                .antMatchers("/access/read/**").hasRole("READONLY")
                .antMatchers("/access/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/mylogin.html")          // 自定义的登录页面
                .loginProcessingUrl("/login")        // 登录页面提交访问的uri地址
                .failureUrl("/error.html")           // 登录验证错误提示页面
                .and()
                .csrf()
                .disable();
    }
}
