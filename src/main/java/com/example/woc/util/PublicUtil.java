package com.example.woc.util;

import com.example.woc.enums.ErrorEnum;
import com.example.woc.exception.LocalException;

/**
 * @author NuoTian
 * @date 2022/2/4
 */
public class PublicUtil {
    /**
     * 判断多个字符串是否为空
     * @param value 要判断的字符串
     * @return 有空的就返回true
     */
    public static boolean isEmpty(String... value) {
        for (String s : value) {
            if (s == null || "".equals(s))
                return true;
        }
        return false;
    }

    /**
     * 把String转换成Integer类型
     * @param s 要转化的字符串
     * @return 转化成的Integer
     */
    public static Integer parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new LocalException(ErrorEnum.PARAMS_ILLEGAL_ERROR);
        }
    }
}
