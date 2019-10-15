package com.lixin.tuling2;

import com.alibaba.fastjson.JSONObject;

import net.spy.memcached.MemcachedClient;

public class ProxyData implements IDataDao
{
	private IDataDao dao;

	private DataCacheAop datacacheaop;

	private MemcachedClient client;

	JSONObject jobj = null;

	public ProxyData(IDataDao dao, DataCacheAop datacacheaop)
	{
		this.dao = dao;
		this.datacacheaop = datacacheaop;
		this.client = this.datacacheaop.getMclient();

	}

	public Object queryDatas()
	{
		System.out.println("-------------------------------");

		if (null == this.datacacheaop.getObject("njweather"))
		{
			System.out.println("缓存中没有数据....");

			// 执行目标方法
			jobj = (JSONObject) this.dao.queryDatas();
			this.datacacheaop.setObject("njweather", jobj);
		} else
		{
			System.out.println("缓存中有数据***********");
			jobj = (JSONObject) this.datacacheaop.getObject("njweather");
		}
		// TODO Auto-generated method stub
		return jobj;
	}

}
