package com.chen.javasocket.code3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//服务器端启动，保持对客户机的监听(一个客户机变成N个客户机)

/**
 * 
 * 1. 1客户机和1个服务器，消息的应答正常。
 * 
 * 2.多个客户机和服务器应答，出现了阻塞现象，阻塞现象的测试????? 如何测试：1.服务器下发消息给客户机1，客户机1准备发消息
 * 2.又有一台客户机2和服务器建立连接，客户机2发消息给服务器，服务器不能接受。
 * 
 * 如何解决阻塞现象???????
 * 
 * @author lenovo
 *
 */
public class UserServer
{

	private ServerSocket serverSocket;

	private Socket socket;

	public void init()
	{
		System.out.println("服务端正在启动.....");

		try
		{

			// 保持在8250端口对客户机的监听
			serverSocket = new ServerSocket(8250);

			while (true)
			{
				// 如果有客户机请求服务器，在8250端口发起建立连接的请求
				// 保持对客户机监听并建立连接的是：主线程
				socket = serverSocket.accept();

				System.out.println(Thread.currentThread().getName() + "线程,建立了连接,客户机的socket信息-->" + socket);

				// 收发消息的还是主线程，造成阻塞；如何优化，保持收发的不能再是主线程，而是产生多个子线程保持收发。。。。*****
				//通过线程，解决了阻塞，但是线程的开销太大，后面继续优化，采用线程池？？？
				//1.解决方案 1.1自定义线程池  2.JDK内置线程池
				new UserMsgThread(socket).start();

			}

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		UserServer server = new UserServer();
		server.init();
	}

}
