package com.lujun61.userrole.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * @description 自定义UserDetails接口实现类
 * @author Jun Lu
 * @date 2022-09-25 10:10:16
 */
@Data
public class SysUser implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private String realname;

    private Integer isExpired;           // 1：true  0：false
    private Integer isLocked;            // 1：true  0：false
    private Integer isCredentials;       // 1：true  0：false
    private Integer isEnabled;           // 1：true  0：false


    private Date createTime;
    private Date loginTime;

    private List<GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isExpired == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentials == 1;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled == 1;
    }

    public SysUser() {
    }

    public SysUser(Integer id, String username, String password, String realname,
                   Integer isExpired, Integer isLocked,
                   Integer isCredentials, Integer isEnabled,
                   Date createTime, Date loginTime,List<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.isExpired = isExpired;
        this.isLocked = isLocked;
        this.isCredentials = isCredentials;
        this.isEnabled = isEnabled;
        this.createTime = createTime;
        this.loginTime = loginTime;
        this.authorities = authorities;
    }
}
