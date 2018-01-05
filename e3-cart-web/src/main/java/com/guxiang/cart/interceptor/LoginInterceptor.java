package com.guxiang.cart.interceptor;

import com.guxiang.common.utils.CookieUtils;
import com.guxiang.common.utils.E3Result;
import com.guxiang.pojo.TbUser;
import com.guxiang.sso.service.TokenService;
import jdk.nashorn.internal.parser.Token;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginInterceptor
 *
 * @author guxiang
 * @date 2018/1/5
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 前处理，执行handler之前执行此方法。
        //返回true，放行	false：拦截
        //1.从cookie中取token
        String token = CookieUtils.getCookieValue(httpServletRequest, "token");
        if (StringUtils.isBlank(token)){
            return true;
        }
        E3Result e3Result = tokenService.getUserByToke(token);

        if (e3Result.getStatus() != 200) {
            return true;
        }

        TbUser user = (TbUser) e3Result.getData();
        httpServletRequest.setAttribute("user", user);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //handler执行后 ，返回modeAndView之前
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //完成处理，返回ModelAndView之后。
        //可以再此处理异常

    }
}
