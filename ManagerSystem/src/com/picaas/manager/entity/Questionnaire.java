package com.picaas.manager.entity;

import java.io.Serializable;

/*
 * 	问卷表
 */
public class Questionnaire implements Serializable{
	private String IUID;//问卷ID
	private String title;//标题
	private String content;//内容
	
	public String getIUID() {
		return IUID;
	}
	public void setIUID(String iUID) {
		IUID = iUID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
