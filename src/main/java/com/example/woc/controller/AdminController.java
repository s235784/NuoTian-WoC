package com.example.woc.controller;

import com.example.woc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 04:19
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
    @GetMapping("/getAmount")
    public Integer getAmountOfAccounts(){
        return adminService.getCount();
    }

    /**
     * 根据用户名删除账户
     * @param username 用户名
     */
    @PutMapping("/deleteAccount")
    public void deleteAccount(String username){
        adminService.deleteAccount(username);
    }
}
