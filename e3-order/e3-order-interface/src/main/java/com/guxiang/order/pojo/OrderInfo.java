package com.guxiang.order.pojo;

import com.guxiang.pojo.TbOrder;
import com.guxiang.pojo.TbOrderItem;
import com.guxiang.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

/**
 * OrderInfo
 *
 * @author guxiang
 * @date 2018/1/6
 */
public class OrderInfo extends TbOrder implements Serializable {

    private List<TbOrderItem> orderItems;

    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }
    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
