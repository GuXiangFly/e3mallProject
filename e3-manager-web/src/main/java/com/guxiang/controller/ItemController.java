package com.guxiang.controller;

import com.guxiang.common.pojo.EasyUIDataGridResult;
import com.guxiang.common.pojo.EasyUITreeNode;
import com.guxiang.common.utils.E3Result;
import com.guxiang.pojo.TbItem;

import com.guxiang.service.ItemCatService;
import com.guxiang.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * ItemController
 *
 * @author guxiang
 * @date 2017/6/24
 */
@Controller
public class ItemController {

    @Resource
    private ItemService itemService;

    @Resource
    private ItemCatService itemCatService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping("/1")
    public String e1(){
        return  "index";
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //调用服务查询商品列表
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(
            @RequestParam(name="id", defaultValue="0")Long parentId) {
        //调用服务查询节点列表
        List<EasyUITreeNode> list = itemCatService.getItemCatlist(parentId);
        return list;

    }

    /**
     * 商品添加功能
     */
    @RequestMapping(value="/item/save", method= RequestMethod.POST)
    @ResponseBody
    public E3Result addItem(TbItem item, String desc) {
        E3Result result = itemService.addItem(item, desc);
        return result;
    }

}
