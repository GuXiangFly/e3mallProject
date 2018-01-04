package com.guxiang.service.impl;

import com.guxiang.common.pojo.EasyUITreeNode;
import com.guxiang.mapper.TbItemCatMapper;
import com.guxiang.pojo.TbItemCat;
import com.guxiang.pojo.TbItemCatExample;
import com.guxiang.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {


    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatlist(long parentId) {
        //根据父节点查询子节点
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        //执行条件
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        //创建返回结果的list
        List<EasyUITreeNode> resultlist = new ArrayList<>();


        //列表转换为EasyUITreeNote
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode node = new EasyUITreeNode();

            //设置属性
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            resultlist.add(node);
        }

        return resultlist;
    }
}