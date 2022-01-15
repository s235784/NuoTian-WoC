package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.service.UserService;
import com.example.woc.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 完成注册功能
     * 选做：对密码进行加密处理
     * 密码加密在addUser内
     * @param account 账户实体
     */
    @PostMapping("/register")
    public void uploadUsername(Account account) {
        userService.addUser(account);
    }

    /**
     * 完成登录功能
     * @param account 账户实体
     * @return 是否登录成功
     */
    @PostMapping("/login")
    public Boolean login(Account account) {
        String mail = account.getEmail();
        String name = account.getUsername();
        String pass = account.getPassword();

        if (pass == null || "".equals(pass)) {
            return false;
        }

        pass = Encrypt.encryptPass(pass);
        if (mail != null && !"".equals(mail)) {
            return pass.equals(userService.getPasswordByMail(mail));
        }

        if (name != null && !"".equals(name)) {
            return pass.equals(userService.getPasswordByName(name));
        }

        return false;
    }
}


