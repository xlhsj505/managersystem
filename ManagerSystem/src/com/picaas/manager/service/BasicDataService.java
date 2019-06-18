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
	 * ��Ӷ������
	 * 
	 * @param list
	 */
	public int addPFForList(List<Object> list) {
		// TODO Auto-generated method stub
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.addPFForList(list);
	}

	/**
	 * ��Ӷ�����߲�ʷ��
	 * 
	 * @param list
	 */
	public int addIllnessForList(List<Object> list) {
		// TODO Auto-generated method stub
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.addIllnessForList(list);
	}

	/**
	 * ��Ӷ��Ӱ��ѧ
	 * 
	 * @param list
	 */
	public int addIconographyForList(List<Object> list) {
		// TODO Auto-generated method stub
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.addIconographyForList(list);
	}

	/**
	 * ��ѯ���л��߲�ʷ
	 * 
	 * @return list ģ�鼯��
	 */
	public List<Illness> doQueryIllness() {
		BasicDataDao basicDataDao = new BasicDataDao();
		List<Illness> list = basicDataDao.doQueryIllness();
		return list;
	}

	/**
	 * ��ѯӰ��ѧ
	 * 
	 * @return list ģ�鼯��
	 * @throws SQLException
	 */
	public List<Iconography> doQueryIconography() {
		BasicDataDao basicDataDao = new BasicDataDao();
		List<Iconography> list = basicDataDao.doQueryIconography();
		return list;
	}

	/**
	 * ��ѯ���л�����Ϣ
	 * 
	 * @return list ģ�鼯��
	 * @throws SQLException
	 */
	public List<PatientInformation> doQueryPatientInformation() {
		BasicDataDao basicDataDao = new BasicDataDao();
		List<PatientInformation> list = basicDataDao.doQueryPatientInformation();
		return list;
	}

	// ��ѯ�ɼ�
	public List<Information> doAUpGrade(String round, String iuid) {
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.doAUpGrade(round, iuid);
	}

	/**
	 * ��ҳ��ѯ����ѧ����Ϣ
	 * 
	 * @return list ģ�鼯��
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

	// ��ѯ�����ʾ�
	public List<String> doQueryQuestionnaire(List<String> list) {
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.doQueryQuestionnaire(list);
	}

	// ��ӳɼ�
	public List<Integer> doAddGrade(List<List<String>> list) {
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.doAddGrade(list);
	}

	////
	// /**
	// * ��Ӷ��ѧ��
	// * @param list
	// */
	// public int addStudentsForList(List<Object> list) {
	// // TODO Auto-generated method stub
	// BasicDataDao basicDataDao = new BasicDataDao();
	// return basicDataDao.addStudentsForList(list);
	// }
	//
	// /**
	// * ��������ѯѧ����Ϣ
	// * @param pageBean ��ҳ
	// * @param idCard ���֤
	// * @param schoolName ѧУ��
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
	// * ����ѧ����Ϣ���еı�������¼ʱ��
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
	// * ����ѧ���ɼ�
	// */
	// public List<CollectScore> doCollectScore() {
	// BasicDataDao basicDataDao = new BasicDataDao();
	// return basicDataDao.doCollectScore();
	//
	// }
	//
	// /**
	// * ����ѧ��IUID����ѧ����Ϣ���е�totalScore
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
	// * ����ѧУIUID��ѯѧ����Ϣ
	// * @param iuid
	// */
	// public List<Students> doQueryStudentsBySchoolIUID(int iuid) {
	// BasicDataDao basicDataDao = new BasicDataDao();
	// return basicDataDao.doQueryStudentsBySchoolIUID(iuid);
	//
	// }
	//
	/**
	 * ��ѯ���߳ɼ�
	 * 
	 * @param iuid
	 * @param
	 */
	public List<Grade> doQueryGrade(String iuid) {
		BasicDataDao basicDataDao = new BasicDataDao();
		return basicDataDao.doQueryGrade(iuid);

	}

}
