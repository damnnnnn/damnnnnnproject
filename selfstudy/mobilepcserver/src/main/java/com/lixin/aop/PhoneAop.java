package com.lixin.aop;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class PhoneAop {

	private MemcachedClient mcacheClient;

	public PhoneAop() {
		try {
			mcacheClient = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));

			System.out.println("连接成功"+mcacheClient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public  Object   getPhoneCache()
	{
		return  this.mcacheClient.get("empphone");
	}
	
	public  void  setPhoneCache(Object  obj)
	{
		this.mcacheClient.set("empphone", 2*60, obj);
	}

}
