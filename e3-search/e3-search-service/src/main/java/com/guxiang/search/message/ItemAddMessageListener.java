package com.guxiang.search.message;

import com.guxiang.common.pojo.SearchItem;
import com.guxiang.search.mapper.ItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ItemAddMessageListener
 *
 * @author guxiang
 * @date 2018/1/1
 */
public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            long itemId = Long.parseLong(text);
            Thread.sleep(100);
            //根据商品id查询商品信息
            SearchItem searchItem = itemMapper.getItemById(itemId);

            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSell_point());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImage());
            document.addField("item_category_name", searchItem.getCategory_name());
            solrServer.add(document);
            //提交
            solrServer.commit();
            System.out.println("添加商品"+searchItem.getTitle()+"成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
