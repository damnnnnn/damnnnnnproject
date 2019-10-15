package com.liujain.music.controller;

import com.liujain.music.runn.BaiduMusicRunn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaiduMusic {

    @Autowired
    BaiduMusicRunn runn ;

    @RequestMapping("/spiderBaiduMusic")
    public String spiderBaiduMusic(){
        System.out.println("BaiduMusic is spiderBaiduMusic start...");

        new Thread(runn).start();

        return "爬取百度音乐榜单";
    }

}
