package com.lixin.bootjwtclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	
	@RequestMapping("/")
	public   String   defaultView()
	{
		System.out.println("IndexController  is  defaultView  start...");
		
		return  "index";
	}
	
	@RequestMapping("/usermain")
	public   String   usermain()
	{
		System.out.println("IndexController  is  usermain  start...");
		
		return  "user_main";
	}
}
