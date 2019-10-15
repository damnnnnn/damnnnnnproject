package com.thzm.lixin.mavenfreemarkerinterceptor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
public class PropertiesBean {
	
	@Value("${queryusersInfo}")
	private  String  userinfo;

	public String getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
	

}
