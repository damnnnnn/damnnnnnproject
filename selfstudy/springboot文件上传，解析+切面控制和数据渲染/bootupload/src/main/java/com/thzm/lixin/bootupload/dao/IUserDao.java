package com.thzm.lixin.bootupload.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {
	
	public  void  batchUserInfo(List  lists);

}
