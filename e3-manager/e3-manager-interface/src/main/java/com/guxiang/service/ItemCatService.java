package com.guxiang.service;

import com.guxiang.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * ItemCatService
 *
 * @author guxiang
 * @date 2017/12/24
 */
public interface ItemCatService {

    List<EasyUITreeNode> getItemCatlist(long parentId);

}
