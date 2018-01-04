package com.guxiang.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guxiang.common.jedis.JedisClient;
import com.guxiang.common.pojo.EasyUIDataGridResult;
import com.guxiang.common.utils.E3Result;
import com.guxiang.common.utils.JsonUtils;
import com.guxiang.mapper.TbContentMapper;
import com.guxiang.pojo.TbContent;
import com.guxiang.pojo.TbContentExample;
import com.guxiang.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * ContentServiceImpl
 *
 * @author guxiang
 * @date 2017/12/26
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;


    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;



    @Override
    public E3Result addContent(TbContent content) {

        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        //缓存同步
        jedisClient.hdel(CONTENT_LIST,content.getCategoryId()+"");
        return E3Result.ok();
    }

    @Override
    public List<TbContent> getContentListByCid(long cid) {

        //查询缓存
        try {
            //如果缓存中有直接响应结果
            String json = jedisClient.hget(CONTENT_LIST, cid + "");
            if (StringUtils.isNoneBlank(json)){
                List<TbContent> objects = JsonUtils.jsonToList(json,TbContent.class);
                return objects;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample contentExample = new TbContentExample();
        TbContentExample.Criteria criteria = contentExample.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> tbContents = contentMapper.selectByExample(contentExample);

        //添加缓存
        try {
            Long json = jedisClient.hset(CONTENT_LIST,cid+"",JsonUtils.objectToJson(tbContents));

        }catch (Exception e){
            e.printStackTrace();
        }
        return tbContents;
    }

    @Override
    public EasyUIDataGridResult getContentList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbContentExample contentExample = new TbContentExample();
        List<TbContent> tbContents = contentMapper.selectByExample(contentExample);

        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(tbContents);

        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
        long total = pageInfo.getTotal();
        result.setTotal(total);
        return result;
    }


}
