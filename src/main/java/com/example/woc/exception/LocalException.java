package com.example.woc.exception;

import com.example.woc.enums.ErrorEnum;
import lombok.Getter;

/**
 * @author NuoTian
 * @date 2022/2/4
 */
@Getter
public class LocalException extends RuntimeException {
    private ErrorEnum errorEnum;

    public LocalException(String msg) {
        super(msg);
    }

    public LocalException(ErrorEnum errorEnum) {
        super(errorEnum.getErrMsg());
        this.errorEnum = errorEnum;
    }
}
