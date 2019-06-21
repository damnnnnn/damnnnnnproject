package com.chen.fileio.model;

import java.io.Serializable;

public class Music implements Serializable{
	private String sname;
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSimg() {
		return simg;
	}
	public void setSimg(String simg) {
		this.simg = simg;
	}
	public String getSlrc() {
		return slrc;
	}
	public void setSlrc(String slrc) {
		this.slrc = slrc;
	}
	public String getSlink() {
		return slink;
	}
	public void setSlink(String slink) {
		this.slink = slink;
	}
	public String getShowlink() {
		return showlink;
	}
	public void setShowlink(String showlink) {
		this.showlink = showlink;
	}
	private String simg;
	private String slrc;
	private String slink;
	private String showlink;

}
