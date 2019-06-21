package com.chen.fileio.model;
//套接字 让另一台机器下载
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerMusic {
	
	public static void main(String[] args) {
		System.out.println("下载铃音的服务器启动");
		
		try {
			ServerSocket  serverSocket  = new ServerSocket(8700);
			Socket  socket=serverSocket.accept();
			
			System.out.println("建立连接"+socket);
			
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			List<Music>   lists=(List<Music>)in.readObject();
			
			for(Music  m:lists)
			{
				System.out.println(m.getSname()+"\t"+m.getSlink());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
