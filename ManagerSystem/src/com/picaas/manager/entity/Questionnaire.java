package com.picaas.manager.entity;

import java.io.Serializable;

/*
 * 	�ʾ��
 */
public class Questionnaire implements Serializable{
	private String IUID;//�ʾ�ID
	private String title;//����
	private String content;//����
	
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
