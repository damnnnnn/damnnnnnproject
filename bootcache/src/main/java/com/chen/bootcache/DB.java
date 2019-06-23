package com.chen.bootcache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DB
{

	private Connection conn;

	static Properties p = new Properties();

	static// 最先执行，只执行一次
	{
		try
		{
			FileInputStream in = new FileInputStream("./src/main/resources/application.properties");
			p.load(in);

			System.out.println("初始化一次");

		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DB()
	{
		try
		{
			Class.forName(p.getProperty("damnnn.driver"));

			conn = DriverManager.getConnection(p.getProperty("damnnn.url"), p.getProperty("damnnn.username"),
					p.getProperty("damnnn.password"));

			System.out.println(conn);
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

	// 通用
	public List<Map<String, Object>> queryAllDatas(String tableName)
	{
		String sql = "SELECT  *     FROM   " + tableName;

		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();

		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next())
			{
				Map<String, Object> lineMaps = new HashMap<String, Object>();

				for (int i = 1; i <= rsmd.getColumnCount(); i++)
				{
					lineMaps.put(rsmd.getColumnName(i), rs.getObject(i));
				}

				lists.add(lineMaps);
			}

		} catch (Exception e)
		{
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

		return lists;

	}

	// 局限
	public List<Menu> queryMenuDatas(String tableName)
	{

		String sql = "SELECT  * FROM  " + tableName;
		List<Menu> lists = new ArrayList<Menu>();
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				Menu menu = new Menu();
				menu.setId(rs.getInt(1));
				menu.setMname(rs.getString(2));

				lists.add(menu);
			}

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

		return lists;
	}
}

