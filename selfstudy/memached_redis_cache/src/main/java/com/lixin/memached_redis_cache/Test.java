package com.lixin.memached_redis_cache;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class Test
{
	
	public static void main(String[] args) 
	{
		try
		{
			MemcachedClient mclient = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));

			System.out.println("连接成功--》"+mclient);
			mclient.set("njweather",20, "南京");
			System.out.println(mclient.get("njweather"));
			mclient.shutdown();
		}
	    catch(Exception e)
		{
	    	e.printStackTrace();
		}
	}

}
