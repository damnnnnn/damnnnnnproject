package com.lixin.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

//rmi  remote  method
//必须继承Remote接口
public interface IUserDao   extends  Remote
{
	//远程方法
	public  String    queryDatas()  throws  RemoteException;

}
