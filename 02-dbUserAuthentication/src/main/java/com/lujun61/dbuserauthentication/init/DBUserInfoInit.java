package com.lujun61.dbuserauthentication.init;

import com.lujun61.dbuserauthentication.entity.UserInfo;
import com.lujun61.dbuserauthentication.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Jun Lu
 * @description 此类只是初始化数据表中的用户信息以进行下一步的测试。
 * 第一次用完后，请注释掉@Component注解！！！
 * @date 2022-09-23 21:37:45
 */
//@Component
public class DBUserInfoInit {

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        UserInfo user = new UserInfo();
        user.setId("1");
        user.setUsername("rainlu");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRole("normal");
        userService.addUserInfo(user);

        user.setId("2");
        user.setUsername("lujun");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRole("admin");
        userService.addUserInfo(user);
    }

}
