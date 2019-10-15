package com.lixin.tuling1;

import java.util.HashMap;

import cn.hutool.http.HttpUtil;

public class Test1
{

	public static void main(String[] args)
	{
		HashMap<String, Object> paramsMaps = new HashMap<String, Object>();
		paramsMaps.put("key", "");
		paramsMaps.put("info", "南京天气");

		String result = HttpUtil.post("http://www.tuling123.com/openapi/api", paramsMaps);
		System.out.println(result);
	}

}
