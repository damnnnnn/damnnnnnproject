package com.lixin.tuling1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

import net.spy.memcached.MemcachedClient;

public class Test
{

	public static void main(String[] args) throws MalformedURLException
	{
		String turl = "http://www.tuling123.com/openapi/api";
		String key = "";
		String info = "南京天气";

		String linkurl = turl + "?key=" + key + "&info=" + info;

		URL url = new URL(linkurl);

		try
		{
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				InputStream in = conn.getInputStream();

				BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

				String line = "";

				StringBuilder sb = new StringBuilder();

				while ((line = br.readLine()) != null)
				{
					sb.append(line);
				}
				System.out.println(sb.toString());

				JSONObject jobj = JSONObject.parseObject(sb.toString());
				String weatherInfo = (String) jobj.get("text");
				System.out.println(weatherInfo);

				MemcachedClient mclient = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));

				System.out.println(mclient);
				mclient.set("aa",60, jobj);
				
				JSONObject jobj11=(JSONObject)mclient.get("aa");
				System.out.println("-->"+jobj11.get("text"));

				mclient.shutdown();

			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
