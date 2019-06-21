package com.lixin.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.lixin.interfaces.IUserDao;

public class UserDaoServiceImpl  extends UnicastRemoteObject implements IUserDao
{

	public UserDaoServiceImpl() throws RemoteException
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public String queryDatas() throws RemoteException
	{
		System.out.println("UserDaoServiceImpl  is   queryDatas  start...");
		
		// TODO Auto-generated method stub
		return "远程呼叫的结果的返回_天汇智码_智码堂技术中心";
	}

}
