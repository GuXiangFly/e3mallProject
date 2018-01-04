package com.guxiang.item.listener;

import com.guxiang.item.pojo.Item;
import com.guxiang.pojo.TbItem;
import com.guxiang.pojo.TbItemDesc;
import com.guxiang.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * HtmlGenListener
 *
 * @author guxiang
 * @date 2018/1/3
 */

public class HtmlGenListener implements MessageListener {

    @Resource
    private ItemService itemService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${HTML_GEN_PATH}")
    private String HTML_GEN_PATH;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            long itemId = Long.parseLong(textMessage.getText());
            TbItem tbItem = itemService.getItemById(itemId);
            Item item = new Item(tbItem);

            TbItemDesc itemDesc = itemService.getItemDescById(itemId);
            Map data = new HashMap<>();
            data.put("item", item);
            data.put("itemDesc", itemDesc);

            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");

            //创建一个输出流，指定输出的目录及文件名。
            Writer out = new FileWriter(HTML_GEN_PATH + itemId + ".html");
            //生成静态页面。
            template.process(data, out);
            //关闭流
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
