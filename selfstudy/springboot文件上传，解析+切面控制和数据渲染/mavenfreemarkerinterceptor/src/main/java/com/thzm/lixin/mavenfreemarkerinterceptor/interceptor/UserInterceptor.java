package com.thzm.lixin.mavenfreemarkerinterceptor.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.thzm.lixin.mavenfreemarkerinterceptor.config.PropertiesBean;

public class UserInterceptor implements HandlerInterceptor {

	@Autowired
	private PropertiesBean pbean;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("UserInterceptor  is  preHandle[前置通知]  start....");

		String headInfo = request.getHeader("User-Agent");
		System.out.println("headInfo-->" + headInfo);

		if (!headInfo.contains("Mozilla")) {
			System.out.println("你这个不是正常的访问，可能是爬虫");
			response.setCharacterEncoding("UTF-8");
			PrintWriter pw = response.getWriter();
			pw.println("对不起, 你是非法访问");
			pw.flush();
			return false;

		}

		// 1.黑名单，非法IP，非工作时间，权限（登录用户)

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("UserInterceptor  is  postHandle  start...... ");

		System.out.println("handler-->" + handler.toString());

		// public java.lang.String
		// com.thzm.lixin.mavenfreemarkerinterceptor.controller.UserController.queryusersInfo(org.springframework.ui.Model)

		String[] datas = handler.toString().split("\\(");
		System.out.println(datas[0].substring(datas[0].lastIndexOf(".") + 1));
		// 表中的列名我们不需要从数据中获取，可以从配置文件或缓存中获取。

		if (datas[0].substring(datas[0].lastIndexOf(".") + 1).contains("users")) {
			String user_column_info = this.pbean.getUserinfo();
			System.out.println(user_column_info);

			String[] columns = user_column_info.split(",");
			modelAndView.addObject("cnames", columns);
			
			
		} else if (datas[0].substring(datas[0].lastIndexOf(".") + 1).contains("depts")) {

		}

//       String[] columns= {"编号","姓名","密码"};

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
