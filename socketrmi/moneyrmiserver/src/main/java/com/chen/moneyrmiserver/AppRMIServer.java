package com.chen.moneyrmiserver;

/**
 * Hello world!
 *
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import com.money.interfaces.IMoney;
import com.money.service.MoneyServiceImpl;

/**
 * Hello world!
 *
 */
public class AppRMIServer
{
	private static Properties p;

	static
	{
		p = new Properties();
		try
		{
			FileInputStream in = new FileInputStream("./src/main/resources/application.properties");

			p.load(in);

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

	public AppRMIServer()
	{

		System.out.println("RMI结算费用的服务已经发布**************");

//		 System.setProperty("java.rmi.server.hostname",
//		 p.getProperty("server.remoteip"));

		try
		{

			// 提供远程服务的类
			IMoney moneyService = new MoneyServiceImpl();

			// 服务的端口注册
			LocateRegistry.createRegistry(Integer.parseInt(p.getProperty("server.remoteport")));

			// 注册绑定服务
			Naming.bind("rmi://" + p.getProperty("server.remoteip") + ":"
					+ Integer.parseInt(p.getProperty("server.remoteport")) + "/" + p.getProperty("server.operator"),
					moneyService);

			System.out.println("RMI结算费用的服务发布成功------------success"+","+p.getProperty("server.remoteport"));
		} catch (NumberFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		new AppRMIServer();
	}
}
