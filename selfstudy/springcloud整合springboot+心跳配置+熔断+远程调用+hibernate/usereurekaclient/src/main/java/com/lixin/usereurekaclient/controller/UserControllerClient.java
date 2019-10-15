package com.lixin.usereurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.lixin.usereurekaclient.service.UserService;

@Controller
public class UserControllerClient {

	@Autowired
	private  UserService   uservice;

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		System.out.println("UserControllerClient  is  index");

		// 远程呼叫
//		String result = this.restTempplate.getForObject("http://usereurekaservice/querydeptsByName?dname={1}",
//				String.class, "安全部");
		
		String  result=this.uservice.queryUserService();

		System.out.println("result-->" + result);

		return result;
	}

}
