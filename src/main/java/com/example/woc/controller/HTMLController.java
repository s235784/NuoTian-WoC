package com.example.woc.controller;

import com.example.woc.annotation.CheckUser;
import com.example.woc.annotation.HTMLAnnotation;
import com.example.woc.annotation.PassToken;
import com.example.woc.entity.Account;
import com.example.woc.enums.ErrorEnum;
import com.example.woc.exception.LocalException;
import com.example.woc.service.TokenService;
import com.example.woc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author NuoTian
 * @date 2022/3/3
 */
@Controller
public class HTMLController {
    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 网页根目录
     */
    @PassToken
    @HTMLAnnotation
    @RequestMapping("/")
    public void index(@CookieValue(value = "token", defaultValue = "") String token,
                      HttpServletResponse response) {
        try {
            if (token.equals("")) response.sendRedirect("/login");
            else if (tokenService.verifyToken(token)) response.sendRedirect("/home");
            else response.sendRedirect("/login");
        } catch (IOException e) {
            throw new LocalException(ErrorEnum.SERVER_ERROR);
        }
    }

    /**
     * 登录界面
     */
    @PassToken
    @HTMLAnnotation
    @RequestMapping("/login")
    public String loginHTML() {
        return "login";
    }

    /**
     * 主页
     */
    @CheckUser
    @HTMLAnnotation
    @RequestMapping("/home")
    public String homeHTML(HttpServletRequest request, Model model) {
        Account account = (Account) request.getAttribute("account");
        List<Account> userList = userService.getAccountList();
        model.addAttribute("loginUser", account);
        model.addAttribute("userList", userList);
        return "home";
    }
}
