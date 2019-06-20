package com.chen.fileio.runn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chen.fileio.dao.MusicDao;
import com.chen.fileio.model.Music;

@Component
public class MusicRunnable implements Runnable {
	@Autowired
	private MusicDao dao;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+",开始分析抓取百度音乐连接run...");
		 //第一步获取热歌页面的所有的歌曲的链接编号
		List<String>  numLists=ParseBaiDudatas.parseHtml("http://music.taihe.com/top/dayhot", "song-title");
	
	    //第二步拿到整个音乐的详细页面的歌曲的data-songid
		List<String>  songLists=ParseBaiDudatas.parseHtml(numLists, "data-songid");
		
		//System.out.println("data-songid数量:"+songLists.size());
		
		
		//第三步获取百度音乐链接的json数据格式及音乐数据的集合
		List<Music>  lists=ParseBaiDudatas.parseHtml(songLists);
		
		//*********????使用套接字完成音乐文件的下载mp3 李乃昊机器完成？？？？
		
		
		//启动线程下载音乐
//		new Thread(new DownMusicRunn(lists)).start();
	
		System.out.println("dao-->"+dao);
		
		int count=this.dao.batchDatas(lists);
		
		System.out.println("入库的数据条数:"+count);
	}
	

}
