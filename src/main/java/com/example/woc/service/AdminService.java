package com.example.woc.service;

import com.example.woc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NuoTian
 * @date 2022/1/15
 */
@Service
public class AdminService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int getCount() {
        return userMapper.getCount();
    }

    public void deleteAccount(String name) {
        userMapper.deleteAccount(name);
    }
}
