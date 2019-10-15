package com.liujain.music.runn;

import com.liujain.music.dao.MusicMysql;
import com.liujain.music.model.BaiduMusicBean;
import org.springframework.beans.factory.annotation.Autowired;
import sun.awt.windows.ThemeReader;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ParseSongRunn implements Runnable {

    LinkedList songlist;

//    @Autowired
//    MusicMysql mm;

    public ParseSongRunn(LinkedList songlist) {
        this.songlist = songlist;
    }

    @Override
    public void run() {
        BaiduSpider spider = new BaiduSpider();

        Iterator iterator = songlist.iterator();

        while (iterator.hasNext()) {
            BaiduMusicBean baiduMusicBean = spider.spiderSongData((BaiduMusicBean) iterator.next());
            if(baiduMusicBean.getSongID() == null){
                spider.spiderSongData((BaiduMusicBean) iterator.next());
            }
            System.out.println(Thread.currentThread().getName());
        }

//        mm.songInsert(songlist);
    }
}
