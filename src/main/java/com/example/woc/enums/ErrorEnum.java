package com.example.woc.enums;

import lombok.AllArgsConstructor;

/**
 * @author NuoTian
 * @date 2022/2/4
 */
@AllArgsConstructor
public enum ErrorEnum {
    COMMON_ERROR(1000, "错误"),
    PARAMS_LOSS_ERROR(1001, "参数不足"),
    LOGIN_ERROR(2000, "账号或密码错误"),
    TOKEN_ERROR(3000, "Token验证失败"),
    TOKEN_MISS_ERROR(3001, "缺少Token"),
    AUTHORITY_ERROR(4000, "权限不足");

    private final int errCode;
    private final String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public int getErrCode() {
        return errCode;
    }
}