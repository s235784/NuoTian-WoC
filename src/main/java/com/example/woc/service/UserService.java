package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 風楪fy
 * @date 2022-01-15 01:22
 **/
@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Account getAccountByMail(String mail) {
        return userMapper.getAccountByMail(mail);
    }

    public Account getAccountByName(String name) {
        return userMapper.getAccountByName(name);
    }
}
