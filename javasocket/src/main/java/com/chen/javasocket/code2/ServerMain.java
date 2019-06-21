package com.chen.javasocket.code2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain
{

	// 创建ServerSocket对象
	private ServerSocket serverSocket = null;

	private Socket socket = null;

	public ServerMain()
	{
		System.out.println("服务器端正在启动....");

		try
		{
			// 在8700端口监听，有没有客户机请求
			serverSocket = new ServerSocket(8700);
			
			//服务器始终保持对客户端的监听
			while(true)
			{
				// 1.如果有客户机，另外一个进程，发起了请求
				socket = serverSocket.accept();

				System.out.println("建立了连接，握手成功," + socket);

				// 2.接受客户端传递的数据

				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String receiverMsg = br.readLine();

				System.out.println("服务器接受的消息为:" + receiverMsg);
				
				
				
				//3.服务器要构建消息，下发消息给客户机
				System.out.println("请服务器构建要发送的消息:");
				//2.通过键盘输入，构建输入的消息
				BufferedReader  sbr = new BufferedReader(new InputStreamReader(System.in));
				String ServerMsg  =sbr.readLine();
				System.out.println("服务器通过键盘构建的消息为:"+ServerMsg);
				
				
			   //4.下发消息给客户机
				PrintWriter  pw = new PrintWriter(socket.getOutputStream());
				pw.println(ServerMsg);
				pw.flush();
				
			}

		

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new ServerMain();
	}

}
