package com.picaas.manager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.picaas.comm.DBUtils;
import com.picaas.manager.entity.UserInfo;



public class UserDao {
	public List<UserInfo> login(String userName, String password) {
		//获得数据库连接
//		Connection conn = DBUtils.getConn();
		List<UserInfo> list = new ArrayList<>();
		ResultSet result = null;
		String sql = "select * from TB_User where user_name = ? and upwd = ?";
		String[] params = {userName, password};
		result = DBUtils.executeQuery(sql, params);
		
		try {
			if (result.next()) {
				UserInfo userInfo = new UserInfo();
//				System.out.println(result.getInt(1));
				userInfo.setIUID(result.getString(1));
				userInfo.setUserName(result.getString(2));
				userInfo.setuPWD(result.getString(3));
				userInfo.setIsEnable(result.getString(4));
				list.add(userInfo);
			}else {
				return list = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
