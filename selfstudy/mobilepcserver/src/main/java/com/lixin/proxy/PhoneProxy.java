package com.lixin.proxy;

import java.util.List;

import com.lixin.aop.PhoneAop;
import com.lixin.dao.IUserDao;
import com.lixin.model.UserInfo;

public class PhoneProxy implements IUserDao {

	// ʵ����ĸ��ӿ�
	private IUserDao userdao;

	// ������
	private PhoneAop phoneaop;

	public PhoneProxy(IUserDao userdao, PhoneAop phoneaop) {
		this.userdao = userdao;
		this.phoneaop = phoneaop;
	}

	public List<UserInfo> queryUserInfo() {
		// TODO Auto-generated method stub
		Object obj = this.phoneaop.getPhoneCache();
		List<UserInfo> lists = null;
		if (null == obj) {
			System.out.println("memcache������û������");

			lists = this.userdao.queryUserInfo();
			
			this.phoneaop.setPhoneCache(lists);

		}
		else
		{
			System.out.println("memcache����������***********************");
			lists =(List<UserInfo>)this.phoneaop.getPhoneCache();
		}
		return lists;
	}

}
