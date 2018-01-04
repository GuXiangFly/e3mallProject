package com.guxiang;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import javax.jms.*;

/**
 * ActiveMqTest
 *
 * @author guxiang
 * @date 2017/12/31
 */
public class ActiveMqTest {

    @Test
    public void testQueueProducer() throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://101.132.177.62:61616");
        // 使用工厂创建
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageProducer producer = session.createProducer(queue);

       // TextMessage textMessage = new ActiveMQTextMessage();
       // textMessage.setText("hello Activemq");
        TextMessage textMessage = session.createTextMessage("hello activemq");
        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testQueueConsumer() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://101.132.177.62:61616");
        // 使用工厂创建
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //等待接收消息
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();

    }


    @Test
    public void testTopicProducer() throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://101.132.177.62:61616");
        // 使用工厂创建
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageProducer producer = session.createProducer(topic);

        // TextMessage textMessage = new ActiveMQTextMessage();
        // textMessage.setText("hello Activemq");
        TextMessage textMessage = session.createTextMessage("hello activemq");
        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicConsumer() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://101.132.177.62:61616");
        // 使用工厂创建
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //等待接收消息
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();

    }
}
