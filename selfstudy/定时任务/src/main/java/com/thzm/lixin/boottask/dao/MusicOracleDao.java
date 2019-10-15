package com.thzm.lixin.boottask.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository  //dao组件  插入数据库的类
public class MusicOracleDao implements IMusicDao{

	@Override
	public void batchInsert(List lists) {
		// TODO Auto-generated method stub
		System.out.println("oracle数据库准备入库....");
	}


	
	

}
