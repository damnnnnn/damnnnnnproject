package com.lixin.memached_redis_cache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	{
		try
		{
			MemcachedClient client = new MemcachedClient(
					new InetSocketAddress("127.0.0.1", 11211));

			System.out.println("连接成功");

			// 设置值  第2个值秒  0
			Future f = client.set("username", 60, "赵玉斌");
	
			// 获取值 replace  append
			System.out.println(client.get("username"));
			client.shutdown();

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
