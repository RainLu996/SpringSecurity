package com.lujun61.userrole.dao;

import com.lujun61.userrole.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {

    int insertSysUser(SysUser user);

    //根据账号名称，获取用户信息
    SysUser selectSysUser(String username);
}
