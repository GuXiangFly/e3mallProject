package com.guxiang.sso.service;

import com.guxiang.common.utils.E3Result;
import com.guxiang.pojo.TbUser;

/**
 * RegisterService
 *
 * @author guxiang
 * @date 2018/1/4
 */
public interface RegisterService {
    E3Result checkData(String param, int type);
    E3Result register(TbUser user);
}
