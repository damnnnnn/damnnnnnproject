package com.lixin.bootactivemqproduct.procduct;

import java.util.Random;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;


@Component
public class MessageProcudt {
	
	
	@Autowired
	private Queue  queue;
	
	
	@Autowired   //jms标准
	private JmsTemplate  jmsTemplate;
	
	
	
	@Scheduled(fixedDelay=60000)
	public void  sendMessgae()
	{
		
		String  name="李国"+new Random().nextInt(100);
		
		String  tomail="1365728923@qq.com";
		
		JSONObject  jsonObj = new JSONObject();
		jsonObj.put("uname", name);
		jsonObj.put("umail", tomail);
		
		System.out.println("生产者向消费者构建的消息为:"+jsonObj.toString());
		
		//发送消息给消费者
		jmsTemplate.convertAndSend(queue, jsonObj.toJSONString());
		
		
		
	}
	

}
