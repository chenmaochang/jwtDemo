package com.cmc.jwt.demo.service.impl;

import org.springframework.stereotype.Service;

import com.cmc.jwt.demo.entity.User;
import com.cmc.jwt.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public boolean checkUser(String loginName, String passWord) {
        return true;
    }

    @Override
    public User getUser(String loginName) {
        User user = new User();
        user.setUserName("李四");
        user.setPassword("123");
        return user;
    }
}