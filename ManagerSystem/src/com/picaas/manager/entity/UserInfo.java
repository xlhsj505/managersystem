package com.picaas.manager.entity;

/*
 * 	�û���
 */
import java.io.Serializable;

public class UserInfo implements Serializable{
	
	private String IUID;//Ψһ���
	private String userName;//�û���
	private String uPWD;//����
	private String isEnable;//״̬
	
	
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
