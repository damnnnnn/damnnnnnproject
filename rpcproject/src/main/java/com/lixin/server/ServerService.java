package com.lixin.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.lixin.interfaces.IUserDao;
import com.lixin.service.UserDaoServiceImpl;

public class ServerService
{
	
	public static void main(String[] args)
	{
		
		System.setProperty("java.rmi.server.hostname","192.168.255.207");
		System.out.println("远程的服务数据接口准备启动发布....");
		
		
	
		try
		{
			//1.首先注册这个远程服务，先声明服务类
			IUserDao  dao = new UserDaoServiceImpl();
			
			//2.本地注册这个服务的发布的端口
			LocateRegistry.createRegistry(8300);
			
			//3.绑定发布的服务的url,和服务类
			Naming.bind("rmi://localhost:8300/userdatas", dao);
			
			
			System.out.println("数据服务的远程发布成功....");
			
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
