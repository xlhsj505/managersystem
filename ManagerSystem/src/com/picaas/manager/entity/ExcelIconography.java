package com.picaas.manager.entity;

import java.io.Serializable;

public class ExcelIconography implements Serializable{
	private String IUID;//身份证
	private String hematencephalon;//脑出血
	private String brainInfarction;//脑梗塞
	private String angiography;//血管造影
	private String presentation;//影像报告
	
	public String getIUID() {
		return IUID;
	}
	public void setIUID(String iUID) {
		IUID = iUID;
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
