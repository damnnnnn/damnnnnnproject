package com.chen.javasocket.code2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain
{

	private Socket socket = null;

	public ClientMain()
	{
		System.out.println("客户机启动请求...");

		while (true)
		{

			try
			{

				// 1.建立连接
				socket = new Socket("192.168.255.44", 8700);
				 //socket = new Socket("192.168.255.207", 8700);
				//socket = new Socket("192.168.255.63", 8700);
				System.out.println("客户机请求成功," + socket);

				System.out.println("请客户机构建要发送的消息:");
				// 2.通过键盘输入，构建输入的消息
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				String clientMsg = br.readLine();

				System.out.println("客户端通过键盘构建的消息为:" + clientMsg);

				// 3.向服务器发送消息
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				pw.println(clientMsg);
				pw.flush();

				// 4.接受服务器消息
				BufferedReader cbr = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String receiverClientMsg = cbr.readLine();

				System.out.println("客户端接受的消息为:" + receiverClientMsg);

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
		new ClientMain();
	}
}

