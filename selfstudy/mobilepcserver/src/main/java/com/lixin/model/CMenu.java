package com.lixin.model;

public class CMenu {
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public String getCctime() {
		return cctime;
	}

	public void setCctime(String cctime) {
		this.cctime = cctime;
	}

	public String getMobileview() {
		return mobileview;
	}

	public void setMobileview(String mobileview) {
		this.mobileview = mobileview;
	}

	private int cid;
	
	private String  cname;
	
	private String  curl;
	
	public int getCfid() {
		return cfid;
	}

	public void setCfid(int cfid) {
		this.cfid = cfid;
	}

	private  int  cfid;
	
	private String  cctime;
	
	private String mobileview;

}
