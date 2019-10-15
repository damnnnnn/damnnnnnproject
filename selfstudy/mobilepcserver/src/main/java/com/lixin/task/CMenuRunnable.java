package com.lixin.task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lixin.dao.MenuDao;
import com.lixin.model.CMenu;

public class CMenuRunnable    implements  Runnable{
	
	private String  cpath;
	public CMenuRunnable(String  cpath)
	{
		this.cpath=cpath;
	}

	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+",∆Ù∂Øcmenu»Îø‚....");
		
		List<CMenu>  clists = new ArrayList<CMenu>();
		
		try {
			BufferedReader  br = new BufferedReader(new FileReader(cpath));
			
			
			String  line="";
			
			
			while((line=br.readLine())!=null)
			{
				System.out.println("-->"+line);
				
				String[] datas=line.split("\\|");
				
				CMenu  cmenu  = new  CMenu(); 
				cmenu.setCid(Integer.parseInt(datas[0]));
				cmenu.setCname(datas[1]);
				cmenu.setCurl(datas[2]);
				cmenu.setCfid(Integer.parseInt(datas[3]));
				cmenu.setMobileview(datas[4]);
				
				clists.add(cmenu);
				
			}
			
			
			MenuDao dao = new MenuDao();
			dao.batchCMenu(clists);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
