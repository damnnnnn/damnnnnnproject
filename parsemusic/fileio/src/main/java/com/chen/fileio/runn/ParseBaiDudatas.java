package com.chen.fileio.runn;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;



import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chen.fileio.model.Music;




//1.百度音乐的链接热歌进行抓取// http://music.taihe.com/top/dayhot,song-title

//2.http://music.taihe.com+song/537201084 抓取data-songid

//3.http://ting.baidu.com/data/music/links?songIds=data-songid&rate=320

//4.数据入库(歌曲名称，歌曲图片，歌曲歌词链接，歌曲的音乐链接)
public class ParseBaiDudatas {
	static List<String> numLists = new LinkedList<String>();
	static List<String> songLists = new LinkedList<String>();
	static List<Music> musicdatas = new LinkedList<Music>();
	//-------------------------------------------------------------------------------
	public static List<String> parseHtml(String htmlurl, String codeName)
	{
		Connection conn = Jsoup.connect(htmlurl);
		
		try {
			Document doc = conn.get();
			
			if (codeName.equals("song-title")) {
				Elements listsElements=doc.select(".song-title");
				
				for(Element currentElement: listsElements)
				{
					String linkNum= currentElement.select("a").attr("href");
					numLists.add(linkNum);
				}
				return numLists;
			}
			else if (codeName.equals("data-songid")) {
				Elements listElements = doc.select(".song-opera");

				for (Element currentElememt : listElements) {
					String songNum = currentElememt.attr("data-songid");
					// System.out.println("每个歌曲data-songid编号为:"+songNum);

					songLists.add(songNum);
				}

				return songLists;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//-------------------------------------------------------------------------------
	public static List<String> parseHtml(List<String> lists, String codeName) {

		List<String> songidLists = null;
		for (String numlink : lists) {
			String numurl = "http://music.taihe.com" + numlink;
			songidLists = parseHtml(numurl, codeName);
		}

		return songidLists;
	}
	//-------------------------------------------------------------------------------
	public static List<Music> parseHtml(List<String> lists) {

		for (String datasongid : lists) {
			String musicurl = "http://ting.baidu.com/data/music/links?songIds=" + datasongid + "&rate=320";

			try {
				// 把这个字符串路径构建成URL类，网络路径
				URL url = new URL(musicurl);

				// 打开这个链接
				HttpURLConnection connurl = (HttpURLConnection) url.openConnection();

				if (connurl.getResponseCode() == HttpURLConnection.HTTP_OK) {
					InputStream in = connurl.getInputStream();

					int len = 0;

					String str = "";
					while ((len = in.read()) != -1) {
						char c = (char) len;
						str = str + c;
					}

					// System.out.println("json的数据为-->" + str);

					// 1.歌曲名称 2.歌曲图片 3.歌曲歌词 4.歌曲音乐链接

					// 把一个json字符串转换陈jsonObject对象。
					JSONObject jsonObj = JSONObject.parseObject(str);

					JSONObject nextObj = jsonObj.getJSONObject("data");

					JSONArray arrayObj = nextObj.getJSONArray("songList");

					JSONObject dataObj = (JSONObject) arrayObj.get(0);

					String songName = (String) dataObj.get("songName");
					String songPic = (String) dataObj.get("songPicRadio");
					String songLRC = (String) dataObj.get("lrcLink");
					String songLink = (String) dataObj.get("songLink");
					String songShowlink = (String) dataObj.get("showLink");

					System.out.println(songName + "\t" + songPic + "\t" + songLRC + "\t" + songLink+ "\t" + songShowlink);

					Music m = new Music();
					m.setSname(songName);
					m.setSimg(songPic);
					m.setSlrc(songLRC);
                    m.setSlink(songLink);
                    m.setShowlink(songShowlink);
					musicdatas.add(m);
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return musicdatas;

	}
	

}
