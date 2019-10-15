package com.thzm.lixin.boottask.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.thzm.lixin.boottask.dao.IMusicDao;


@Component
public class MusicTask {

	//private MusicMysqlDao  dao  = new MusicMysqlDao();
	
	//给这个接口绑定子类对象，请问是不是绑定死了？？？
	//private IMusicDao dao1= new MusicMysqlDao();
	
	@Autowired
	private IMusicDao dao;
	
	@Scheduled(fixedRate=6000)
	public void  execTask()
	{
		
		System.out.println(Thread.currentThread().getName()+",MusicTask  is  execTask" +new Date());
		
		//替换抓取音乐的链接的代码，考虑性能。
		
		
		List<String>   lists  = new ArrayList<String>(); 
		lists.add("童话");
		lists.add("生命中的每一天");
		
		this.dao.batchInsert(lists);
		
		
		
	}

}
