package com.lixin.usereurekaservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//实体类的标志
@Entity
//对应的数据库表名
@Table(name = "t_userinfo")
public class UserInfo {

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

	public String getUaddress() {
		return uaddress;
	}

	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}

	public String getUbirthday() {
		return ubirthday;
	}

	public void setUbirthday(String ubirthday) {
		this.ubirthday = ubirthday;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长主键
	private Long uid;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	private String uname;

	private String upwd;

	private String usex;

	private String uaddress;

	private String ubirthday;

}
