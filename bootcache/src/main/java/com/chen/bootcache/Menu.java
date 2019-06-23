package com.chen.bootcache;

import java.io.Serializable;

public class Menu implements Serializable {
	private int id;
	private String mname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}

}
