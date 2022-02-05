package com.example.woc.controller;

import com.example.woc.annontation.PassToken;
import com.example.woc.entity.Account;
import com.example.woc.enums.RoleEnum;
import com.example.woc.service.AdminService;
import com.example.woc.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author  風楪fy
 * @date 2022-01-15 01:22
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private AdminService adminService;
    private TokenService tokenService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 注册功能
     * @param account 账户实体
     */
    @PassToken
    @PostMapping("/register")
    public void uploadUsername(Account account) {
        account.setRole(RoleEnum.ROLE_USER.getCode());
        adminService.addAccount(account, RoleEnum.ROLE_USER.getCode());
    }

    /**
     * 用户登录
     * @param account 账户实体
     * @return token
     */
    @PassToken
    @PostMapping("/login")
    public Map<String, String> login(Account account) {
        return tokenService.login(account);
    }
}
