package com.guxiang.controller;

import com.guxiang.common.pojo.EasyUIDataGridResult;
import com.guxiang.common.utils.E3Result;
import com.guxiang.pojo.TbContent;
import com.guxiang.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * ContentController
 *
 * @author guxiang
 * @date 2017/12/26
 */
@Controller
public class ContentController {

    @Resource
    private ContentService contentService;

    @RequestMapping(value="/content/save", method= RequestMethod.POST)
    @ResponseBody
    public E3Result addContent(TbContent content) {
        //调用服务把内容数据保存到数据库
        E3Result e3Result = contentService.addContent(content);
        return e3Result;
    }



    @RequestMapping(value="/content/query/list", method= RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult listContent(Long categoryId,int page,int rows) {
        //调用服务把内容数据保存到数据库
        EasyUIDataGridResult contentList = contentService.getContentList(page, rows);

        return contentList;
    }
}
