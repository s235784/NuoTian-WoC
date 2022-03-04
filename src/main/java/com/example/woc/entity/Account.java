package com.example.woc.entity;

import com.example.woc.enums.ErrorEnum;
import com.example.woc.enums.RoleEnum;
import com.example.woc.exception.LocalException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 風楪fy
 * @date 2022-01-15 03:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer role;

    public String getRoleName() {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getCode() == role) {
                return roleEnum.getName();
            }
        }
        throw new LocalException(ErrorEnum.SERVER_ERROR);
    }

    public boolean isAdmin() {
        return role >= RoleEnum.ROLE_ADMIN.getCode();
    }

    public boolean isSuperAdmin() {
        return role >= RoleEnum.ROLE_SUPER_ADMIN.getCode();
    }
}
