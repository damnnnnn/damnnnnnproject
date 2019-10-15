package com.lixin.dao;

import java.util.List;

import com.lixin.factory.DBBase;
import com.lixin.model.CMenu;
import com.lixin.model.FMenu;
import com.lixin.model.GridMenu;

public class MenuDao extends DBBase {

	public void batchFMenu(List<FMenu> flists) {

		this.sqlSession.insert("fmenudao.batchfmenudatas", flists);

		System.out.println("**FMENU数据入库成功");
	}

	public void batchCMenu(List<CMenu> clists) {
		this.sqlSession.insert("cmenudao.batchcmenudatas", clists);
		System.out.println("-->CMENU数据入库成功");
	}

	public List<FMenu> getMenusData() {

		List<FMenu> flists = this.sqlSession.selectList("fmenudao.queryfmenu");

		for (FMenu fmenu : flists) {

			List<CMenu> clists = this.sqlSession.selectList("cmenudao.querycmenybyid", fmenu.getFid());

			fmenu.setClists(clists);
		}

		return flists;
	}

	public List<GridMenu> getGridMenu() {
		List<GridMenu> lists = this.sqlSession.selectList("fmenudao.querygridmenu");

		return lists;
	}

}
