package com.guxiang.pagehelper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TestPublish
 *
 * @author guxiang
 * @date 2017/12/25
 */
public class TestPublish {

    public void publishService() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        System.out.println("服务已经启动。。。。");
        System.in.read();
        System.out.println("服务已经关闭");
    }
}
