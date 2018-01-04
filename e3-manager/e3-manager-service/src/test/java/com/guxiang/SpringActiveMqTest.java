package com.guxiang;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * SpringActiveMq
 *
 * @author guxiang
 * @date 2018/1/1
 */
public class SpringActiveMqTest {

    @Test
    public void sendMessage(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        Destination destination= applicationContext.getBean(ActiveMQQueue.class);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("send guxiang message");
            }
        });
    }
}
