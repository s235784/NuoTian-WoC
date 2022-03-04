package com.example.woc.enums;

import lombok.AllArgsConstructor;

/**
 * @author NuoTian
 * @date 2022/2/4
 */
@AllArgsConstructor
public enum RoleEnum {
    ROLE_USER(0, "用户"),
    ROLE_ADMIN(1, "管理员"),
    ROLE_SUPER_ADMIN(2, "超级管理员");

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
