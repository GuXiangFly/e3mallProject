package com.guxiang.service;

import com.guxiang.common.pojo.EasyUIDataGridResult;
import com.guxiang.common.utils.E3Result;
import com.guxiang.pojo.TbItem;
import com.guxiang.pojo.TbItemDesc;

/**
 * ItemService
 *
 * @author guxiang
 * @date 2017/6/24
 */
public interface ItemService {

    public TbItem getItemById(Long itemId);

    public TbItemDesc getItemDescById(long itemId);

    public EasyUIDataGridResult getItemList(int page, int rows);

    public E3Result addItem(TbItem item, String desc);
}
