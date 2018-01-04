package com.guxiang.controller;

import com.guxiang.common.pojo.EasyUITreeNode;
import com.guxiang.common.utils.E3Result;
import com.guxiang.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * ContentCatController
 *
 * @author guxiang
 * @date 2017/12/25
 */
@Controller
public class ContentCatController {

    @Resource
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(
            @RequestParam(name="id", defaultValue="0")Long parentId) {
        List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
        return list;
    }

    /**
     * 添加分类节点
     */
    @RequestMapping(value="/content/category/create", method= RequestMethod.POST)
    @ResponseBody
    public E3Result createContentCategory(Long parentId, String name) {
        //调用服务添加节点
        E3Result e3Result = contentCategoryService.addContentCategory(parentId, name);
        return e3Result;
    }


}
