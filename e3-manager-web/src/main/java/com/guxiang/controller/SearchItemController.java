package com.guxiang.controller;

import com.guxiang.common.utils.E3Result;
import com.guxiang.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * SearchItemController
 *
 * @author guxiang
 * @date 2017/12/29
 */
@Controller
public class SearchItemController {

    @Resource
    private SearchItemService searchItemService;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public E3Result importItemList() {
        E3Result e3Result = searchItemService.importAllItems();
        return e3Result;

    }
}

