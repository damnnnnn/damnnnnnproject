package com.lixin.model;

public class GridMenu {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGimg() {
		return gimg;
	}

	public void setGimg(String gimg) {
		this.gimg = gimg;
	}

	private  int id;
	
	private String  gname;
	
	private String  gimg;
	
	private String  viewpath;

	public String getViewpath() {
		return viewpath;
	}

	public void setViewpath(String viewpath) {
		this.viewpath = viewpath;
	}

}
