package com.lixin.thread.two;

public class Test1
{

	private int num = 0;

	public synchronized void add()
	{
		num++;// num赋值，num+1,再赋值
	}

	public static void main(String[] args)
	{

		final Test1 t = new Test1();

		for (int i = 0; i < 10; i++)
		{

			new Thread()
			{

				public void run()
				{

					for (int j = 0; j < 1000; j++)
					{
						t.add();
					}

				}

			}.start();
		}

		// 保证上面的线程执行完毕
		while (Thread.activeCount() > 1)
			Thread.yield();

		System.out.println("值为:" + t.num);// 10*1000=10000

	}

}
