package com.example.woc.interceptor;

import com.example.woc.annotation.*;
import com.example.woc.entity.Account;
import com.example.woc.enums.ErrorEnum;
import com.example.woc.enums.RoleEnum;
import com.example.woc.exception.LocalException;
import com.example.woc.service.TokenService;
import com.example.woc.util.DecodeCookie;
import com.example.woc.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author NuoTian
 * @date 2022/2/5
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) return true;

        Method method = ((HandlerMethod) handler).getMethod();
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        if (method.isAnnotationPresent(CheckUser.class)) {
            CheckUser checkUser = method.getAnnotation(CheckUser.class);
            if (checkUser.required()) {
                String token = getToken(method, request);
                if (PublicUtil.isEmpty(token)) {
                    throw new LocalException(ErrorEnum.TOKEN_MISS_ERROR);
                }
                Account account = tokenService.getAccount(token);
                request.setAttribute("account", account);
            }
        }

        if (method.isAnnotationPresent(CheckAdmin.class)) {
            CheckAdmin checkAdmin = method.getAnnotation(CheckAdmin.class);
            if (checkAdmin.required()) {
                String token = getToken(method, request);
                if (PublicUtil.isEmpty(token)) {
                    throw new LocalException(ErrorEnum.TOKEN_MISS_ERROR);
                }
                Account account = tokenService.getAccount(token);
                if (account.getRole() < RoleEnum.ROLE_ADMIN.getCode()) {
                    throw new LocalException(ErrorEnum.AUTHORITY_ERROR);
                }
                request.setAttribute("account", account);
            }
        }

        if (method.isAnnotationPresent(CheckSuperAdmin.class)) {
            CheckSuperAdmin checkSuperAdmin = method.getAnnotation(CheckSuperAdmin.class);
            if (checkSuperAdmin.required()) {
                String token = getToken(method, request);
                if (PublicUtil.isEmpty(token)) {
                    throw new LocalException(ErrorEnum.TOKEN_MISS_ERROR);
                }
                Account account = tokenService.getAccount(token);
                if (account.getRole() < RoleEnum.ROLE_SUPER_ADMIN.getCode()) {
                    throw new LocalException(ErrorEnum.AUTHORITY_ERROR);
                }
                request.setAttribute("account", account);
            }
        }
        return true;
    }

    private String getToken(Method method, HttpServletRequest request) {
        if (method.isAnnotationPresent(APIAnnotation.class)) {
            APIAnnotation apiAnnotation = method.getAnnotation(APIAnnotation.class);
            if (apiAnnotation.required()) {
                return request.getHeader("token");
            }
        } else if (method.isAnnotationPresent(HTMLAnnotation.class)) {
            HTMLAnnotation htmlController = method.getAnnotation(HTMLAnnotation.class);
            if (htmlController.required()) {
                return DecodeCookie.getValue(request.getCookies(), "token");
            }
        } else {
            return request.getHeader("token");
        }
        throw new LocalException(ErrorEnum.SERVER_ERROR);
    }
}
