package com.liujain.music.runn;

import com.alibaba.fastjson.JSONObject;
import com.liujain.music.model.BaiduMusicBean;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class BaiduSpider {

    public LinkedList<BaiduMusicBean> spiderSongId(String listName){
        System.out.println("BaiduSpider is spiderSongId start...");

        String baseUrl = "http://music.taihe.com/top/"+listName;
        Connection conn = Jsoup.connect(baseUrl);
        LinkedList<BaiduMusicBean> songData = new LinkedList<BaiduMusicBean>();

        try {
            Document doc = conn.get();

            Elements elements = doc.select(".song-item");
//            System.out.println(elements.size());

            for(Element element:elements){
                BaiduMusicBean music = new BaiduMusicBean();

                music.setList(listName);
                music.setTop(element.select(".index-num").text());
                music.setSongID(element.select(".song-title a").attr("href").split("/")[2]);

                System.out.println(element.select(".index-num").text());
                System.out.println(element.select(".song-title a").attr("href").split("/")[2]);

                songData.add(music);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return songData;
    }

    public BaiduMusicBean spiderSongData(BaiduMusicBean songData){
        System.out.println("BaiduSpider is spiderSongData start...");

        String url = "http://ting.baidu.com/data/music/links?songIds=" + songData.getSongID() + "&rate=320";

        try {
            URL songUrl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) songUrl.openConnection();

            if (conn.getResponseCode() == 200) {
                InputStream in = conn.getInputStream();

                int len = 0;
                String res = "";
                byte[] buffer = new byte[1024];
                //获取返回值
                while ((len = in.read(buffer)) != -1) {
                    res = res + new String(buffer, 0, len);
                }
                System.out.println(res);

                JSONObject resData = JSONObject.parseObject(res);

                JSONObject data = resData.getJSONObject("data");
                JSONObject songList = data.getJSONArray("songList").getJSONObject(0);

                songData.setAuthor(songList.get("artistName").toString());
                songData.setSongName(songList.get("songName").toString());
                songData.setSongImg(songList.get("songPicRadio").toString());
                songData.setIrcLink(songList.get("lrcLink").toString());
                songData.setSongLink(songList.get("songLink").toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return songData;
    }

    public static void main(String[] args) {
        BaiduSpider spider = new BaiduSpider();
        LinkedList<BaiduMusicBean> lit=spider.spiderSongId("dayhot");

        BaiduMusicBean baiduMusicBean= spider.spiderSongData( lit.getFirst());

    }
}
