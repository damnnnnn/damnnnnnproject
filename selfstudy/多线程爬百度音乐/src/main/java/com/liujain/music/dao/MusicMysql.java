package com.liujain.music.dao;

import com.liujain.music.model.BaiduMusicBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MusicMysql {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void songInsert(List<BaiduMusicBean> lists){
        System.out.println("MusicMysql is songInsert start...");

        final List<BaiduMusicBean>  mlists=lists;

        String sql="insert  into  t_music(author,songName,songImg,IrcLink,songLink,list,top,songId)  values(?,?,?,?,?,?,?,?)";

//批量数据处理
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                // TODO Auto-generated method stub
                ps.setString(1, mlists.get(i).getAuthor());
                ps.setString(2, mlists.get(i).getSongName());
                ps.setString(3, mlists.get(i).getSongImg());
                ps.setString(4, mlists.get(i).getIrcLink());
                ps.setString(5, mlists.get(i).getSongLink());
                ps.setString(6, mlists.get(i).getList());
                ps.setString(7, mlists.get(i).getTop());
                ps.setString(8, mlists.get(i).getSongID());
            }

            @Override
            public int getBatchSize() {
                // TODO Auto-generated method stub
                return   mlists.size();
            }
        });

    }
}
