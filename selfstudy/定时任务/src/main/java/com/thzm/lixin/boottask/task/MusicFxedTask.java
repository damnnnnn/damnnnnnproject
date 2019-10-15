package com.thzm.lixin.boottask.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MusicFxedTask {
	
	//秒 分  时  天  月 星期   年
	@Scheduled(cron="0/6 44 9 * * ?")
	public void  exec()
	{
		System.out.println(Thread.currentThread().getName()+"\t"+new  Date());
	
	}

}
