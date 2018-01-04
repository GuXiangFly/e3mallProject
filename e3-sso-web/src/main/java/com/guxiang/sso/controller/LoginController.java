package com.guxiang.sso.controller;

import com.guxiang.common.utils.CookieUtils;
import com.guxiang.common.utils.E3Result;
import com.guxiang.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginController
 *
 * @author guxiang
 * @date 2018/1/4
 */
@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping("/page/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value="/user/login", method= RequestMethod.POST)
    @ResponseBody
    public E3Result login(String username, String password,
                          HttpServletRequest request, HttpServletResponse response) {
        E3Result e3Result = loginService.userLogin(username, password);
        //判断是否登录成功
        if(e3Result.getStatus() == 200) {
            String token = e3Result.getData().toString();
          CookieUtils.setCookie(request,response,TOKEN_KEY,token);
        }
        return e3Result;
    }
}
