package com.example.woc.interceptor;

import com.example.woc.annontation.CheckAdmin;
import com.example.woc.annontation.CheckSuperAdmin;
import com.example.woc.annontation.CheckUser;
import com.example.woc.annontation.PassToken;
import com.example.woc.entity.Account;
import com.example.woc.enums.ErrorEnum;
import com.example.woc.enums.RoleEnum;
import com.example.woc.exception.LocalException;
import com.example.woc.service.TokenService;
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
public class AuthAPIInterceptor implements HandlerInterceptor {
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
                String token = request.getHeader("token");
                if (PublicUtil.isEmpty(token)) {
                    throw new LocalException(ErrorEnum.TOKEN_MISS_ERROR);
                }
                Account account = tokenService.verifyToken(token);
                request.setAttribute("account", account);
            }
        }

        if (method.isAnnotationPresent(CheckAdmin.class)) {
            CheckAdmin checkAdmin = method.getAnnotation(CheckAdmin.class);
            if (checkAdmin.required()) {
                String token = request.getHeader("token");
                if (PublicUtil.isEmpty(token)) {
                    throw new LocalException(ErrorEnum.TOKEN_MISS_ERROR);
                }
                Account account = tokenService.verifyToken(token);
                if (account.getRole() < RoleEnum.ROLE_ADMIN.getCode()) {
                    throw new LocalException(ErrorEnum.AUTHORITY_ERROR);
                }
                request.setAttribute("account", account);
            }
        }

        if (method.isAnnotationPresent(CheckSuperAdmin.class)) {
            CheckSuperAdmin checkSuperAdmin = method.getAnnotation(CheckSuperAdmin.class);
            if (checkSuperAdmin.required()) {
                String token = request.getHeader("token");
                if (PublicUtil.isEmpty(token)) {
                    throw new LocalException(ErrorEnum.TOKEN_MISS_ERROR);
                }
                Account account = tokenService.verifyToken(token);
                if (account.getRole() < RoleEnum.ROLE_SUPER_ADMIN.getCode()) {
                    throw new LocalException(ErrorEnum.AUTHORITY_ERROR);
                }
                request.setAttribute("account", account);
            }
        }
        return true;
    }
}
