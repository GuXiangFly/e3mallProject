package com.guxiang.item.controller;

import com.guxiang.item.pojo.Item;
import com.guxiang.pojo.TbItem;
import com.guxiang.pojo.TbItemDesc;
import com.guxiang.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


/**
 * ItemController
 *
 * @author guxiang
 * @date 2018/1/2
 */
@Controller
public class ItemController {

    @Resource
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model) {

        TbItem tbItem = itemService.getItemById(itemId);
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        Item item = new Item(tbItem);
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }
}
