package com.chen.socketthreadpool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

import com.chen.threadPool.ThreadPool;

/**
 * Hello world!
 *
 */
public class App {
	private static Properties p = null;
	private static ThreadPool pool =null;
	static {
		FileInputStream fin = null;
		try {
			fin = new FileInputStream("./src/main/resources/threadpool.properties");
			p = new Properties();
			p.load(fin);
			int poolNumber = Integer.parseInt(p.getProperty("poolNum"));
			ThreadPool pool = new ThreadPool(poolNumber);

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(null!=fin)
			{
				try {
					fin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	public static Vector<String> taskLists = new Vector<String>();
	public void createTask()
	{
		//重复创建任务
		while(true)
		{
			
			//扫描键盘打的字
			String taskName = new Scanner(System.in).next();
			taskLists.add(taskName);
			pool.executeTask(taskLists.get(0));
		}
	}

	public static void main(String[] args) {
		App app = new App();
		app.createTask();
	}
}
