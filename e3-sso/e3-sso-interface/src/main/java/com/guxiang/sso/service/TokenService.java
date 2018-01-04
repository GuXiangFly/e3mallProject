package com.guxiang.sso.service;

import com.guxiang.common.utils.E3Result;

/**
 * TokenService
 *
 * @author guxiang
 * @date 2018/1/4
 */
public interface TokenService {
    E3Result getUserByToke(String token);
}
