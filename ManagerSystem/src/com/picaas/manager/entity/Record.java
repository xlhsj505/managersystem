package com.picaas.manager.entity;

import java.io.Serializable;

/*
 * 	调查记录表
 */
public class Record implements Serializable{
	private String IUID;//调查记录表ID
	private Questionnaire questionnaire;//问卷表ID外键
	private String round;//轮次
	private String startTime;//开始时间
	private String endTime;//结束时间
	
	
	public String getIUID() {
		return IUID;
	}
	public void setIUID(String iUID) {
		IUID = iUID;
	}
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
