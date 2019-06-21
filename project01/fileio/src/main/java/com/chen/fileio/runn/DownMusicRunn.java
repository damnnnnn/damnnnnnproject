package com.chen.fileio.runn;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.chen.fileio.model.Music;

public class DownMusicRunn implements Runnable {

	private List<Music> lists;
	public DownMusicRunn(List<Music> lists) {
		this.lists = lists;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int num = 0;
		
		BufferedInputStream br = null;
		
		BufferedOutputStream out = null;

		for (Music music : lists) {
			try {
				URL url = new URL(music.getSlink());

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				if (conn.getResponseCode() == 200) {
					InputStream in = conn.getInputStream();

					String musicPath = "./src/main/resources/static/music/";

					Date date = new Date();

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

					String fileName = sdf.format(date);

					musicPath += fileName + "_" + num + ".mp3";

					File file = new File(musicPath);

					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();
					}

					// 普通字节流转换成字节缓冲输入流
					 br = new BufferedInputStream(in);

				     out = new BufferedOutputStream(new FileOutputStream(file));

					int len = 0;

					while ((len = br.read()) != -1) {
						out.write(len);
					}

					num++;

				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				
				if(out!=null)
				{
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(null!=br)
				{
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}

		}
	}

}
