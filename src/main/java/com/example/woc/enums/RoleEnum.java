package com.example.woc.enums;

import lombok.AllArgsConstructor;

/**
 * @author NuoTian
 * @date 2022/2/4
 */
@AllArgsConstructor
public enum RoleEnum {
    ROLE_USER(0),
    ROLE_ADMIN(1),
    ROLE_SUPER_ADMIN(2);

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
