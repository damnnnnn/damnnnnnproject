package com.lixin.bootjwtserver.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.JWT;
import com.lixin.bootjwtserver.annation.CheckUser;

public class UserAop   implements  HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("UserAop  is   preHandle  start....");
		
		//如果这个地方跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        //解决了请求头的跨域
        response.setHeader("Access-Control-Allow-Headers", "authorization");

		System.out.println("handler-->"+handler.toString());
		
		
		if(!(handler  instanceof HandlerMethod) ) 
		{
			System.out.println("不是访问方法");
			return  true;
		}
		//1.没有登录，直接访问分离的后台业务方法 
		HandlerMethod   handlerMethpd=(HandlerMethod)handler;
		Method  method=handlerMethpd.getMethod();
		String execMethod=method.getName();
		System.out.println("用户将要执行的方法名:"+execMethod);
		
		//区分方法访问的级别，就是安全性
		if(method.isAnnotationPresent(CheckUser.class))
		{
			CheckUser  ck=method.getDeclaredAnnotation(CheckUser.class);
			// 去检查安全性
			if(ck.check())
			{
				System.out.println("这个方法是安全的，要检查你的令牌.......");
				String  token=request.getHeader("Authorization");
				System.out.println("token-->"+token);
				
				//1.你有没有令牌，没有不能访问
				if(null==token||"".equals(token))
				{
					//springboot自定义异常  统一异常处理机制
					throw   new RuntimeException("令牌是空的，不能访问");
				}
			
				String name= JWT.decode(token).getAudience().get(0);
				System.out.println("name-->"+name);
				
				//2.根据name去查找这个人的权限 ，看这个人有没有权限去执
				//行, 没有return false;  
				
				
				return true;
			}
		}
		
	
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
