package com.guxiang.sso.service.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.guxiang.common.jedis.JedisClient;
import com.guxiang.common.utils.E3Result;
import com.guxiang.common.utils.JsonUtils;
import com.guxiang.pojo.TbUser;
import com.guxiang.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TokenServiceImpl
 *
 * @author guxiang
 * @date 2018/1/4
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JedisClient jedisClient;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;
    @Override
    public E3Result getUserByToke(String token) {
        String json = jedisClient.get("SESSION:" + token);
        if (StringUtils.isBlank(json)){
            return E3Result.build(201,"用户登录已经过期");
        }
        jedisClient.expire("SESSION:"+token,SESSION_EXPIRE);
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        return E3Result.ok(user);
    }
}
