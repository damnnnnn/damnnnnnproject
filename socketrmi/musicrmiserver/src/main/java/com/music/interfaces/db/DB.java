package com.music.interfaces.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB
{

	private Connection conn;

	public DB()
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

	public String queryByNameMusic(String musicName)
	{

		String sql = "select   spic,slink,slrc  from  t_musicinfo  where sname=?";

		String msg = "";

		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, musicName);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				msg = rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3);
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

		return msg;

	}

}
