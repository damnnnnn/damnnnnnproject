package com.lixin.bootactivemqcustomer.customer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class Customer {

	@Autowired
	private JavaMailSender jmailsend;

	@Value("${spring.mail.username}")
	private String formail;

	@JmsListener(destination = "${queue}")
	public void receiver(String msg) {

		if (StringUtils.isEmpty(msg)) {
			return;
		}

		System.out.println("消费者收到的生产者的消息为:" + msg);

		JSONObject obj = JSONObject.parseObject(msg);
		String name = (String) obj.get("uname");
		String tomail = (String) obj.get("umail");

		System.out.println(name + "\t" + tomail);

		sendMail(name, tomail);

	}

	public void sendMail(String name, String tomail) {
		// 构建发送消息的对象
		SimpleMailMessage sm = new SimpleMailMessage();

		// 设置发送者
		sm.setFrom(formail);

		// 设置发送给谁
		sm.setTo(tomail);

		// 设置邮件的主题
		sm.setSubject("大家好，全力以赴，全世界都会为你让路");
		
		

		// 设置邮件的内容
		sm.setText("陈澳一，去哪呢?");

		this.jmailsend.send(sm);

		System.out.println("邮件发送成功...........");

	}

}
