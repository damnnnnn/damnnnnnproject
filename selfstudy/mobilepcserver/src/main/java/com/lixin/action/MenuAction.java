package com.lixin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.lixin.dao.MenuDao;
import com.lixin.model.CMenu;
import com.lixin.model.FMenu;
import com.lixin.model.GridMenu;
import com.lixin.task.MenuDataTask;

public class MenuAction extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("DeptAction  is  init  start.... ");

		// 表示web应用服务器
		ServletContext sc = config.getServletContext();

		String cpath = sc.getRealPath("/WEB-INF/cmenu.txt");

		System.out.println("cpath-->" + cpath);

		// 启动定时备份

		// 启动定时任务，2019-07-16 8:55:00启动入库任务
		Timer timer = new Timer();

		String strDate = "2019-08-16 09:14:00";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date startDate = null;
		try {
			startDate = sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		timer.scheduleAtFixedRate(new MenuDataTask(cpath), startDate, 24 * 60 * 60 * 1000);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MenuAction  is  doPost  start.... ");
		
		resp.setCharacterEncoding("UTF-8");

////	/* 允许跨域的主机地址 */
//		resp.setHeader("Access-Control-Allow-Origin", "*");
////	/* 允许跨域的请求方法GET, POST, HEAD 等 */
//		resp.setHeader("Access-Control-Allow-Methods", "GET, POST,DELETE,PUT");
////	/* 重新预检验跨域的缓存时间 (s) */
//		resp.setHeader("Access-Control-Max-Age", "100");
////	/* 允许跨域的请求头 */
//		resp.setHeader("Access-Control-Allow-Headers", "*");
////	/* 是否携带cookie */
//		resp.setHeader("Access-Control-Allow-Credentials", "false");

		// 怎么判断是PC浏览器的请求，还是移动的请求
		String headsInfo = req.getHeader("user-agent");
		System.out.println("headsInfo-->" + headsInfo);
		
		
		String  operatorName=req.getParameter("operator");
		System.out.println("operatorName-->"+operatorName);
		
		
		if(operatorName.equals("querygridmenu"))
		{
			
			System.out.println("查询宫格菜单数据");
			
			MenuDao menudao = new MenuDao();
			List<GridMenu>   glists=menudao.getGridMenu();
			
			String  jsonStrs=JSON.toJSONString(glists);
			System.out.println(jsonStrs);
			
			//发送给客户端
			PrintWriter  pw=resp.getWriter();
			pw.println(jsonStrs);
			pw.flush();
			pw.close();
			
			
			
			
		}
		else  if(operatorName.equals("listmenu"))
		{
			System.out.println("查询折叠菜单数据");
			
			MenuDao dao = new MenuDao();
			List<FMenu> lists = dao.getMenusData();
			
			//设置发送的数据中文不乱码
			resp.setCharacterEncoding("UTF-8");

//			for (FMenu f : lists) {
//				System.out.println("父-->" + f.getFname());
	//
//				for (CMenu c : f.getClists()) {
//					System.out.println(c.getCname());
	//
//				}
//			}

			if (headsInfo.toLowerCase().contains("android") || headsInfo.toLowerCase().contains("iphone")) {
				System.out.println("移动端");
				
				String  jsonStrs=JSON.toJSONString(lists);
				System.out.println(jsonStrs);
				
				//发送给客户端
				PrintWriter  pw=resp.getWriter();
				pw.println(jsonStrs);
				pw.flush();
				pw.close();
				
				
			} else {
				System.out.println("PC端");
				
				
				String  jsonStrs=JSON.toJSONString(lists);
				System.out.println(jsonStrs);
				
				//发送给客户端
				PrintWriter  pw=resp.getWriter();
				pw.println(jsonStrs);
				pw.flush();
				pw.close();
				
				
			}
		}

	
	}

}
