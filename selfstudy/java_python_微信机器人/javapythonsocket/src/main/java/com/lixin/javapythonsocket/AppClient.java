package com.lixin.javapythonsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class AppClient 
{
	public AppClient()
	{
		System.out.println("java客户机正在启动....");
		
		while(true)
		{
			try
			{
				Socket  socket   = new Socket("127.0.0.1",9999);
				
				System.out.println("客户机和python服务器端建立了连接");
				
				System.out.println("请java构建消息");
				
				
				Scanner  scanner   = new Scanner(System.in);
				String  msg =scanner.next();
				
				System.out.println("java客户端构建的消息为:"+msg);
				
				//发消息给python
				PrintWriter  pw = new PrintWriter(socket.getOutputStream());
				pw.println(msg);
				pw.flush();
				
				
				//接受python消息
				BufferedReader  br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String  pythonMsg =br.readLine();
				
				System.out.println("python过来的消息为:"+pythonMsg);
				
				
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
    public static void main( String[] args )
    {
       new AppClient();
    }
}
