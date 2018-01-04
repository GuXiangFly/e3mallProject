package com.guxiang.service;

import com.guxiang.common.pojo.EasyUITreeNode;
import com.guxiang.common.utils.E3Result;

import java.util.List;

/**
 * ContentCategoryService
 *
 * @author guxiang
 * @date 2017/12/25
 */
public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCatList(long parentId);
    E3Result addContentCategory(long parentId, String name);
}
