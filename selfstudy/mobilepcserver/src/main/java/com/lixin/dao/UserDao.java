package com.lixin.dao;

import java.util.List;

import com.lixin.factory.DBBase;
import com.lixin.model.UserInfo;

public class UserDao  extends DBBase   implements IUserDao{

	
	public  List<UserInfo>  queryUserInfo()
	{
		System.out.println("UserDao  is   queryUserInfo  start...");
		
		List<UserInfo>  lists=this.sqlSession.selectList("userinfodao.queryuserphone");
		return  lists;
	}
}
