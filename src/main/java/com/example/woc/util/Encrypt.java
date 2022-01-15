package com.example.woc.util;

import org.springframework.util.DigestUtils;

/**
 * @author NuoTian
 * @date 2022/1/15
 */
public class Encrypt {
    public static final String verCode = "@woc.NuoTian.2022";
    public static String encryptPass(String pass) {
        return DigestUtils.md5DigestAsHex((pass + verCode).getBytes());
    }
}
