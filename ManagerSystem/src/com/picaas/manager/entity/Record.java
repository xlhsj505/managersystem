package com.picaas.manager.entity;

import java.io.Serializable;

/*
 * 	�����¼��
 */
public class Record implements Serializable{
	private String IUID;//�����¼��ID
	private Questionnaire questionnaire;//�ʾ��ID���
	private String round;//�ִ�
	private String startTime;//��ʼʱ��
	private String endTime;//����ʱ��
	
	
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
