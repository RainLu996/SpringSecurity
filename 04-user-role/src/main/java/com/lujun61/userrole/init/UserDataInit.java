package com.lujun61.userrole.init;

import com.lujun61.userrole.dao.SysUserMapper;
import com.lujun61.userrole.entity.SysUser;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @description 给数据库中初始化用户信息，并指定角色
 * @author Jun Lu
 * @date 2022-09-25 09:30:38
 */
//@Component
public class UserDataInit {

    @Resource
    SysUserMapper userMapper;

    @Resource
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void jdbcInit() {

        Date curDate = new Date();

        SysUser user = new SysUser(
                1, "lj", passwordEncoder.encode("123456"), "lujun", 1, 1, 1, 1, curDate, curDate, null
        );
        userMapper.insertSysUser(user);

        SysUser user2 = new SysUser(
                2, "rainlu", passwordEncoder.encode("123456"), "lujun", 1, 1, 1, 1, curDate, curDate, null
        );
        userMapper.insertSysUser(user2);

        SysUser user3 = new SysUser(
                3, "admin", passwordEncoder.encode("123456"), "admin", 1, 1, 1, 1, curDate, curDate, null
        );
        userMapper.insertSysUser(user3);
    }

}
