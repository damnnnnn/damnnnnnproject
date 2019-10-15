package com.lixin.usereurekaservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lixin.usereurekaservice.dao.UserDao;
import com.lixin.usereurekaservice.model.UserInfo;

@Service
public class UserServiceImpl   implements IUserService{

	@Autowired
	private UserDao dao;

	public Iterable queryUserAll() {
		System.out.println("UserServiceImpl  is  queryUserAll  start...");

		Iterable<UserInfo> its = this.dao.findAll();

		return its;

	}

}
