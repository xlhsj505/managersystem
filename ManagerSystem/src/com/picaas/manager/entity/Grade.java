package com.picaas.manager.entity;

import java.io.Serializable;

/*
 * 	�ɼ���
 */
public class Grade implements Serializable{
	private String IUID;//�ɼ���ID
	private PatientInformation patientInformation;//����Id���
	private Record record;//��¼ID���
	private String grade;//�ɼ�
	private String startTime;//¼��ʱ��
	private Questionnaire questionnaire;
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}
	public String getIUID() {
		return IUID;
	}
	public void setIUID(String iUID) {
		IUID = iUID;
	}
	public PatientInformation getPatientInformation() {
		return patientInformation;
	}
	public void setPatientInformation(PatientInformation patientInformation) {
		this.patientInformation = patientInformation;
	}
	public Record getRecord() {
		return record;
	}
	public void setRecord(Record record) {
		this.record = record;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	
}
