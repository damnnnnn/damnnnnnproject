package com.chen.runn;

import com.chen.socketthreadpool.App;
import com.chen.threadPool.ThreadPool;

public class WorkRunnable extends Thread {
	private boolean isflag;
	private String taskName;
	public void run() {
		//打印线程启动日志
		System.out.println(Thread.currentThread().getName()+"线程启动...");
		
		//线程不死 进入死循环
		while(true)
		{
			synchronized (this) {
				if(isflag)
				{
					System.out.println(Thread.currentThread().getName()+"线程当前有任务，任务为:"+this.taskName);
					try {
						//假设线程做这个任务花了30s.
						Thread.sleep(30*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"线程已经完成"+this.taskName+"这个任务");
					//该线程完成任务，将标识位改为false
					this.setIsflag(false);
				}
				//空闲的线程
				else {
					try {
					System.out.println(Thread.currentThread().getName()+"在此被阻塞，没有任务");
					if(App.taskLists.size()>0)
					{
						String tname = App.taskLists.get(0);
						System.out.println("任务队列中还有未完成的任务:"+tname);
						ThreadPool.executeTask(tname);
						System.out.println(Thread.currentThread().getName()+"线程去执行"+tname+"任务");
					}
					else {
						
							//没有任务了，线程在此阻塞
							
								this.wait();
								//System.out.println(Thread.currentThread().getName()+"在此阻塞");
					}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						
					}
				}
			}
		}
	

	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
		synchronized (this) {
			//有任务
			if(this.isflag)
			{
				//唤醒
				this.notify();
			}
		}
	}
	public boolean isIsflag() {
		return isflag;
	}
	public void setIsflag(boolean isflag) {
		this.isflag = isflag;
		synchronized (this) {
			if(isflag) {
				this.notify();
			}
		}
	}
	
}
