package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.enums.RoleEnum;
import com.example.woc.mapper.UserMapper;
import com.example.woc.util.Encrypt;
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

    /**
     * 添加用户（密码未加密）
     * @param account 用户实体
     */
    public void addAccount(Account account) {
        String pass = account.getPassword();
        userMapper.insertAccount(account.getUsername(), Encrypt.encryptPass(pass),
                account.getEmail(), RoleEnum.ROLE_USER.getCode());
    }

    public Account getAccountByMail(String mail) {
        return userMapper.getAccountByMail(mail);
    }

    public Account getAccountByName(String name) {
        return userMapper.getAccountByName(name);
    }
}
