package com.picaas.manager.entity;

import java.io.Serializable;

/*
 * 	患者信息表
 */
public class PatientInformation implements Serializable{
	private String IUID;//患者ID
	private String name;//姓名
	private String sex;//性别
	private String birthday;//出生日期
	private String age;//年龄
	private String height;//身高
	private String weight;//体重
	private String registeredResidence;//户口所在
	private String tel;//电话
	private String address;//地址是否和户口相同
	private String permanentAddress;//常住地址
	private String nation;//民族
	private String culturalLevel;//文化水平
	private String occupation;//职业
	private String workingStaus;//工作状况
	private String jobNature;//工作性质
	private String maritalStatus;//婚姻状况
	private String isChild;//有无子女
	private String lifeStyle;//生活方式
	private String independece;//独立程度
	private String medicalInsurance;//医疗保险
	private String other;//其他
	private String religiousBelief;//宗教信仰
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
