package com.guxiang.search.mapper;

import com.guxiang.common.pojo.SearchItem;

import java.util.List;

/**
 * ItemMapper
 *
 * @author guxiang
 * @date 2017/12/28
 */
public interface ItemMapper {
    List<SearchItem> getItemList();
    SearchItem getItemById(long itemId);
}
