package com.guxiang.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guxiang.mapper.TbItemMapper;
import com.guxiang.pojo.TbItem;
import com.guxiang.pojo.TbItemExample;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.*;
import java.util.List;

/**
 * TestPageHelper
 *
 * @author guxiang
 * @date 2017/7/16
 */
public class TestPageHelper {

        @Test
        public void testPageHelper() throws Exception {
            //初始化spring容器
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
            //从容器中获得Mapper代理对象
            TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
            //执行sql语句之前设置分页信息使用PageHelper的startPage方法。
            PageHelper.startPage(1, 1);
            //执行查询
            TbItemExample example = new TbItemExample();
            List<TbItem> list = itemMapper.selectByExample(example);
            //取分页信息，PageInfo。1、总记录数2、总页数 。当前页码
            PageInfo<TbItem> pageInfo = new PageInfo<>(list);
            System.out.println(pageInfo.getTotal());
            System.out.println(pageInfo.getPages());
            System.out.println(list.size());
        }



}
