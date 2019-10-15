package com.lixin.bootjwtserver.model;

public class UserInfo
{

	private int id;

	private String uname;

	private String upwd;

	private String usex;
	private String ubirthday;

	private String uaddress;

	private int ustate;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String  phone;
	
	public String getFaceimg() {
		return faceimg;
	}

	public void setFaceimg(String faceimg) {
		this.faceimg = faceimg;
	}

	private String faceimg;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUname()
	{
		return uname;
	}

	public void setUname(String uname)
	{
		this.uname = uname;
	}

	public String getUpwd()
	{
		return upwd;
	}

	public void setUpwd(String upwd)
	{
		this.upwd = upwd;
	}

	public String getUsex()
	{
		return usex;
	}

	public void setUsex(String usex)
	{
		this.usex = usex;
	}

	public String getUbirthday()
	{
		return ubirthday;
	}

	public void setUbirthday(String ubirthday)
	{
		this.ubirthday = ubirthday;
	}

	public String getUaddress()
	{
		return uaddress;
	}

	public void setUaddress(String uaddress)
	{
		this.uaddress = uaddress;
	}

	public int getUstate()
	{
		return ustate;
	}

	public void setUstate(int ustate)
	{
		this.ustate = ustate;
	}

}
