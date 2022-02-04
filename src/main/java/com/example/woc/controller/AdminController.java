package com.example.woc.controller;

import com.example.woc.annontation.CheckAdmin;
import com.example.woc.service.AdminService;
import com.example.woc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 風楪fy
 * @date 2022-01-15 04:19
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private AdminService adminService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 获取当前的账户总数
     * @return 用户总数
     */
    @CheckAdmin
    @GetMapping("/getAmount")
    public Integer getAmountOfAccounts(){
        return adminService.getCount();
    }

    /**
     * 根据用户名删除账户
     * @param username 用户名
     */
    @CheckAdmin
    @PutMapping("/deleteAccount")
    public void deleteAccount(String username){
        adminService.deleteAccount(username);
    }
}
