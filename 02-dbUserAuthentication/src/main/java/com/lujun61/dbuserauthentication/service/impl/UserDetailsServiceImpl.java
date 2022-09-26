package com.lujun61.dbuserauthentication.service.impl;

import com.lujun61.dbuserauthentication.dao.UserMapper;
import com.lujun61.dbuserauthentication.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @description UserDetailsService实现类是为了加载数据表中的用户信息
 * @author Jun Lu
 * @date 2022-09-23 22:07:23
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userMapper.selectUserInfoByName(username);

        if (userInfo != null) {
            List<GrantedAuthority> roleList = new LinkedList<>();
            // 角色命名规则：必须以“ROLE_”开头
            GrantedAuthority role1 = new SimpleGrantedAuthority("ROLE_" + userInfo.getRole());
            roleList.add(role1);

            // 创建并返回User对象（UserDetails实现类）
            return new User(userInfo.getUsername(), userInfo.getPassword(), roleList);
        }

        return null;
    }
}
