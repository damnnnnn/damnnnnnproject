package com.chen.socketclient;

/**
 * Hello world!
 *
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;

public class AppClient
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

	public AppClient()
	{
		System.out.println("启动客户机，准备和服务器建立连接");

		while (true)
		{

			try
			{
				Socket socket = new Socket(p.getProperty("server.ip"), Integer.parseInt(p.getProperty("server.port")));

				System.out.println("客户机和服务器建立了连接.....");

				System.out.println("请输入任务名称:");

				Scanner s = new Scanner(System.in);

				String taskName = s.next();

				System.out.println("客户机输入的任务的名称为:" + taskName);

				// 客户机把任务发送个服务器
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				pw.println(taskName);
				pw.flush();

				// 接受socket服务器返回的值
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String receiverMsg = br.readLine();

				System.out.println("客户机接受的服务器处理的消息为:" + receiverMsg);

			} catch (NumberFormatException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args)
	{
		AppClient client = new AppClient();
	}
}

