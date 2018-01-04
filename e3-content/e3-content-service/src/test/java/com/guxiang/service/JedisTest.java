package com.guxiang.service;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * JedisTest
 *
 * @author guxiang
 * @date 2017/12/27
 */
public class JedisTest {

    @Test
    public void testJedis() throws Exception{
        //创建一个jedis链接
        Jedis jedis = new Jedis("101.132.177.62",6379);
        jedis.set("name1","guxiang");
        String name1 = jedis.get("name1");
        System.out.println(name1);
        jedis.close();

    }

    public void testJedisPool() throws Exception{
        JedisPool jedisPool = new JedisPool("101.132.177.62",6379);
        Jedis resource = jedisPool.getResource();

    }
}
