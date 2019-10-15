package com.lixin.factory;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBBase {

	protected static SqlSession sqlSession;

	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");

			System.out.println(reader);

			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);

			sqlSession = ssf.openSession();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
