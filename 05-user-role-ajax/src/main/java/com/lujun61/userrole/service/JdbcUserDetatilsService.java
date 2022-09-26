package com.lujun61.userrole.service;


import com.lujun61.userrole.dao.SysRoleMapper;
import com.lujun61.userrole.dao.SysUserMapper;
import com.lujun61.userrole.entity.SysRole;
import com.lujun61.userrole.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class JdbcUserDetatilsService implements UserDetailsService {

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. 根据username 获取SysUser
        SysUser user = userMapper.selectSysUser(username);
        System.out.println("loadUserByUsername user:" + user);

        // 2、给自定义的 UserDetails实现类：SysUser 封装 授权 信息
        if (user != null) {
            // 3. 根据userid 获取用户的 role 信息
            List<SysRole> roleList = roleMapper.selectRoleByUser(user.getId());
            System.out.println("roleList:" + roleList);

            List<GrantedAuthority> authorities = new ArrayList<>();
            String roleName = "";
            for (SysRole role : roleList) {
                roleName = role.getName();
                // SimpleGrantedAuthority最常用
                //参数角色名称，需要以"ROLE_"开头， 后面加上自定义的角色名称
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);
                authorities.add(authority);
            }
            user.setAuthorities(authorities);
        }
        return user;
    }
}
