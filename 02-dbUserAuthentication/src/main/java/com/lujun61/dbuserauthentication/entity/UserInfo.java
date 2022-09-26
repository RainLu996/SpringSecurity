package com.lujun61.dbuserauthentication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    // 用户id
    private String id;
    // 用户名
    private String username;
    // 用户密码
    private String password;
    // 用户角色
    private String role;

}
