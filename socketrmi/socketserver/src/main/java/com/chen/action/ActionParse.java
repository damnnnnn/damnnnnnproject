package com.chen.action;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import com.money.interfaces.IMoney;
import com.music.interfaces.IMusic;

public class ActionParse
{

	public void parseClientAction(Socket socket, String opername, Properties p)
	{

		// 字符串分割函数
		String[] ops = opername.split(",");
		String execMessage = "";

		// 分布式，把不同的任务分派给不同的机器去处理相对应的业务

		if (ops[0].contains("money"))
		{
			System.out.println("客户端是请求处理费用的请求");

			execMessage = execMoney(ops[0], ops[1], ops[2], p);

		} else if (ops[0].contains("music"))
		{
			System.out.println("客户端是请求处理音乐的请求");

			execMessage = execMoney(ops[0], ops[1], p);

		}

		try
		{
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println(execMessage);
			pw.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private String execMoney(String opname, String name, Properties p)
	{

		System.out.println("开始远程调用音乐接口");

		String musicResult = "";
		try
		{
			// 去rm服务器上查找服务
			IMusic musicAction = (IMusic) Naming.lookup("rmi://" + p.getProperty("server.remoteip") + ":"
					+ p.getProperty("server.remotemusicport") + "/" + opname);

			musicResult = musicAction.queryMusicInfo(name);

			System.out.println("音乐远程方法调用的结果为:" + musicResult);

		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return musicResult;
	}

	private String execMoney(String opname, String name, String smoney, Properties p)
	{
		System.out.println("开始远程调用费用接口");

		String moneyResult = "";
		try
		{
			// 去rmi服务器上查找服务
			IMoney moneyAction = (IMoney) Naming.lookup("rmi://" + p.getProperty("server.remoteip") + ":"
					+ p.getProperty("server.remoteport") + "/" + opname);

			moneyResult = moneyAction.countMoney(name, Double.parseDouble(smoney));

			System.out.println("远程方法调用的结果为:" + moneyResult);

		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return moneyResult;
	}

}
