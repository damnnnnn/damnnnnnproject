package com.lixin.tuling2;

import com.alibaba.fastjson.JSONObject;

public class Test
{
	public static void main(String[] args)
	{
		IDataDao dao = new ProxyData(new DataDaoImpl(), new DataCacheAop());
		Object  obj=dao.queryDatas();
		System.out.println("-->"+obj);
		JSONObject  jobj=(JSONObject)obj;
		System.out.println(jobj.get("text"));
	}
}
