package com.chen.fileio.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chen.fileio.model.Music;

@Repository
public class MusicDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int batchDatas(List<Music> lists)
	{
		System.out.println("MusicDao is batchDatas start...");
		
		final List<Music> mlists= lists;
		
		System.out.println("jdbcTemplate-->"+jdbcTemplate);
		
		String sql= "insert  into  t_musicinfo (sname,spic,slink,slrc,showlink)  values(?,?,?,?,?)";
		
		//批量数据处理
		this.jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter()
				{

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// TODO Auto-generated method stub
						ps.setString(1, mlists.get(i).getSname());
						ps.setString(2, mlists.get(i).getSimg());
						ps.setString(3, mlists.get(i).getSlink());
						ps.setString(4, mlists.get(i).getSlrc());
						ps.setString(5, mlists.get(i).getShowlink());
					}

					@Override
					public int getBatchSize() {
						// TODO Auto-generated method stub
						return mlists.size();
					}
			
			
				});
		return mlists.size();
	}

}
