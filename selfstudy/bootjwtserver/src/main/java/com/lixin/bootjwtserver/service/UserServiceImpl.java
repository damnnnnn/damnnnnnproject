package com.lixin.bootjwtserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lixin.bootjwtserver.dao.IUserDao;

@Service
public class UserServiceImpl   implements IService{

	@Autowired
	private IUserDao dao;
	
	@Override
	public Object checkLogin(Object obj) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is  checkLogin  start..");
		return dao.checkLogin(obj);
		
		
	}

	





}
