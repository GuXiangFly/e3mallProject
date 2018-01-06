package com.guxiang.order.controller;

import com.guxiang.cart.service.CartService;
import com.guxiang.pojo.TbItem;
import com.guxiang.pojo.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * OrderController
 *
 * @author guxiang
 * @date 2018/1/6
 */
@Controller
public class OrderController {

    @Resource
    private CartService cartService;

    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request) {
        TbUser user= (TbUser) request.getAttribute("user");

        List<TbItem> cartList = cartService.getCartList(user.getId());
        request.setAttribute("cartList", cartList);
        return "order-cart";
    }
}
