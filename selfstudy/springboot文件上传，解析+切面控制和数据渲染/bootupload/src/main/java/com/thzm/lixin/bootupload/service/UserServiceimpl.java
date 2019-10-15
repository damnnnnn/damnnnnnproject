package com.thzm.lixin.bootupload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thzm.lixin.bootupload.dao.IUserDao;

@Service
public class UserServiceimpl   implements  IUserService{
	
	@Autowired
	private IUserDao  udao;

	@Override
	public void batchUser(List lists) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceimpl  is  batchUser  start...");
		this.udao.batchUserInfo(lists);
	}

}
