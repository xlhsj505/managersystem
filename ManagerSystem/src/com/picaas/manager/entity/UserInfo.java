package com.picaas.manager.entity;

/*
 * 	用户表
 */
import java.io.Serializable;

public class UserInfo implements Serializable{
	
	private String IUID;//唯一编号
	private String userName;//用户名
	private String uPWD;//密码
	private String isEnable;//状态
	
	
	public String getIUID() {
		return IUID;
	}
	public void setIUID(String iUID) {
		IUID = iUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getuPWD() {
		return uPWD;
	}
	public void setuPWD(String uPWD) {
		this.uPWD = uPWD;
	}
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	@Override
	public String toString() {
		return "UserInfo [IUID=" + IUID + ", userName=" + userName + ", uPWD=" + uPWD + ", isEnable=" + isEnable + "]";
	}
	
	
}
