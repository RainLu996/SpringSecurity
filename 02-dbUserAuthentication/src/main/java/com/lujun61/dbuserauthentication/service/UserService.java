package com.lujun61.dbuserauthentication.service;

import com.lujun61.dbuserauthentication.entity.UserInfo;

public interface UserService {

    UserInfo queryUserInfoByName(String username);

    int addUserInfo(UserInfo userInfo);
}
