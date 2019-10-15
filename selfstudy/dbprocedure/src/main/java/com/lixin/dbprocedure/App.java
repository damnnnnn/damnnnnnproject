package com.lixin.dbprocedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Hello world!
 *
 */
public class App
{

	private Connection conn;

	public App()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "student", "student");
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

	public void callProcedure(String name, String pwd)
	{

		String sql = "{call p_login(?,?,?)}";

		try
		{
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1, name);
			cstmt.setString(2, pwd);

			cstmt.registerOutParameter(3, Types.VARCHAR);

			cstmt.execute();

			String result = cstmt.getString(3);

			System.out.println(result);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void callProcedure(String dname)
	{

		String sql = "{call p_queryempmen(?,?)}";

		try
		{
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1, dname);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);

			cstmt.execute();

			ResultSet rs = (ResultSet) cstmt.getObject(2);

			while (rs.next())
			{
				System.out.println(rs.getString(2));
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void callProcedureD(String tableName)
	{

		String sql = "{call p_count(?,?)}";

		try
		{
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1, tableName);
			cstmt.registerOutParameter(2, Types.INTEGER);

			cstmt.execute();

			int count = cstmt.getInt(2);

			System.out.println(count);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void callfunction(String tableName)
	{

		String sql = "{?=call f_querycount(?)}";

		try
		{
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, tableName);

			cstmt.execute();

			int count = cstmt.getInt(1);

			System.out.println(count);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void main(String[] args)
	{
		App app = new App();
		// app.callProcedure("王长林", "123456");
		//app.callProcedure("生产部");
		app.callfunction("t_emp");
		//app.callProcedureD("t_dept");
	}
}
