package com.money.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMoney  extends  Remote
{
	public  String  countMoney(String  name,double  money ) throws  RemoteException;
	 
}
