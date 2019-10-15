package com.thzm.lixin.bootvueproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="/*")
public class CrossFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("CrossFilter  is   init  start....");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("CrossFilter  is   doFilter  start....");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 设置可以跨域访问
//		/* 允许跨域的主机地址 */
		resp.setHeader("Access-Control-Allow-Origin", "*");
//		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
//		/* 重新预检验跨域的缓存时间 (s) */
		resp.setHeader("Access-Control-Max-Age", "100");
//		/* 允许跨域的请求头 */
		resp.setHeader("Access-Control-Allow-Headers", "*");
//		/* 是否携带cookie */
		resp.setHeader("Access-Control-Allow-Credentials", "false");

		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
