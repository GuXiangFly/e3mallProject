package com.guxiang.sso.service.impl;

import com.guxiang.common.jedis.JedisClient;
import com.guxiang.common.utils.E3Result;
import com.guxiang.common.utils.JsonUtils;
import com.guxiang.mapper.TbUserMapper;
import com.guxiang.pojo.TbUser;
import com.guxiang.pojo.TbUserExample;
import com.guxiang.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * LoginServiceImpl
 *
 * @author guxiang
 * @date 2018/1/4
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public E3Result userLogin(String username, String password) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return E3Result.build(400, "用户名或密码错误");
        }
        TbUser tbUser = list.get(0);
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())){
            return E3Result.build(400,"用户名或密码错误");
        }
        String token = UUID.randomUUID().toString();
        jedisClient.set("SESSION:"+token, JsonUtils.objectToJson(tbUser));
        jedisClient.expire("SESSION:"+token, SESSION_EXPIRE);

        return E3Result.ok(token);
    }
}
