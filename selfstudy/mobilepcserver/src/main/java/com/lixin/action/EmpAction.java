package com.lixin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lixin.aop.PhoneAop;
import com.lixin.dao.IUserDao;
import com.lixin.dao.UserDao;
import com.lixin.model.UserInfo;
import com.lixin.proxy.PhoneProxy;

public class EmpAction extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EmpAction  is  doPost  start.... ");

////		/* 允许跨域的主机地址 */
//		resp.setHeader("Access-Control-Allow-Origin", "*");
////		/* 允许跨域的请求方法GET, POST, HEAD 等 */
//		resp.setHeader("Access-Control-Allow-Methods", "GET, POST,DELETE,PUT");
////		/* 重新预检验跨域的缓存时间 (s) */
//		resp.setHeader("Access-Control-Max-Age", "100");
////		/* 允许跨域的请求头 */
//		resp.setHeader("Access-Control-Allow-Headers", "*");
////		/* 是否携带cookie */
//		resp.setHeader("Access-Control-Allow-Credentials", "false");
//
//		resp.setCharacterEncoding("UTF-8");

		//手机号码和员工姓名不是经常变化的数据，应从性能考虑，放入缓存，减轻对数据库访问的压力
//		UserDao userdao = new UserDao();
//		List<UserInfo> lists = userdao.queryUserInfo();
		
		IUserDao  userdao = new PhoneProxy(new UserDao() , new PhoneAop());
		List<UserInfo> lists =userdao.queryUserInfo();
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		sb.append("<phones>");

		for (UserInfo u : lists) {
			sb.append("<phone>");
			sb.append("<name>" + u.getUname() + "</name>" + "<telphone>" + u.getPhone() + "</telphone>");
			sb.append("</phone>");
		}

		sb.append("</phones>");

		// resp.setContentType("text/xml");

//		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
//		sb.append("<phones>");
//		sb.append("<phone>13913321086</phone>");
//		sb.append("</phones>");
		PrintWriter pw = resp.getWriter();

		pw.println(sb.toString());
		pw.flush();

	}

}
