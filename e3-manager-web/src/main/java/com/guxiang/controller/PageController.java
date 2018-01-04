package com.guxiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PageController
 *
 * @author guxiang
 * @date 2017/7/2
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }


    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

}
