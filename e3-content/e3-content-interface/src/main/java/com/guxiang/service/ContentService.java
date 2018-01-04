package com.guxiang.service;

import com.guxiang.common.pojo.EasyUIDataGridResult;
import com.guxiang.common.utils.E3Result;
import com.guxiang.pojo.TbContent;

import java.util.List;

/**
 * ContentService
 *
 * @author guxiang
 * @date 2017/12/25
 */
public interface ContentService {

   public E3Result addContent(TbContent content);
   public List<TbContent> getContentListByCid(long cid);
   public EasyUIDataGridResult getContentList(int page, int rows);
}
