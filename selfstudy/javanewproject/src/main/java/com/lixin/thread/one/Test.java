package com.lixin.thread.one;

//volatile轻量级的同步  Netty框架
public class Test extends Thread
{

	//volatile保证线程的可见性,同步最新的值，保证每个线程获取最新的值。
	//volatile 是可以保证线程的可见性，但不能保证非原子操作。
	//a++;
	// 怎么保证线程的可见性，也就是保证每个线程获取最新的值，也就是可见性
	private  volatile  boolean isRunning = true;

	public void run()
	{
		System.out.println(Thread.currentThread().getName() + "-->线程进入到run()方法....");

		while (isRunning == true)
		{
			// System.out.println(Thread.currentThread().getName() + "进入到循环");
		}

		System.out.println("线程运行停止了.....");
	}

	public static void main(String[] args)
	{
		Test t = new Test();
		t.start();
		try
		{
			Thread.sleep(1000);
			t.setRunning(false);

			System.out.println("线程的标识位为false");
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

}
