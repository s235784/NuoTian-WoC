package com.example.woc.controller;

import com.example.woc.annontation.PassToken;
import com.example.woc.entity.Account;
import com.example.woc.enums.ErrorEnum;
import com.example.woc.exception.LocalException;
import com.example.woc.service.TokenService;
import com.example.woc.service.UserService;
import com.example.woc.util.Encrypt;
import com.example.woc.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  風楪fy
 * @date 2022-01-15 01:22
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册功能
     * @param account 账户实体
     */
    @PassToken
    @PostMapping("/register")
    public void uploadUsername(Account account) {
        userService.addAccount(account);
    }

    /**
     * 用户登录
     * @param account 账户实体
     * @return token
     */
    @PassToken
    @PostMapping("/login")
    public Map<String, String> login(Account account) {
        String mail = account.getEmail();
        String name = account.getUsername();
        String pass = account.getPassword();

        if (PublicUtil.isEmpty(pass)) {
            throw new LocalException(ErrorEnum.PARAMS_LOSS_ERROR);
        }

        Account accountDB = null;
        pass = Encrypt.encryptPass(pass);
        if (PublicUtil.isEmpty(mail)) {
            accountDB = userService.getAccountByMail(mail);
        } else if (PublicUtil.isEmpty(name)) {
            accountDB = userService.getAccountByName(name);
        }

        if (accountDB == null) {
            throw new LocalException(ErrorEnum.LOGIN_ERROR);
        }

        if (pass.equals(accountDB.getPassword())) {
            String token = tokenService.createToken(accountDB);
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            return result;
        } else {
            throw new LocalException(ErrorEnum.LOGIN_ERROR);
        }
    }
}
