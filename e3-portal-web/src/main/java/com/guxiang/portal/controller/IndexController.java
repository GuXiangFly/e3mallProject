package com.guxiang.portal.controller;

import com.guxiang.pojo.TbContent;
import com.guxiang.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * IndexController
 *
 * @author guxiang
 * @date 2017/12/25
 */

@Controller
public class IndexController {

    @Value("${CONTENT_LUNBO_ID}")
    private Long CONTENT_LUNBO_ID;

    @Resource
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
       //查询内容列表
        List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
        // 把结果传递给页
        model.addAttribute("ad1List", ad1List);
        return "index";
    }
}
