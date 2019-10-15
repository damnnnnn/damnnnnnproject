package com.lixin.tuling2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class DataDaoImpl implements IDataDao
{

	public Object queryDatas()
	{
		JSONObject jobj = null;
		// TODO Auto-generated method stub
		String turl = "http://www.tuling123.com/openapi/api";
		String key = "";
		String info = "南京天气";

		String linkurl = turl + "?key=" + key + "&info=" + info;

		try
		{

			URL url = new URL(linkurl);
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
				// System.out.println(sb.toString());

				jobj = JSONObject.parseObject(sb.toString());
				
				
				//String weatherInfo = (String) jobj.get("text");
				//System.out.println(weatherInfo);

			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobj;

	}

}
