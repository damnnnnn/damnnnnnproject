package com.chen.socketserver;

/**
 * Hello world!
 *
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import com.chen.runn.WorkRunn;

/**
 * Hello world!
 *
 */
public class AppServer
{
	private static Properties p;

	static
	{
		p = new Properties();
		try
		{
			FileInputStream in = new FileInputStream("./src/main/resources/application.properties");

			p.load(in);

		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AppServer()
	{
		System.out.println("服务器端正在启动..........");

		try
		{
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(p.getProperty("server.port")));

			while (true)
			{
				Socket socket = serverSocket.accept();

				System.out.println("服务器监听到客户机的请求，建立了连接...");

				//两台rmiServer，一台处理扣费，一台负责查询音乐
				
				
				//现在不考虑服务器的对象的开销的压力，来一个请求，建立一个线程去处理客户端的请求
				new Thread(new WorkRunn(socket,p)).start();

			}
		} catch (NumberFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		new AppServer();
	}
}

