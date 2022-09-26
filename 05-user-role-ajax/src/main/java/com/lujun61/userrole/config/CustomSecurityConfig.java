package com.lujun61.userrole.config;

import com.lujun61.userrole.filter.VerificationCodeFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    //把SuccssHandler , FailureHandler注入进来
    @Resource
    private AuthenticationSuccessHandler successHandler;

    @Resource
    private AuthenticationFailureHandler failureHandler;

    @Resource
    private VerificationCodeFilter verificationCodeFilter;

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
                .antMatchers("/index", "/login", "/myajax.html", "/error.html", "/captcha/**").permitAll()
                .antMatchers("/access/user/**").hasRole("USER")  //
                .antMatchers("/access/read/**").hasRole("READONLY")
                .antMatchers("/access/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .loginPage("/myajax.html")          // 自定义的登录页面
                .loginProcessingUrl("/login")        // 登录页面提交访问的uri地址
                .failureUrl("/error.html")           // 登录验证错误提示页面
                .and()
                .csrf()
                .disable();

        // 在 安全认证 的 用户名以及密码验证过滤器之前 添加 验证码验证 的过滤器
        http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // 静态资源全部不进行安全认证
    @Override
    public void configure(WebSecurity security) {
        security.ignoring()
                .antMatchers("/**/*.html")
                .antMatchers("/**/*.htm")
                .antMatchers("/**/*.js")
                .antMatchers("/**/*.png")
                .antMatchers("/**/*.jpg")
                .antMatchers("/favicon.ico")
                .antMatchers("/**/*.css")
                .antMatchers("/images/**");
    }
}
