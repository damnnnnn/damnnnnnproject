package com.lixin.usereurekaclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserService {

	@Autowired
	private  RestTemplate  restTemplate;
	
	
	@HystrixCommand(fallbackMethod="exceptionStop")
	public  String  queryUserService()
	{
		System.out.println("UserService  is  queryUserService  start.... ");
		
		String result = this.restTemplate.getForObject("http://usereurekaservice/querydeptsByName?dname={1}",
		String.class, "安全部");
		
		return  result;
	}
	
	
	public String exceptionStop()
	{
		return  "服务正在切换,请刷新!";
	}
	
	
}
