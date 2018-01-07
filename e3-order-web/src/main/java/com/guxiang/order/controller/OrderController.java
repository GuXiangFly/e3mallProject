package com.guxiang.order.controller;

import com.guxiang.cart.service.CartService;
import com.guxiang.common.utils.E3Result;
import com.guxiang.order.pojo.OrderInfo;
import com.guxiang.order.service.OrderService;
import com.guxiang.pojo.TbItem;
import com.guxiang.pojo.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @Resource
    private OrderService orderService;



    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request) {
        TbUser user= (TbUser) request.getAttribute("user");

        List<TbItem> cartList = cartService.getCartList(user.getId());
        request.setAttribute("cartList", cartList);
        return "order-cart";
    }

    @RequestMapping(value="/order/create", method= RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        //把用户信息添加到orderInfo中。
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        //调用服务生成订单
        E3Result e3Result = orderService.createOrder(orderInfo);
        if (e3Result.getStatus()==200){
            cartService.clearCartItem(user.getId());
        }
        //把订单号传递给页面
        request.setAttribute("orderId", e3Result.getData());
        request.setAttribute("payment", orderInfo.getPayment());
        return "success";
    }
}
