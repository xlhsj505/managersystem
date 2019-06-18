package com.picaas.manager.service;

import java.sql.Connection;
import java.util.List;

import com.picaas.comm.DBUtils;
import com.picaas.manager.dao.UserDao;
import com.picaas.manager.entity.UserInfo;



public class UserService {

	public List<UserInfo> login(String userName, String password) {
		List<UserInfo> list = null;
		
		//获得数据库连接
		Connection conn = DBUtils.getConn();
		UserDao userDao = new UserDao();
		list = userDao.login(userName, password);
		
		return list;
	}

}
