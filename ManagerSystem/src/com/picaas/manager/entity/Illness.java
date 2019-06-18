package com.picaas.manager.entity;

import java.io.Serializable;

/*
 * 	���߲�ʷ��
 */
public class Illness implements Serializable{
	private String IUID;//���߲�ʷ��id
	private PatientInformation patientInformation;//����id���
	private String smoke;//����
	private String drink;//����
	private String highSalinity;//�ȸ�����ʳ
	private String highFat;//�ȸ�֬��ʳ
	private String piquancy;//��������ʳ
	private String highGlucose;//�ȸ�����ʳ
	private String sleepQuality;//ÿ��˯�����
	private String diabetes;//����ʷ
	private String glucoseControl;//ĿǰѪ�ǿ������
	private String hypertension;//��Ѫѹ��ʷ
	private String hyperlipemia;//��Ѫ֢֬ʷ
	private String coronaryDisease;//���Ĳ�ʷ
	private String shortCerebral;//��������ȱѪ����ʷ
	private String stroke;//������ʷ
	private String headInjury;//ͷ������ʷ
	private String majorLifeEvents;///�����ش������¼�ʷ
	private String operation;//����ʷ
	private String elseIllness;//��������ʷ
	private String drugAllergy;//ҩ�����ʷ/������Ӧʷ
	private String relyOn;//ҩ�����û�����ʷ
	private String dailyExercise;//���β�ǰ�ճ��˶�ϰ��
	private String teaHabit;//���β�ǰ���޺Ȳ�ϰ��
	private String coffeeHabit;//���β�ǰ���޺ȿ���ϰ��
	private String keepingPets;//���β�ǰ������������ϰ��
	private String hobbiesInterests;//��������ֵ���Ȥ�����糪��滭���Ƶ�
	private String mannerism;//���β�ǰ������������ϰ��(�籣��������ҽ��)
	
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
