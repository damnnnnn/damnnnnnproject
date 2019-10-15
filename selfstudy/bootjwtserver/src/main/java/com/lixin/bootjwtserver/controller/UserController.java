package com.lixin.bootjwtserver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lixin.bootjwtserver.annation.CheckUser;
import com.lixin.bootjwtserver.model.UserInfo;
import com.lixin.bootjwtserver.service.IService;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
@RequestMapping(value="/users",method=RequestMethod.POST)
public class UserController {
	
	@Autowired
	private IService  uservice;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public  String   login(HttpServletRequest   request)
	{
		System.out.println("UserController  is   login  start...");
		
		String uname=request.getParameter("uname");
		String  upwd=request.getParameter("upwd");
		
		System.out.println(uname+"\t"+upwd);
		
		UserInfo  uinfo = new UserInfo();
		uinfo.setUname(uname);
		uinfo.setUpwd(upwd);
		
		//登录的流程正常去做
		Integer  count=(Integer)this.uservice.checkLogin(uinfo);
		JSONObject  jsonObj = new JSONObject();
		
		if(count>0)
		{
			System.out.println("登录成功");
			
			//JWT生成令牌
			String  token=JWT.create().withAudience(uname).sign(Algorithm.HMAC256(upwd));
			
			System.out.println("token-->"+token);
			jsonObj.put("jwt", token);
			jsonObj.put("logindata", "success");
		}
		else
		{
			System.out.println("登录失败");
			jsonObj.put("logindata", "fail");
		}
		
		
		
		return jsonObj.toString();
	}
	
	//查询公司利润
	@CheckUser
	@RequestMapping(value="/querymoney",method=RequestMethod.POST)
	public  String   queryThzmMoney()
	{
		System.out.println("UserController  is  queryMoney  start...");
		
		return  JSON.toJSONString("查询公司利润");
	}
	
	
	//查询公司地址
	@RequestMapping(value="/selectaddress",method=RequestMethod.POST)
	public  String  queryAddress()
	{
		System.out.println("UserController  is  queryAddress  start...");
		
		return  JSON.toJSONString("查询到的公司的地址是:星火路");
	}
	

}
