package com.example.woc.util;

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
}
