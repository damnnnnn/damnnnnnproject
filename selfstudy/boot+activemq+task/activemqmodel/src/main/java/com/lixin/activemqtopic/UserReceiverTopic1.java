package com.lixin.activemqtopic;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class UserReceiverTopic1 {
	
	public static void main(String[] args) throws JMSException {
		
		System.out.println("--------------消费者1--------------");
		// 创建ActiveMQ对象，初始化信息
		ActiveMQConnectionFactory mq = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");

		// 2.连接
		Connection conn = mq.createConnection();

		System.out.println("连接成功" + conn);

		// 3.启动连接
		conn.start();

		// 4.得到消息的session对象 1.transacted 事务 2.消息确认模式
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 5.创建队列的名称和对象 订阅模式
		Topic topic = session.createTopic("thzmtopic_one");

		// 6.创建消息的消费者
		MessageConsumer mc = session.createConsumer(topic);

		// 7.监听消息
		mc.setMessageListener(new MessageListener() {

			public void onMessage(Message message) {
				// TODO Auto-generated method stub
				TextMessage tm = (TextMessage) message;

				try {
					System.out.println("*******消费者1受到的消息为-->:" + tm.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

}
