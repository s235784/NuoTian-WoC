package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import com.example.woc.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 添加用户（密码未加密）
     * @param account 用户实体
     */
    public void addUser(Account account) {
        String pass = account.getPassword();
        userMapper.insertAccount(account.getUsername(), Encrypt.encryptPass(pass), account.getEmail());
    }

    public String getPasswordByMail(String mail) {
        return userMapper.getPasswordByMail(mail);
    }

    public String getPasswordByName(String name) {
        return userMapper.getPasswordByName(name);
    }
}
