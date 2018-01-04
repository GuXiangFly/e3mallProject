package com.guxiang.sso.service;

import com.guxiang.common.utils.E3Result;

/**
 * LoginService
 *
 * @author guxiang
 * @date 2018/1/4
 */
public interface LoginService {
    public E3Result userLogin(String username, String password);

}
