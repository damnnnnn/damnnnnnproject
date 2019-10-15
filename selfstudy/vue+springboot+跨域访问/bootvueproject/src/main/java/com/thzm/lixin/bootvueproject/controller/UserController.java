package com.thzm.lixin.bootvueproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600) // 应许跨域的注解
@RequestMapping(value = "/users", method = RequestMethod.POST)
public class UserController {

	@RequestMapping(value = "/code", method = RequestMethod.POST)
	@ResponseBody
	public String creatCode() {
		System.out.println("UserController  is  creatCode  start.... ");

		return "7889";

	}

	@RequestMapping(value = "/checkusername", method = RequestMethod.POST)
	@ResponseBody
	public String checkUserName(HttpServletRequest request) {
		System.out.println("UserController  is  checkUserName  start....");

		String username = request.getParameter("name");
		System.out.println(username);

		String msg = "";

		if (username.equals("李欣")) {
			msg = "fail";
		} else {
			msg = "success";
		}
		

		return JSON.toJSONString(msg);
	}
}
