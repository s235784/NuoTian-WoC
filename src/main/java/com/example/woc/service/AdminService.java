package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.enums.ErrorEnum;
import com.example.woc.enums.RoleEnum;
import com.example.woc.exception.LocalException;
import com.example.woc.mapper.UserMapper;
import com.example.woc.util.Encrypt;
import com.example.woc.util.PublicUtil;
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

    /**
     * 获取用户总数
     * @return 用户总数
     */
    public int getCount() {
        return userMapper.getCount();
    }

    /**
     * 根据用户名删除用户
     * @param name 要删除用户的用户名
     * @param role 请求此方法的用户的角色
     */
    public void deleteAccountByName(String name, int role) {
        Account account = userMapper.getAccountByName(name);
        if (account == null) {
            throw new LocalException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }
        if (account.getRole() >= role) {
            throw new LocalException(ErrorEnum.AUTHORITY_ERROR);
        }
        userMapper.deleteAccountByName(name);
    }

    /**
     * 根据邮箱删除用户
     * @param mail 要删除用户的邮箱
     * @param role 请求此方法的用户的角色
     */
    public void deleteAccountByMail(String mail, int role) {
        Account account = userMapper.getAccountByMail(mail);
        if (account == null) {
            throw new LocalException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }
        if (account.getRole() >= role) {
            throw new LocalException(ErrorEnum.AUTHORITY_ERROR);
        }
        userMapper.deleteAccountByMail(mail);
    }

    /**
     * 添加用户（密码未加密）
     * @param account 用户实体
     * @param role 请求此方法的用户的角色
     */
    public void addAccount(Account account, int role) {
        String addName = account.getUsername();
        String addMail = account.getEmail();
        String addPass = account.getPassword();
        Integer addRole = account.getRole();
        if (addRole == null) {
            addRole = RoleEnum.ROLE_USER.getCode();
        }
        if (addRole != RoleEnum.ROLE_USER.getCode() && addRole >= role) {
            throw new LocalException(ErrorEnum.AUTHORITY_ERROR);
        }
        if (PublicUtil.isEmpty(addName, addMail, addPass)) {
            throw new LocalException(ErrorEnum.PARAMS_LOSS_ERROR);
        }
        Account nameAccount = userMapper.getAccountByName(addName);
        Account mailAccount = userMapper.getAccountByMail(addMail);
        if (nameAccount != null || mailAccount != null) {
            throw new LocalException(ErrorEnum.USER_EXIST_ERROR);
        }
        userMapper.insertAccount(account.getUsername(), Encrypt.encryptPass(addPass),
                account.getEmail(), account.getRole());
    }
}
