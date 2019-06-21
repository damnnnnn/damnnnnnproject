package com.chen.javasocket.code3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserMsgThread extends Thread
{

	private Socket socket;

	public UserMsgThread(Socket socket)
	{

		this.socket = socket;
	}

	public void run()
	{
		try
		{
			// 1.服务器在控制台接受客户端消息
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String ServerMsg = br.readLine();

			System.out.println(Thread.currentThread().getName() + "线程,服务器接受的消息为::" + ServerMsg);

			System.out.println(
					Thread.currentThread().getName() + "线程,服务器接受消息完毕--------------------------------------------");

			System.out.println(Thread.currentThread().getName() + "线程,服务器开始在控制台通过键盘构建回复消息:");

			// 2.服务器构建消息
			BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));

			String ServerSendMsg = sbr.readLine();

			System.out.println(Thread.currentThread().getName() + "线程,服务器通过键盘在控制台构建的消息为:" + ServerSendMsg);

			System.out.println(
					Thread.currentThread().getName() + "线程,服务器构建消息完毕--------------------------------------------");

			// 3.服务器发送消息给客户机
			PrintWriter pw = new PrintWriter(socket.getOutputStream());

			// 服务器接受消息的标识为:最后写入/r/n,代表这个消息输入完毕
			// 服务器才开始读取这条消息
			// pw.write(clientMsg+"\r\n");

			pw.println(ServerSendMsg);

			// 立即刷新缓冲区，向目标立即写入
			pw.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}