package com.lujun61.dbuserauthentication.dao;

import com.lujun61.dbuserauthentication.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    UserInfo selectUserInfoByName(String username);

    int insertUserInfo(UserInfo userInfo);

}
