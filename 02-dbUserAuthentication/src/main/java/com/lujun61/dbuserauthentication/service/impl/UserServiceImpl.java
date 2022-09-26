package com.lujun61.dbuserauthentication.service.impl;

import com.lujun61.dbuserauthentication.dao.UserMapper;
import com.lujun61.dbuserauthentication.entity.UserInfo;
import com.lujun61.dbuserauthentication.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserInfo queryUserInfoByName(String username) {
        return userMapper.selectUserInfoByName(username);
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        return userMapper.insertUserInfo(userInfo);
    }
}
