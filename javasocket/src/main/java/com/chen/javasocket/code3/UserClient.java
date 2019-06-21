package com.chen.javasocket.code3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserClient
{
	
	private Socket  socket;
	public void  startCient()
	{
		System.out.println("客户机正在启动，请求服务器的服务...");
		
		
		while(true)
		{
			
			try
			{
				//客户机向那台服务器，端口发起请求
				socket = new Socket("127.0.0.1",8250);
				
				System.out.println("客户机请求成功，建立了连接:"+socket);
				
				System.out.println("客户机开始在控制台构建消息************");
				
				//1.客户机在控制台构建消息
				BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
				
				String  clientMsg =br.readLine();
				
				System.out.println("客户机通过键盘在控制台构建的消息为:"+clientMsg);
				
				System.out.println("客户端构建消息完毕--------------------------------------------");
				
				//2.把构建的消息传输给服务器端  
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				
				//服务器接受消息的标识为:最后写入/r/n,代表这个消息输入完毕
				//服务器才开始读取这条消息
				 // pw.write(clientMsg+"\r\n");
				
				 pw.println(clientMsg);
				
				//立即刷新缓冲区，向目标立即写入
				pw.flush();
				
				//3.客户机接受消息
                BufferedReader  cbr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String  clientReceiverMsg =cbr.readLine();
				
				System.out.println("客户机接受的消息为::"+clientReceiverMsg);
				
				System.out.println("客户机接受的消息为:接受消息完毕--------------------------------------------");
				
				
				
				
				
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
		UserClient  uc = new UserClient();
		uc.startCient();
	}

}