package com.demo.UserAction;

import com.demo.dao.IDao;

public class UserAction {
    private IDao iDao;

    public IDao getiDao() {
        return iDao;
    }

    public void setiDao(IDao iDao) {
        this.iDao = iDao;
    }

    public void exec(){
        this.iDao.queryData();
    }
}