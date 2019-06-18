package com.picaas.manager.entity;

import java.io.Serializable;

/*
 * 	患者病史表
 */
public class Illness implements Serializable{
	private String IUID;//患者病史表id
	private PatientInformation patientInformation;//患者id外键
	private String smoke;//吸烟
	private String drink;//饮酒
	private String highSalinity;//嗜高盐饮食
	private String highFat;//嗜高脂饮食
	private String piquancy;//嗜辛辣饮食
	private String highGlucose;//嗜高糖饮食
	private String sleepQuality;//每晚睡眠情况
	private String diabetes;//糖尿病史
	private String glucoseControl;//目前血糖控制情况
	private String hypertension;//高血压病史
	private String hyperlipemia;//高血脂症史
	private String coronaryDisease;//冠心病史
	private String shortCerebral;//短暂性脑缺血发作史
	private String stroke;//脑卒中史
	private String headInjury;//头部外伤史
	private String majorLifeEvents;///既往重大生活事件史
	private String operation;//手术史
	private String elseIllness;//其他疾病史
	private String drugAllergy;//药物过敏史/不良反应史
	private String relyOn;//药物滥用或依赖史
	private String dailyExercise;//本次病前日常运动习惯
	private String teaHabit;//本次病前有无喝茶习惯
	private String coffeeHabit;//本次病前有无喝咖啡习惯
	private String keepingPets;//本次病前有无饲养宠物习惯
	private String hobbiesInterests;//其他常坚持的兴趣爱好如唱歌绘画打牌等
	private String mannerism;//本次病前有无特殊生活习惯(如保健养生中医等)
	
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
	public String getSmoke() {
		return smoke;
	}
	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}
	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
	public String getHighSalinity() {
		return highSalinity;
	}
	public void setHighSalinity(String highSalinity) {
		this.highSalinity = highSalinity;
	}
	public String getHighFat() {
		return highFat;
	}
	public void setHighFat(String highFat) {
		this.highFat = highFat;
	}
	public String getPiquancy() {
		return piquancy;
	}
	public void setPiquancy(String piquancy) {
		this.piquancy = piquancy;
	}
	public String getHighGlucose() {
		return highGlucose;
	}
	public void setHighGlucose(String highGlucose) {
		this.highGlucose = highGlucose;
	}
	public String getSleepQuality() {
		return sleepQuality;
	}
	public void setSleepQuality(String sleepQuality) {
		this.sleepQuality = sleepQuality;
	}
	public String getDiabetes() {
		return diabetes;
	}
	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}
	public String getGlucoseControl() {
		return glucoseControl;
	}
	public void setGlucoseControl(String glucoseControl) {
		this.glucoseControl = glucoseControl;
	}
	public String getHypertension() {
		return hypertension;
	}
	public void setHypertension(String hypertension) {
		this.hypertension = hypertension;
	}
	public String getHyperlipemia() {
		return hyperlipemia;
	}
	public void setHyperlipemia(String hyperlipemia) {
		this.hyperlipemia = hyperlipemia;
	}
	public String getCoronaryDisease() {
		return coronaryDisease;
	}
	public void setCoronaryDisease(String coronaryDisease) {
		this.coronaryDisease = coronaryDisease;
	}
	public String getShortCerebral() {
		return shortCerebral;
	}
	public void setShortCerebral(String shortCerebral) {
		this.shortCerebral = shortCerebral;
	}
	public String getStroke() {
		return stroke;
	}
	public void setStroke(String stroke) {
		this.stroke = stroke;
	}
	public String getHeadInjury() {
		return headInjury;
	}
	public void setHeadInjury(String headInjury) {
		this.headInjury = headInjury;
	}
	public String getMajorLifeEvents() {
		return majorLifeEvents;
	}
	public void setMajorLifeEvents(String majorLifeEvents) {
		this.majorLifeEvents = majorLifeEvents;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getElseIllness() {
		return elseIllness;
	}
	public void setElseIllness(String elseIllness) {
		this.elseIllness = elseIllness;
	}
	public String getDrugAllergy() {
		return drugAllergy;
	}
	public void setDrugAllergy(String drugAllergy) {
		this.drugAllergy = drugAllergy;
	}
	public String getRelyOn() {
		return relyOn;
	}
	public void setRelyOn(String relyOn) {
		this.relyOn = relyOn;
	}
	public String getDailyExercise() {
		return dailyExercise;
	}
	public void setDailyExercise(String dailyExercise) {
		this.dailyExercise = dailyExercise;
	}
	public String getTeaHabit() {
		return teaHabit;
	}
	public void setTeaHabit(String teaHabit) {
		this.teaHabit = teaHabit;
	}
	public String getCoffeeHabit() {
		return coffeeHabit;
	}
	public void setCoffeeHabit(String coffeeHabit) {
		this.coffeeHabit = coffeeHabit;
	}
	public String getKeepingPets() {
		return keepingPets;
	}
	public void setKeepingPets(String keepingPets) {
		this.keepingPets = keepingPets;
	}
	public String getHobbiesInterests() {
		return hobbiesInterests;
	}
	public void setHobbiesInterests(String hobbiesInterests) {
		this.hobbiesInterests = hobbiesInterests;
	}
	public String getMannerism() {
		return mannerism;
	}
	public void setMannerism(String mannerism) {
		this.mannerism = mannerism;
	}

	
}
