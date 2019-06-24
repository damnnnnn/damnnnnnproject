package com.money.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.money.db.DB;
import com.money.interfaces.IMoney;

public class MoneyServiceImpl extends UnicastRemoteObject implements IMoney
{

	public MoneyServiceImpl() throws RemoteException
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public String countMoney(String name, double money) throws RemoteException
	{

		System.out.println("MoneyServiceImpl  is   countMoney  start...");
		System.out.println("rmiserver:" + name + "," + money);
		DB db = new DB();
		String msg = db.modifyMoney(name, money);
		System.out.println(msg);
		// TODO Auto-generated method stub
		return msg;
	}

}
