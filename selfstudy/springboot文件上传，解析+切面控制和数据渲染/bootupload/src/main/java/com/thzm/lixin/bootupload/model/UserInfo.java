package com.thzm.lixin.bootupload.model;

public class UserInfo {

	private Integer id;

	private String uname;

	private String upwd;

	private String usex;

	private String ubirthday;

	private String uaddress;

	private Integer ustate;

	private String faceimg;

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private int did;

	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUsex() {
		return usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	public String getUbirthday() {
		return ubirthday;
	}

	public void setUbirthday(String ubirthday) {
		this.ubirthday = ubirthday;
	}

	public String getUaddress() {
		return uaddress;
	}

	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}

	public Integer getUstate() {
		return ustate;
	}

	public void setUstate(Integer ustate) {
		this.ustate = ustate;
	}

	public String getFaceimg() {
		return faceimg;
	}

	public void setFaceimg(String faceimg) {
		this.faceimg = faceimg;
	}
}
