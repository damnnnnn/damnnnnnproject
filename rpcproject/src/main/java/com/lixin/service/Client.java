package com.lixin.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.lixin.interfaces.IUserDao;
//java命名目录服务
public class Client
{
	
	public static void main(String[] args)
	{

		try
		{
			//1.去远程服务器上查找提供的远程服务
			IUserDao  dao=(IUserDao)Naming.lookup("rmi://192.168.43.61:8300/userdatas");
		
		
			String   value= dao.queryDatas();
			System.out.println("远程方法获取的值为:"+value);
			
			
			
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
		
		
	}

}
