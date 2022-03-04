package com.example.woc.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.woc.entity.Account;
import com.example.woc.enums.ErrorEnum;
import com.example.woc.exception.LocalException;
import com.example.woc.util.Encrypt;
import com.example.woc.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

    public boolean verifyToken(String token) {
        String email;
        try {
            email = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            return false;
        }
        Account account = userService.getAccountByMail(email);
        if (account == null) {
            return false;
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(account.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    public Account getAccount(String token) {
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

    public Map<String, String> login(Account account) {
        String mail = account.getEmail();
        String name = account.getUsername();
        String pass = account.getPassword();

        if (PublicUtil.isEmpty(pass)) {
            throw new LocalException(ErrorEnum.PARAMS_LOSS_ERROR);
        }

        Account accountDB = null;
        pass = Encrypt.encryptPass(pass);
        if (!PublicUtil.isEmpty(mail)) {
            accountDB = userService.getAccountByMail(mail);
        } else if (!PublicUtil.isEmpty(name)) {
            accountDB = userService.getAccountByName(name);
        }

        if (accountDB == null) {
            throw new LocalException(ErrorEnum.LOGIN_ERROR);
        }

        if (pass.equals(accountDB.getPassword())) {
            String token = createToken(accountDB);
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            return result;
        } else {
            throw new LocalException(ErrorEnum.LOGIN_ERROR);
        }
    }
}
