package com.guxiang.cart.service;

import com.guxiang.common.utils.E3Result;
import com.guxiang.pojo.TbItem;

import java.util.List;

/**
 * CartService
 *
 * @author guxiang
 * @date 2018/1/5
 */

public interface CartService {

    E3Result addCart(long userId, long itemId, int num);
    E3Result mergeCart(long userId, List<TbItem> itemList);
    List<TbItem> getCartList(long userId);
    E3Result updateCartNum(long userId, long itemId, int num);
    E3Result deleteCartItem(long userId, long itemId);
}
