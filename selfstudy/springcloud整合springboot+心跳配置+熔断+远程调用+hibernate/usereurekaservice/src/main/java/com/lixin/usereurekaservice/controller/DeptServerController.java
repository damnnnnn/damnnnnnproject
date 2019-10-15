package com.lixin.usereurekaservice.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lixin.usereurekaservice.model.UserInfo;
import com.lixin.usereurekaservice.service.IUserService;
import com.lixin.usereurekaservice.service.UserServiceImpl;

@Controller
@AutoConfigurationPackage
public class DeptServerController {

	@Autowired
	private IUserService userservice;

	@RequestMapping("/querydeptsByName")
	@ResponseBody
	public String querydeptsByName(@RequestParam String dname) {
		System.out.println("DeptServerController  is  querydeptsByName  start...");

		Iterable<UserInfo> its = this.userservice.queryUserAll();

		String jsonStr = JSON.toJSONString(its);
		
		System.out.println("jsonStr-->"+jsonStr);

		return jsonStr;
	}

}
