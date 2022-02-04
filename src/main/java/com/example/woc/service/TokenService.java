package com.example.woc.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.woc.entity.Account;
import com.example.woc.enums.ErrorEnum;
import com.example.woc.exception.LocalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author NuoTian
 * @date 2022/2/4
 */
@Service
public class TokenService {
    @Value("${jwt.expiration}")
    int expiration;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String createToken(Account account) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, expiration);
        return JWT.create().withAudience(account.getEmail())
                .withExpiresAt(cal.getTime())
                .sign(Algorithm.HMAC256(account.getPassword()));
    }

    public Account verifyToken(String token) {
        String email;
        try {
            email = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new LocalException(ErrorEnum.TOKEN_ERROR);
        }
        Account account = userService.getAccountByMail(email);
        if (account == null) {
            throw new LocalException(ErrorEnum.TOKEN_ERROR);
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(account.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new LocalException(ErrorEnum.TOKEN_ERROR);
        }
        return account;
    }
}
