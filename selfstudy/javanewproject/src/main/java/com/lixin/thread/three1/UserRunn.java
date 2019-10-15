package com.lixin.thread.three1;

import java.util.Random;

/**
 * volatile 也是保证线程安全，保证线程的可见性，线程的值是最新的,不能保证非原子操作。
 * 
 * synchronized 也是保证线程安全，++,--等等，以性能换安全。
 * 
 * ThreadLocal 也是保证线程安全，以内存换安全。？
 * 
 * lock 锁。？
 * 
 * java.util.concurrent安全包 安全工具类。？
 * 
 * 
 * 
 * @author lenovo
 *
 */
//synchronized  保证多线程情况下数据安全，其实是以性能换安全。
public class UserRunn implements Runnable
{

	private User u;

	public void run()
	{
		// TODO Auto-generated method stub
		String ThreadName = Thread.currentThread().getName();
		System.out.println(ThreadName + "--->run");

		synchronized (this)
		{

			u = new User();

			Random r = new Random();
			int age = r.nextInt(100);

			System.out.println(ThreadName + ",set  before-->" + age);

			u.setAge(age);

			System.out.println(ThreadName + ",set  1  after-->" + u.getAge());

			try
			{
				Thread.sleep(2000);
				System.out.println(ThreadName + "->,set 2  after-->" + u.getAge());
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args)
	{
		UserRunn ur = new UserRunn();

		Thread t1 = new Thread(ur, "用户one");
		Thread t2 = new Thread(ur, "用户two");
		t1.start();
		t2.start();
	}

}
