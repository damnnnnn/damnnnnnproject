package com.thzm.lixin.mavenfreemarkerinterceptor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thzm.lixin.mavenfreemarkerinterceptor.model.User;

@Controller
public class UserController {
	
	@RequestMapping("/users")
	public  String  queryusersInfo(Model  model)
	{
		
		
		System.out.println("UserController  is  queryusersInfo");  
		
		String  str="刘坚是男的";
		
		String[] arrs= {"成本核算","补助标准","交通出行"};
		
		Set<String>  set = new HashSet<String>();
		set.add("曹正");
		set.add("陈明");
		set.add("李耀辉");
		
		
		List<String>  lists  = new ArrayList<String>();
		lists.add("刘坚");
		lists.add("刘东正");
		lists.add("刘宝");
		
		
		List<User>   ulists  = new ArrayList<User>();
		User  u1 =  new  User();
		u1.setId(1);
		u1.setUsername("陈澳一");
		u1.setUserpwd("123456");
		
		User  u2 =  new  User();
		u2.setId(2);
		u2.setUsername("万慧琴");
		u2.setUserpwd("654321");
		
		ulists.add(u1);
		ulists.add(u2);
		
		
		Map<String,String>  maps = new HashMap<String,String>();
		maps.put("key1", "刘坚");
		maps.put("key2", "刘冬正");
		maps.put("key3", "陈宇涛");
		
		model.addAttribute("strs", str);
		model.addAttribute("arrdatas", arrs);
		model.addAttribute("setdatas",set);
		model.addAttribute("listsdatas", lists);
		model.addAttribute("userdatas", ulists);
		model.addAttribute("namemaps", maps);
		
		System.out.println("UserController  is  queryusersInfo 返回...");
		
		return "index";
		
	}
	
	@RequestMapping("/login")
	public String  checkLogin()
	{
		System.out.println("UserController  is  checkLogin.....");  
		
		return "登录的结果";
	}
	

}
