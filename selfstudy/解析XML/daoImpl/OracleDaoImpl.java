package com.demo.daoImpl;

import com.demo.dao.IDao;

public class OracleDaoImpl implements IDao {
    @Override
    public void queryData() {
        System.out.println("this is oracle");
    }
}
