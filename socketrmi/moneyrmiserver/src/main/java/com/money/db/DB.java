package com.money.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.chen.model.Students;

public class DB
{
	private Connection conn;

	public DB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/d_test_one", "root", "123456");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String modifyMoney(String name, double money)
	{
		String sql = "update  t_student  set  money=money+?  where sname=?";

		String dbmsg = "";

		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, money);
			pstmt.setString(2, name);

			pstmt.executeUpdate();

			Students s = this.queryByNameInfo(name);

			dbmsg = s.getSname() + ",操作的金额为:" + money + ",现在的金额为:" + s.getMoney() + ",奖金的信息为-->"
					+ ((s.getBonus() == 0.0) ? "没有奖金" : s.getBonus() + "");
			

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if (null != conn)
			{
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return dbmsg;
	}

	public Students queryByNameInfo(String name)
	{
		String sql = "select  *  from  t_student  where sname=?";
		Students s = new Students();
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{

				s.setSid(rs.getInt(1));
				s.setSname(rs.getString(2));
				s.setSsex(rs.getString(4));
				s.setSbirthday(rs.getString(5));
				s.setMoney(rs.getDouble(6));

				if (null != rs.getString(7) || !"".equals(rs.getString(7)))
				{
					s.setBonus(rs.getDouble(7));
				}

			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return s;

	}
	
//	public static void main(String[] args)
//	{
//		  DB db  = new DB();
//		  String  msg=db.modifyMoney("陈明", -100);
//		  System.out.println(msg);
//	}
}