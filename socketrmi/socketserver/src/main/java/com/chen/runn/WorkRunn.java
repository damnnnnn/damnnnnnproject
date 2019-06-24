package com.chen.runn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

import com.chen.action.ActionParse;

public class WorkRunn implements Runnable
{

	private Socket socket;
	
	private Properties  p;

	public WorkRunn(Socket socket,Properties  p)
	{

		this.socket = socket;
		this.p=p;

	}

	public void run()
	{
		// TODO Auto-generated method stub
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String clientOperatorName = br.readLine();

			System.out.println("客户端的动作名称为:" + clientOperatorName);

			// 客户机和服务器有一定的约定的处理的规则.
			// 动作名称,编号，金额
			// 动作名称,歌曲名称

			new ActionParse().parseClientAction(socket,clientOperatorName,p);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
