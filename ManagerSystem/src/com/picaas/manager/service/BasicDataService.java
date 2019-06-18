package com.picaas.manager.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.picaas.comm.PageBean;
import com.picaas.manager.dao.BasicDataDao;
import com.picaas.manager.entity.Grade;
import com.picaas.manager.entity.Iconography;
import com.picaas.manager.entity.Illness;
import com.picaas.manager.entity.Information;
import com.picaas.manager.entity.PatientInformation;

public class BasicDataService {

	/**
	 * 添加多个患者
	 * 
	 * @param list
	 */
	public int addPFForList(List<Object> list) {
		// TODO Auto-generated method stub
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.addPFForList(list);
	}

	/**
	 * 添加多个患者病史表
	 * 
	 * @param list
	 */
	public int addIllnessForList(List<Object> list) {
		// TODO Auto-generated method stub
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.addIllnessForList(list);
	}

	/**
	 * 添加多个影像学
	 * 
	 * @param list
	 */
	public int addIconographyForList(List<Object> list) {
		// TODO Auto-generated method stub
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.addIconographyForList(list);
	}

	/**
	 * 查询所有患者病史
	 * 
	 * @return list 模块集合
	 */
	public List<Illness> doQueryIllness() {
		BasicDataDao basicDataDao = new BasicDataDao();
		List<Illness> list = basicDataDao.doQueryIllness();
		return list;
	}

	/**
	 * 查询影像学
	 * 
	 * @return list 模块集合
	 * @throws SQLException
	 */
	public List<Iconography> doQueryIconography() {
		BasicDataDao basicDataDao = new BasicDataDao();
		List<Iconography> list = basicDataDao.doQueryIconography();
		return list;
	}

	/**
	 * 查询所有患者信息
	 * 
	 * @return list 模块集合
	 * @throws SQLException
	 */
	public List<PatientInformation> doQueryPatientInformation() {
		BasicDataDao basicDataDao = new BasicDataDao();
		List<PatientInformation> list = basicDataDao.doQueryPatientInformation();
		return list;
	}

	// 查询成绩
	public List<Information> doAUpGrade(String round, String iuid) {
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.doAUpGrade(round, iuid);
	}

	/**
	 * 分页查询所有学生信息
	 * 
	 * @return list 模块集合
	 * @throws SQLException
	 */
	public PageBean<PatientInformation> doPagedQueryPatientInformation(PageBean<PatientInformation> page) {
		BasicDataDao basicDataDao = new BasicDataDao();
		PageBean<PatientInformation> pageBean = basicDataDao.doPagedQueryPatientInformation(page);
		return pageBean;
	}

	public List<PatientInformation> doQueryStudentsByIDCard(String iuid) {
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.doQueryStudentsByIDCard(iuid);
	}

	// 查询条件问卷
	public List<String> doQueryQuestionnaire(List<String> list) {
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.doQueryQuestionnaire(list);
	}

	// 添加成绩
	public List<Integer> doAddGrade(List<List<String>> list) {
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.doAddGrade(list);
	}

	////
	// /**
	// * 添加多个学生
	// * @param list
	// */
	// public int addStudentsForList(List<Object> list) {
	// // TODO Auto-generated method stub
	// BasicDataDao basicDataDao = new BasicDataDao();
	// return basicDataDao.addStudentsForList(list);
	// }
	//
	// /**
	// * 多条件查询学生信息
	// * @param pageBean 分页
	// * @param idCard 身份证
	// * @param schoolName 学校名
	// * @return
	// */
	// public PageBean<PatientInformation>
	//// doConditionQueryStudents(PageBean<PatientInformation> pageBean, String
	//// idCard, String schoolName) {
	// BasicDataDao basicDataDao = new BasicDataDao();
	// return basicDataDao.doConditionQueryStudents(pageBean, idCard,
	//// schoolName);
	// }
	//
	// public List<School> doQuerySchool(){
	// BasicDataDao basicDataDao = new BasicDataDao();
	// return basicDataDao.doQuerySchool();
	// }
	//
	// /**
	// * 更新学生信息表中的报到、检录时间
	// * @param iUID
	// * @param registerTime
	// * @param checkInTime
	// * @return
	// */
	// public int ajaxUpRegisterAndCheckInTimeByIUID(String iUID, String
	//// registerTime, String checkInTime) {
	// BasicDataDao basicDataDao = new BasicDataDao();
	// return basicDataDao.ajaxUpRegisterAndCheckInTimeByIUID(iUID,
	//// registerTime, checkInTime);
	// }
	//
	// /**
	// * 汇总学生成绩
	// */
	// public List<CollectScore> doCollectScore() {
	// BasicDataDao basicDataDao = new BasicDataDao();
	// return basicDataDao.doCollectScore();
	//
	// }
	//
	// /**
	// * 根据学生IUID更新学生信息表中的totalScore
	// * @param studentsIUID
	// * @param totalScore
	// */
	// public int doUpdateStuScoreByIUID(int studentsIUID, double totalScore) {
	// BasicDataDao basicDataDao = new BasicDataDao();
	// return basicDataDao.doUpdateStuScoreByIUID(studentsIUID, totalScore);
	//
	// }
	//
	// /**
	// * 根据学校IUID查询学生信息
	// * @param iuid
	// */
	// public List<Students> doQueryStudentsBySchoolIUID(int iuid) {
	// BasicDataDao basicDataDao = new BasicDataDao();
	// return basicDataDao.doQueryStudentsBySchoolIUID(iuid);
	//
	// }
	//
	/**
	 * 查询患者成绩
	 * 
	 * @param iuid
	 * @param
	 */
	public List<Grade> doQueryGrade(String iuid) {
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.doQueryGrade(iuid);

	}

}
