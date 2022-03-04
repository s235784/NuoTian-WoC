package com.example.woc.controller;

import com.example.woc.annotation.APIAnnotation;
import com.example.woc.annotation.CheckAdmin;
import com.example.woc.entity.Account;
import com.example.woc.enums.ErrorEnum;
import com.example.woc.exception.LocalException;
import com.example.woc.service.AdminService;
import com.example.woc.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 風楪fy
 * @date 2022-01-15 04:19
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 获取当前的账户总数
     * @return 用户总数
     */
    @CheckAdmin
    @APIAnnotation
    @GetMapping("/getAmount")
    public Integer getAmountOfAccounts() {
        return adminService.getCount();
    }

    /**
     * 删除用户
     * @param key 删除用户的方式（Name Mail）
     * @param value 此方式下用户的信息
     */
    @CheckAdmin
    @APIAnnotation
    @PostMapping("/deleteAccountBy{key}")
    public void deleteAccount(@PathVariable String key, String value,
                              HttpServletRequest request) {
        if (PublicUtil.isEmpty(value)) {
            throw new LocalException(ErrorEnum.PARAMS_LOSS_ERROR);
        }
        Account account = (Account) request.getAttribute("account");
        switch (key) {
            case "Name":
                adminService.deleteAccountByName(value, account.getRole());
                break;
            case "Mail":
                adminService.deleteAccountByMail(value, account.getRole());
                break;
            case "Id":
                adminService.deleteAccountById(PublicUtil.parseInt(value), account.getRole());
                break;
            default:
                throw new LocalException(ErrorEnum.PARAMS_LOSS_ERROR);
        }
    }

    /**
     * 注册功能
     * @param addAccount 账户实体
     */
    @CheckAdmin
    @APIAnnotation
    @PostMapping("/addAccount")
    public void uploadUsername(Account addAccount, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("account");
        adminService.addAccount(addAccount, account.getRole());
    }
}
