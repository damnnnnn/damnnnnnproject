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
		

		//ȫ�ֵ�����
		
		// �������Ĳ�����
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// ���ÿ��Կ������
//		/* ��������������ַ */
		resp.setHeader("Access-Control-Allow-Origin", "*");
//		/* �����������󷽷�GET, POST, HEAD �� */
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST,DELETE,PUT");
//		/* ����Ԥ�������Ļ���ʱ�� (s) */
		resp.setHeader("Access-Control-Max-Age", "100");
//		/* ������������ͷ */
		resp.setHeader("Access-Control-Allow-Headers", "*");
//		/* �Ƿ�Я��cookie */
		resp.setHeader("Access-Control-Allow-Credentials", "false");
		
		
		String  actionurl=req.getRequestURI();
		System.out.println("actionurl-->"+actionurl);
		
		//ʵʩȨ�޿��ƣ�������ǵ�¼�û�����session ȡ������˵���Ϣ��ȥ��ѯ�����ӵ�еĹ���
		

		chain.doFilter(req, resp);

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
