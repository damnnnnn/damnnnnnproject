package com.chen.fileio.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chen.fileio.runn.MusicRunnable;


//业务控制器，接受用户的请求并返回视图或数据给用户
@RestController
public class UserAction {
	
	@Autowired
	private MusicRunnable  runn;

	
	@RequestMapping("/bdmusicinfo")
	public  String  loadMusicInfo()
	{
		System.out.println("UserAction  is   loadMusicInfo  start...");
		
		//启动一个线程
		//MusicRunnable runn =new MusicRunnable();

		
		new Thread(runn).start();

		
		return  "index";
	}
	

}
