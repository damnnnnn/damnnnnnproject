package com.lixin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoablFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("GoablFilter  is   doFilter  start....");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		

		//全局的设置
		
		// 设置中文不乱码
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// 设置可以跨域访问
//		/* 允许跨域的主机地址 */
		resp.setHeader("Access-Control-Allow-Origin", "*");
//		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST,DELETE,PUT");
//		/* 重新预检验跨域的缓存时间 (s) */
		resp.setHeader("Access-Control-Max-Age", "100");
//		/* 允许跨域的请求头 */
		resp.setHeader("Access-Control-Allow-Headers", "*");
//		/* 是否携带cookie */
		resp.setHeader("Access-Control-Allow-Credentials", "false");
		
		
		String  actionurl=req.getRequestURI();
		System.out.println("actionurl-->"+actionurl);
		
		//实施权限控制，这个人是登录用户，在session 取出这个人的信息，去查询这个人拥有的功能
		

		chain.doFilter(req, resp);

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
