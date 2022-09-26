package com.lujun61.inmemoryauthentication.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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


    /**
     * @description 使用UserDetails的实现类来创建用户信息对象
     * @author Jun Lu
     * @date 2022-09-24 10:36:51
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // 在内存中维护用户信息。
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // 创建一些用户
        manager.createUser(User.withUsername("rainlu")
                .password(passwordEncoder.encode("1234567"))
                .roles("admin", "normal")
                .build());

        manager.createUser(User.withUsername("lujun")
                .password(passwordEncoder.encode("123456"))
                .roles("normal")
                .build());

        return manager;
    }
}
