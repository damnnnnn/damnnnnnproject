package com.lixin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.lixin.model.Depts;
//initĬ�������û�����ʱ����
//��web.xml��    <load-on-startup>0</load-on-startup>,init�ͻ���������������������
import com.lixin.task.MenuDataTask;

public class DeptAction extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

////		/* ��������������ַ */
//		resp.setHeader("Access-Control-Allow-Origin", "*");
////		/* �����������󷽷�GET, POST, HEAD �� */
//		resp.setHeader("Access-Control-Allow-Methods", "GET, POST,DELETE,PUT");
////		/* ����Ԥ�������Ļ���ʱ�� (s) */
//		resp.setHeader("Access-Control-Max-Age", "100");
////		/* ������������ͷ */
//		resp.setHeader("Access-Control-Allow-Headers", "*");
////		/* �Ƿ�Я��cookie */
//		resp.setHeader("Access-Control-Allow-Credentials", "false");
		
		
		System.out.println(req.getMethod());
		if(req.getMethod().equals("POST"))
		{
			
		
			System.out.println("DeptAction  is    doPost  start...");

			// ��ô�ж���PC����������󣬻����ƶ�������
			String headsInfo = req.getHeader("user-agent");
			System.out.println("headsInfo-->" + headsInfo);

			List<Depts> lists = new ArrayList<Depts>();
			Depts d1 = new Depts();
			d1.setDid(100);
			d1.setDname("������");
			d1.setDphone("8888");
			d1.setDlocation("�Ͼ�");

			Depts d2 = new Depts();
			d2.setDid(101);
			d2.setDname("������");
			d2.setDphone("9999");
			d2.setDlocation("����");

			lists.add(d1);
			lists.add(d2);

			if (headsInfo.toLowerCase().contains("android") || headsInfo.toLowerCase().contains("iphone")) {
				System.out.println("�ֻ��˵�����");
				
				String  strs=JSON.toJSONString(lists);
				System.out.println(strs);
				
				PrintWriter  pw=resp.getWriter();
				pw.println(strs);
				pw.flush();
				pw.close();
				
				

			} else {
				System.out.println("pc�����������");

				req.setAttribute("datas", lists);

				req.getRequestDispatcher("main.jsp").forward(req, resp);
			}

		}

	
	}



}
