package com.liujain.music.runn;

import com.liujain.music.dao.MusicMysql;
import com.liujain.music.model.BaiduMusicBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

@Component
public class BaiduMusicRunn implements Runnable {

    @Autowired
    MusicMysql mm;


    @Override
    public void run() {

        //1.获取榜单歌曲songId
        String[] listArray = new String[]{"dayhot","new","netsong","oldsong","yingshijinqu","oumei"};
        BaiduSpider spider = new BaiduSpider();

        LinkedList<BaiduMusicBean> list = new LinkedList<BaiduMusicBean>();

        for (int i = 0; i <listArray.length -1 ; i++) {
            list.addAll(spider.spiderSongId(listArray[i]));
            System.out.println("爬取榜单"+listArray[i]+",歌曲--》"+list.size());
        }

        System.out.println("songID 爬取完毕");


        //2.获取榜单歌曲信息
//        LinkedList<BaiduMusicBean> songDataList = new LinkedList<BaiduMusicBean>();
//
//        Iterator iterator = list.iterator();
//
//        while (iterator.hasNext()) {
//            BaiduMusicBean baiduMusicBean = spider.spiderSongData((BaiduMusicBean) iterator.next());
//        }

//        ParseSongRunn parseSongRunn1 = new ParseSongRunn(new LinkedList(list.subList(0,100)));
        ParseSongRunn parseSongRunn1 = new ParseSongRunn(list);

        ParseSongRunn parseSongRunn2 = new ParseSongRunn(new LinkedList(list.subList(100,list.size())));

        Thread t1=new Thread(parseSongRunn1);
//        Thread t2=new Thread(parseSongRunn2);

        t1.start();
//        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
////            t2.join();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }

        //数据入库
        mm.songInsert(list);

    }
}
