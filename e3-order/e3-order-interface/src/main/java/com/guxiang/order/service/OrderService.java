package com.guxiang.order.service;

import com.guxiang.common.utils.E3Result;
import com.guxiang.order.pojo.OrderInfo;

/**
 * OrderService
 *
 * @author guxiang
 * @date 2018/1/6
 */
public interface OrderService {

    E3Result createOrder(OrderInfo orderInfo);

}
