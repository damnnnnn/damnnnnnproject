package com.lixin.tuling2;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class DataCacheAop
{

	private MemcachedClient mclient;

	public DataCacheAop()
	{
		try
		{
			mclient = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Object getObject(String key)
	{
		return this.mclient.get(key);
	}

	public void setObject(String key, Object obj)
	{
		this.mclient.set(key, 0, obj);
	}

	public void setObject(String key, int time, Object obj)
	{
		this.mclient.set(key, time, obj);
	}

	public MemcachedClient getMclient()
	{
		return mclient;
	}

	public void setMclient(MemcachedClient mclient)
	{
		this.mclient = mclient;
	}

}
