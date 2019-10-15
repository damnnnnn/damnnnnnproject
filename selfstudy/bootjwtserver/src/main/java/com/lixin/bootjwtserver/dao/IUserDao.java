package com.lixin.bootjwtserver.dao;



import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {
	
	public Object checkLogin(Object  obj);
	

	


}
