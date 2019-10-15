package com.lixin.activemqtopic;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

//生产者，发送者
public class UserSendTopic {

	public static void main(String[] args) throws JMSException {

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

		// 6.构建消息的生产者
		MessageProducer mp = session.createProducer(topic);
		
		

		// 7.创建消息 [{"name":"13913321098","msg":"你好"},{"name":"13913321098","msg":"你好"},{"name":"13913321098","msg":"你好"}]
		TextMessage tmsg = session.createTextMessage("全力以赴，全世界都会为你让路!");

		// 8.发送消息
		mp.send(tmsg);

		System.out.println("发送消息完毕");

	}
}
