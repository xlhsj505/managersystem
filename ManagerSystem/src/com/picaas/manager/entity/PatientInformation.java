package com.picaas.manager.entity;

import java.io.Serializable;

/*
 * 	������Ϣ��
 */
public class PatientInformation implements Serializable{
	private String IUID;//����ID
	private String name;//����
	private String sex;//�Ա�
	private String birthday;//��������
	private String age;//����
	private String height;//���
	private String weight;//����
	private String registeredResidence;//��������
	private String tel;//�绰
	private String address;//��ַ�Ƿ�ͻ�����ͬ
	private String permanentAddress;//��ס��ַ
	private String nation;//����
	private String culturalLevel;//�Ļ�ˮƽ
	private String occupation;//ְҵ
	private String workingStaus;//����״��
	private String jobNature;//��������
	private String maritalStatus;//����״��
	private String isChild;//������Ů
	private String lifeStyle;//���ʽ
	private String independece;//�����̶�
	private String medicalInsurance;//ҽ�Ʊ���
	private String other;//����
	private String religiousBelief;//�ڽ�����
	public String getIUID() {
		return IUID;
	}
	public void setIUID(String iUID) {
		IUID = iUID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getRegisteredResidence() {
		return registeredResidence;
	}
	public void setRegisteredResidence(String registeredResidence) {
		this.registeredResidence = registeredResidence;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getCulturalLevel() {
		return culturalLevel;
	}
	public void setCulturalLevel(String culturalLevel) {
		this.culturalLevel = culturalLevel;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getWorkingStaus() {
		return workingStaus;
	}
	public void setWorkingStaus(String workingStaus) {
		this.workingStaus = workingStaus;
	}
	public String getJobNature() {
		return jobNature;
	}
	public void setJobNature(String jobNature) {
		this.jobNature = jobNature;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getIsChild() {
		return isChild;
	}
	public void setIsChild(String isChild) {
		this.isChild = isChild;
	}
	public String getLifeStyle() {
		return lifeStyle;
	}
	public void setLifeStyle(String lifeStyle) {
		this.lifeStyle = lifeStyle;
	}
	public String getIndependece() {
		return independece;
	}
	public void setIndependece(String independece) {
		this.independece = independece;
	}
	public String getMedicalInsurance() {
		return medicalInsurance;
	}
	public void setMedicalInsurance(String medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getReligiousBelief() {
		return religiousBelief;
	}
	public void setReligiousBelief(String religiousBelief) {
		this.religiousBelief = religiousBelief;
	}
	@Override
	public String toString() {
		return "PatientInformation [IUID=" + IUID + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday
				+ ", age=" + age + ", height=" + height + ", weight=" + weight + ", registeredResidence="
				+ registeredResidence + ", tel=" + tel + ", address=" + address + ", permanentAddress="
				+ permanentAddress + ", nation=" + nation + ", culturalLevel=" + culturalLevel + ", occupation="
				+ occupation + ", workingStaus=" + workingStaus + ", jobNature=" + jobNature + ", maritalStatus="
				+ maritalStatus + ", isChild=" + isChild + ", lifeStyle=" + lifeStyle + ", independece=" + independece
				+ ", medicalInsurance=" + medicalInsurance + ", other=" + other + ", religiousBelief=" + religiousBelief
				+ "]";
	}
	
}
