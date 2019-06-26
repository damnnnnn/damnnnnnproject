package com.chen.threadPool;

import java.util.Vector;

import com.chen.runn.WorkRunnable;
import com.chen.socketthreadpool.App;

public class ThreadPool {
	//设置线程池大小
	private int poolNum;
	//创建一个线程池，实际就是一个集合
	static Vector<Runnable> runnVectors;
	//构造函数传参
	public ThreadPool(int poolNum)
	{
		this.poolNum=poolNum;
		//把集合固定大小(实例化了线程池的大小)
		this.runnVectors = new Vector<Runnable>(poolNum);
		//用for循环把线程放入集合
		for(int i = 0;i<this.runnVectors.capacity();i++)
		{
			//创建线程
			WorkRunnable wr = new WorkRunnable();
			//将线程放入池中
			this.runnVectors.add(wr);
			//启动线程
			wr.start();
		}
		
	}
	//创建一个完成任务的方法
	public static void executeTask(String taskName)
	{
//		System.out.println(taskName);
		
		//提升i的作用域，下面可以使用
		int i = 0;
		for(;i<runnVectors.capacity();i++)
		{
			//获取池中的线程，观察他们的状态
			WorkRunnable wr = (WorkRunnable) runnVectors.get(i);
			System.out.println(wr.getName()+"线程现在的状态为:"+(wr.isIsflag()?"忙碌":"空闲"));
			
			//让空闲的出来干活
			if(!wr.isIsflag())
			{
				wr.setIsflag(true);
				wr.setTaskName(taskName);
				//移除任务列表的第一项任务
				App.taskLists.remove(0);
				break;
			}
			
		}
		//判断有没有空闲线程
		if(i==runnVectors.capacity())
		{
			System.out.println("线程池中没有空闲的线程。");
		}
	}

}
