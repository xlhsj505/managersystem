package com.picaas.manager.entity;

import java.io.Serializable;

public class Iconography implements Serializable{
	private String IUID;//Ӱ��ѧ�����ID
	private PatientInformation patientInformation;//����ID���
	private String hematencephalon;//�Գ�Ѫ
	private String brainInfarction;//�Թ���
	private String angiography;//Ѫ����Ӱ
	private String presentation;//Ӱ�񱨸�
	
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
	public String getHematencephalon() {
		return hematencephalon;
	}
	public void setHematencephalon(String hematencephalon) {
		this.hematencephalon = hematencephalon;
	}
	public String getBrainInfarction() {
		return brainInfarction;
	}
	public void setBrainInfarction(String brainInfarction) {
		this.brainInfarction = brainInfarction;
	}
	public String getAngiography() {
		return angiography;
	}
	public void setAngiography(String angiography) {
		this.angiography = angiography;
	}
	public String getPresentation() {
		return presentation;
	}
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	
}
