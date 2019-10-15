package com.lixin.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.lixin.dao.MenuDao;
import com.lixin.model.FMenu;

public class MenuDataTask extends TimerTask {

	private String cpath;

	public MenuDataTask(String cpath) {
		this.cpath = cpath;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + ",启动了....");

		new Thread(new CMenuRunnable(cpath)).start();

		// 解析的是FMenu
		List<FMenu> flists = new ArrayList<FMenu>();

		InputStream in = this.getClass().getClassLoader().getResourceAsStream("fmenu.txt");

		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String line = "";

		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] datas = line.split("\\|");

				FMenu menu = new FMenu();
				menu.setFid(Integer.parseInt(datas[0]));
				menu.setFname(datas[1]);
				flists.add(menu);

			}

			MenuDao md = new MenuDao();
			md.batchFMenu(flists);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != br) {
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
