package com.picaas.manager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.poi.poifs.crypt.dsig.facets.EnvelopedSignatureFacet;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.util.StringUtil;
import org.apache.poi.util.SystemOutLogger;
import org.apache.tomcat.util.digester.ObjectCreateRule;

import com.mchange.lang.StringUtils;
import com.picaas.comm.DBUtils;
import com.picaas.comm.PageBean;
import com.picaas.manager.entity.ExcelIconography;
import com.picaas.manager.entity.ExcelIllness;
import com.picaas.manager.entity.ExcelPatientInformation;
import com.picaas.manager.entity.Grade;
import com.picaas.manager.entity.Iconography;
import com.picaas.manager.entity.Illness;
import com.picaas.manager.entity.Information;
import com.picaas.manager.entity.PatientInformation;
import com.picaas.manager.entity.Questionnaire;
import com.picaas.manager.entity.Record;

public class BasicDataDao {
	//
	// /**
	// * 查询所有学校
	// * @return
	// */
	// public List<School> doQuerySchool(){
	//
	// String sql = "select * from TB_School where IsEnable = 1";
	// String[] params = {};
	// ResultSet resultSet = null;
	// List<School> list = new ArrayList<>();
	// resultSet = DBUtils.executeQuery(sql, params);
	// if (resultSet == null) {
	// return null;
	// } else {
	// try {
	// while (resultSet.next()) {
	// School s = new School();
	// s.setIUID(resultSet.getInt(1));
	// s.setSchoolName(resultSet.getString(2));
	// s.setSchoolCode(resultSet.getString(3));
	// list.add(s);
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return list;
	// }
	//
	/**
	 * 查询某张表的所有记录数
	 * 
	 * @param tableName
	 *            表名
	 * @param condition
	 *            查询条件值
	 * @param name
	 *            字段名
	 * @return
	 */
	public int doQueryCount(String tableName, String condition, String name) {

		String[] params = {};
		String sql = "select count(*) from " + tableName + " where 1=1 ";
		if (condition != null) {
			sql = sql + " and " + name + "= ?";
			params = new String[] { condition };
		}
		ResultSet resultSet = null;
		System.out.println("所有记录sql： " + sql);
		resultSet = DBUtils.executeQuery(sql, params);
		int rs = 0;
		try {
			while (resultSet.next()) {
				rs = resultSet.getInt(1);
				System.out.println(tableName + "表，总共有： " + rs + " 条记录");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 查询所有患者病史类别
	 * 
	 * @return list 患者病史集合
	 */
	public List<Illness> doQueryIllness() {
		List<Illness> list = new ArrayList<>();
		String sql = "select a.*,b.name from TB_Illness as a,TB_PatientInformation as b"
				+ " where a.PatientInformation_iuid = b.IUID ";
		String[] params = {};
		ResultSet resultSet = null;
		resultSet = DBUtils.executeQuery(sql, params);
		try {
			while (resultSet.next()) {
				Illness illness = new Illness();
				illness.setIUID(resultSet.getString(1));
				// illness.setpatientInformation(resultSet.getString(1));
				// PatientInformation patientInformation1 = new
				// PatientInformation();
				PatientInformation patientInformation = new PatientInformation();
				// patientInformation1.setName(resultSet.getString(1));
				// illness.setPatientInformation(patientInformation1);
				patientInformation.setName(resultSet.getString(33));
				// illness.setPatientInformation(patientInformation);//患者姓名
				patientInformation.setIUID(resultSet.getString(2));
				illness.setPatientInformation(patientInformation);// 患者id
				illness.setSmoke(resultSet.getString(3));// 吸烟
				illness.setDrink(resultSet.getString(4));// 饮酒
				illness.setHighSalinity(resultSet.getString(5));// 嗜高盐饮食
				illness.setHighFat(resultSet.getString(6));// 嗜高脂饮食
				illness.setPiquancy(resultSet.getString(7));// 嗜辛辣饮食
				illness.setHighGlucose(resultSet.getString(8));// 嗜高糖饮食
				illness.setSleepQuality(resultSet.getString(9));// 每晚睡眠情况
				illness.setDiabetes(resultSet.getString(10));// 糖尿病史
				illness.setGlucoseControl(resultSet.getString(11));// 目前血糖控制情况
				illness.setHypertension(resultSet.getString(12));// 高血压病史
				illness.setHyperlipemia(resultSet.getString(13));// 高血脂症史
				illness.setCoronaryDisease(resultSet.getString(14));// 冠心病史
				illness.setShortCerebral(resultSet.getString(15));// 短暂性脑缺血发作史
				illness.setStroke(resultSet.getString(16));// 脑卒中史
				illness.setHeadInjury(resultSet.getString(17));// 头部外伤史
				illness.setMajorLifeEvents(resultSet.getString(18));/// 既往重大生活事件史
				illness.setOperation(resultSet.getString(19));// 手术史
				illness.setElseIllness(resultSet.getString(20));// 其他疾病史
				illness.setDrugAllergy(resultSet.getString(21));// 药物过敏史/不良反应史
				illness.setRelyOn(resultSet.getString(22));// 药物滥用或依赖史
				illness.setDailyExercise(resultSet.getString(23));// 本次病前日常运动习惯
				illness.setTeaHabit(resultSet.getString(24));// 本次病前有无喝茶习惯
				illness.setCoffeeHabit(resultSet.getString(25));// 本次病前有无喝咖啡习惯
				illness.setKeepingPets(resultSet.getString(26));// 本次病前有无饲养宠物习惯
				illness.setHobbiesInterests(resultSet.getString(27));// 其他常坚持的兴趣爱好如唱歌绘画打牌等
				illness.setMannerism(resultSet.getString(28));// 本次病前有无特殊生活习惯(如保健养生中医等)
				list.add(illness);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询影像学情况表
	 * 
	 * @return list 影像学情况集合
	 * @throws SQLException
	 */
	public List<Iconography> doQueryIconography() {
		List<Iconography> list = new ArrayList<>();
		ResultSet resultSet = null;
		String sql = "select a.*,b.name from TB_Iconography as a,TB_PatientInformation as b"
				+ " where a.PatientInformation_iuid = b.IUID ";
		String[] params = {};
		resultSet = DBUtils.executeQuery(sql, params);
		try {
			while (resultSet.next()) {
				Iconography m = new Iconography();
				PatientInformation mc = new PatientInformation();
				mc.setName(resultSet.getString(11));
				m.setPatientInformation(mc);
				mc.setIUID(resultSet.getString(2));// 患者ID
				m.setPatientInformation(mc);
				m.setHematencephalon(resultSet.getString(3));// 脑出血
				m.setBrainInfarction(resultSet.getString(4));// 脑梗塞
				m.setAngiography(resultSet.getString(5));// 血管造影
				m.setPresentation(resultSet.getString(6));// 影像报告
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询所有患者信息
	 * 
	 * @return
	 */
	public List<PatientInformation> doQueryPatientInformation() {
		String sql = "select a.* from TB_PatientInformation ";
		String[] params = {};
		List<PatientInformation> list = new ArrayList<>();
		ResultSet resultSet = null;
		resultSet = DBUtils.executeQuery(sql, params);
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					PatientInformation pf = new PatientInformation();
					pf.setIUID(resultSet.getString(1));// 患者ID
					pf.setName(resultSet.getString(2));// 姓名
					pf.setSex(resultSet.getString(3));// 性别
					pf.setBirthday(resultSet.getString(4));// 出生日期
					pf.setAge(resultSet.getString(5));
					pf.setHeight(resultSet.getString(6));
					pf.setWeight(resultSet.getString(7));
					pf.setRegisteredResidence(resultSet.getString(8));
					pf.setTel(resultSet.getString(9));
					pf.setAddress(resultSet.getString(10));
					pf.setPermanentAddress(resultSet.getString(11));
					pf.setNation(resultSet.getString(12));
					pf.setCulturalLevel(resultSet.getString(13));
					pf.setOccupation(resultSet.getString(14));
					pf.setWorkingStaus(resultSet.getString(15));
					pf.setJobNature(resultSet.getString(16));
					pf.setMaritalStatus(resultSet.getString(17));
					pf.setIsChild(resultSet.getString(18));
					pf.setLifeStyle(resultSet.getString(19));
					pf.setIndependece(resultSet.getString(20));
					pf.setMedicalInsurance(resultSet.getString(21));
					pf.setOther(resultSet.getString(22));
					pf.setReligiousBelief(resultSet.getString(23));

					list.add(pf);
				}
			} else {
				return list = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("没有数据。。。");
		}
		return list;
	}

	// /**
	// * 多条件查询学生信息
	// * @param idCard 身份证
	// * @param schoolName 学校名
	// * @return
	// */
	// public PageBean<PatientInformation>
	// doConditionQueryStudents(PageBean<PatientInformation> page,
	// String idCard, String schoolName) {
	// PageBean<PatientInformation> pageBean = new PageBean<>();
	// School school = new School();
	//
	// Map<Object, Object> map = new HashMap<>();
	// List<PatientInformation> list = new ArrayList<>();
	// int count = 0;
	// ResultSet resultSet = null;
	// String str = "";
	// if (idCard.equals("") && schoolName.equals("")) {
	//
	// pageBean = doPagedQueryStudents(page);
	//
	// return pageBean;
	//
	// } else if (idCard.equals("") && !schoolName.equals("")) {
	// System.out.println("身份证为空， 学校名不为空");
	// school = findSchoolByName(schoolName);
	// if (school == null) {
	// return null;
	// }
	// count = doQueryCount("TB_Students", Integer.toString(school.getIUID()),
	// "schoolIUID");
	// if (count == 0) {
	// return null;
	// }
	// str += " and schoolIUID = ?";
	// for (int i=0; i<=charCount(str, '?'); i++){
	// map.put("schoolIUID"+i, school.getIUID());
	// }
	// }else if (!idCard.equals("") && schoolName.equals("")) {
	// System.out.println("身份证不为空， 学校名为空");
	//
	// count = doQueryCount("TB_Students", idCard, "IDCard");
	// str += " and IDCard = ? " ;
	// for (int i=0; i<=charCount(str, '?'); i++){
	// map.put("IDCard"+i, idCard);
	// }
	// }else {
	// System.out.println("身份证不为空 "+idCard +" 学校名不为空 " + schoolName);
	// school = findSchoolByName(schoolName);
	//
	// if (school == null) {
	// return null;
	// }
	// count = doQueryCount("TB_Students", idCard, "IDCard");
	// str += "and a.IDCard = ? and a.schoolIUID = ? ";
	// for (int i=0; i<=charCount(str, '?'); i++){
	// map.put("IDCard"+i, idCard);
	// map.put("schoolIUID"+i, school.getIUID());
	// }
	//
	// }
	//
	// pageBean.setTotalCount(count);
	// if (page.getCurrentPage() <=0) {
	// pageBean.setCurrentPage(1); // 把当前页设置为1
	// } else if (page.getCurrentPage() > pageBean.getTotalPage()){
	// pageBean.setCurrentPage(pageBean.getTotalPage()); // 把当前页设置为最大页数
	// } else {
	// pageBean.setCurrentPage(page.getCurrentPage());
	// System.out.println("当前页数："+pageBean.getCurrentPage());
	// }
	// //查询表的所有记录数
	//
	// String s1 = "select top "+pageBean.getPageCount()+" a.*, b.schoolName
	// from TB_Students as a, "
	// + "TB_School as b where a.isEnable = 1 and 1=1 ";
	// String s2 = " and a.schoolIUID = b.IUID and a.IUID not in ("
	// + "select top "+pageBean.getPageCount()*(pageBean.getCurrentPage()-1)
	// +" a.IUID from TB_Students as a, TB_School as b where a.isEnable = 1 and
	// 1=1 and a.schoolIUID = b.IUID ";
	// String s3 = " order by a.iuid asc ) order by a.iuid asc";
	//
	// String sql = s1 + str + s2 +str + s3;
	// System.out.println("sql 语句： "+sql);
	// resultSet = DBUtils.executeQuery(sql, map);
	//
	//
	// try {
	// if (resultSet != null) {
	// while (resultSet.next()) {
	// Students students = new Students();
	// students.setIUID(resultSet.getInt(1));
	// students.setIDCard(resultSet.getString(2));
	// students.setStuName(resultSet.getString(3));
	// students.setStuNum(resultSet.getString(4));
	// students.setSchoolIUID(resultSet.getInt(5));
	// students.setRegistTime(resultSet.getString(6));
	// students.setCheckInTime(resultSet.getString(7));
	// students.setTotalScore(resultSet.getFloat(8));
	// students.setDisEnable(resultSet.getInt(9));
	// School s = new School();
	// s.setSchoolName(resultSet.getString(14));
	// students.setSchool(s);
	// list.add(students);
	// }
	//
	// pageBean.setPageData(list);
	// }else {
	// return null;
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// System.out.println("没有数据。。。");
	// }
	//
	//
	// return pageBean;
	// }
	//
	/**
	 * 分页查询所有学生信息
	 * 
	 * @param page
	 * @return
	 */
	public PageBean<PatientInformation> doPagedQueryPatientInformation(PageBean<PatientInformation> page) {

		PageBean<PatientInformation> pageBean = new PageBean<>();
		// 查询表的所有记录数
		int count = doQueryCount("TB_PatientInformation", null, null);
		pageBean.setTotalCount(count);
		// 判断当前页数
		if (page.getCurrentPage() <= 0) {
			pageBean.setCurrentPage(1); // 把当前页设置为1
		} else if (page.getCurrentPage() > pageBean.getTotalPage()) {
			pageBean.setCurrentPage(pageBean.getTotalPage()); // 把当前页设置为最大页数
		} else {
			pageBean.setCurrentPage(page.getCurrentPage());
		}
		String sql = "";
		List<PatientInformation> list = new ArrayList<>();
		String[] params = {};
		sql = "select top " + pageBean.getPageCount() + " a.* from TB_PatientInformation as a "
				+ "where a.iuid not in (" + "select top " + pageBean.getPageCount() * (pageBean.getCurrentPage() - 1)
				+ " a.iuid from TB_PatientInformation as a ) order by a.iuid asc	";
		ResultSet resultSet = null;
		System.out.println("分页 ：" + pageBean.getPageCount() + "\n" + sql);
		resultSet = DBUtils.executeQuery(sql, params);
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					PatientInformation pf = new PatientInformation();
					pf.setIUID(resultSet.getString(1));// 患者ID
					pf.setName(resultSet.getString(2));// 姓名
					pf.setSex(resultSet.getString(3));// 性别
					pf.setBirthday(resultSet.getString(4));// 出生日期
					pf.setAge(resultSet.getString(5));
					pf.setHeight(resultSet.getString(6));
					pf.setWeight(resultSet.getString(7));
					pf.setRegisteredResidence(resultSet.getString(8));
					pf.setTel(resultSet.getString(9));
					pf.setAddress(resultSet.getString(10));
					pf.setPermanentAddress(resultSet.getString(11));
					pf.setNation(resultSet.getString(12));
					pf.setCulturalLevel(resultSet.getString(13));
					pf.setOccupation(resultSet.getString(14));
					pf.setWorkingStaus(resultSet.getString(15));
					pf.setJobNature(resultSet.getString(16));
					pf.setMaritalStatus(resultSet.getString(17));
					pf.setIsChild(resultSet.getString(18));
					pf.setLifeStyle(resultSet.getString(19));
					pf.setIndependece(resultSet.getString(20));
					pf.setMedicalInsurance(resultSet.getString(21));
					pf.setOther(resultSet.getString(22));
					pf.setReligiousBelief(resultSet.getString(23));
					list.add(pf);
				}

				pageBean.setPageData(list);
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("没有数据。。。");
		}
		return pageBean;
	}

	//
	// /**
	// * 添加多个学生
	// * @param list
	// */
	// public int addStudentsForList(List<Object> list) {
	// // TODO Auto-generated method stub
	// int rs = 0;
	// for (int i=0; i<list.size(); i++) {
	// ExcelStudents es = (ExcelStudents)list.get(i);
	// School s = findSchoolByName(es.getSchoolName());
	// if (s != null) {
	// String sql = "insert TB_Students(IDCard, stuName, stuNum, schoolIUID, " +
	// "totalScore, IsEnable, CalendarYear) values (?, ?, ?, ?, ?, ?, ?)";
	// String[] params = {es.getIdCard(), es.getStuName(), es.getStuNum(),
	// Integer.toString(s.getIUID()), "0", "1", es.getSkill1()};
	// rs = DBUtils.executeUpdate(sql, params);
	// if (rs != 1) {
	// System.out.println("第" + i + "数据添加不成功。");
	// }
	// }
	// rs = 1;
	// }
	// return rs;
	// }
	//
	// /**
	// * 根据学校名查询学校
	// * @param sn
	// * @return
	// * @throws SQLException
	// */
	// public School findSchoolByName(String sn){
	// School s = null;
	// if (sn == null || sn.equals("")) {
	// System.out.println("学校名为空。。。。");
	// sn = "";
	// }
	// String sql = "select * from TB_school where IsEnable = 1 and schoolName =
	// ?";
	// String[] params = {sn};
	// ResultSet resultSet = null;
	//
	// resultSet = DBUtils.executeQuery(sql, params);
	// try {
	// while (resultSet.next()) {
	// s = new School();
	// s.setIUID(resultSet.getInt(1));
	// s.setSchoolName(resultSet.getString(2));
	// s.setSchoolCode(resultSet.getString(3));
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// System.out.println("没有该学校");
	// }
	// return s;
	// }
	//
	// /**
	// * 根据学校IUID查询学生信息
	// * @param schoolIUID 学校IUID
	// * @return
	// */
	// public List<Students> doQueryStudentsBySchoolIUID(int schoolIUID) {
	//// System.out.println("学校的IUID："+schoolIUID);
	// String sql = "select a.* from TB_Students as a where a.IsEnable = 1 and
	// a.schoolIUID = ?";
	// String[] params = {Integer.toString(schoolIUID)};
	// List<Students> list = new ArrayList<>();
	// ResultSet resultSet = null;
	// resultSet = DBUtils.executeQuery(sql, params);
	// try {
	// if (resultSet != null) {
	// while (resultSet.next()) {
	// Students students = new Students();
	// students.setIUID(resultSet.getInt(1));
	// students.setIDCard(resultSet.getString(2));
	// students.setStuName(resultSet.getString(3));
	// students.setStuNum(resultSet.getString(4));
	// students.setSchoolIUID(resultSet.getInt(5));
	// students.setRegistTime(resultSet.getString(6));
	// students.setCheckInTime(resultSet.getString(7));
	// students.setTotalScore(resultSet.getFloat(8));
	// students.setDisEnable(resultSet.getInt(9));
	// students.setCalendarYear(resultSet.getString(10));
	// list.add(students);
	// }
	// }else {
	// return list=null;
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// System.out.println("没有数据。。。");
	// }
	// return list;
	// }
	//
	// /**
	// * 统计字符出现的次数
	// * @param s
	// * @return
	// */
	// public int charCount(String s, char c){
	// int num=0;
	// for (int i=0;i<s.length();i++)
	// {
	// // 获取每个字符，判断是否是字符a
	// if (s.charAt(i)==c) {
	// // 累加统计次数
	// num++;
	// }
	// }
	// return num;
	// }
	//
	/**
	 * 通过身份证查询患者信息
	 * 
	 * @param idCard
	 * @return
	 */
	public List<PatientInformation> doQueryStudentsByIDCard(String iuid) {

		String sql = "select * from TB_PatientInformation where 1=1 and iuid = ?";
		String[] params = { iuid };
		List<PatientInformation> list = null;
		ResultSet resultSet = null;
		resultSet = DBUtils.executeQuery(sql, params);
		System.out.println("resultSet  " + resultSet.toString());

		System.out.println("is here..");

		try {
			list = new ArrayList<>();
			while (resultSet.next()) {

				PatientInformation pf = new PatientInformation();
				pf.setIUID(resultSet.getString(1));// 患者ID
				System.out.println("IUID:   " + resultSet.getString(1));
				pf.setName(resultSet.getString(2));// 姓名
				pf.setSex(resultSet.getString(3));// 性别
				pf.setBirthday(resultSet.getString(4));// 出生日期
				pf.setAge(resultSet.getString(5));
				pf.setHeight(resultSet.getString(6));
				pf.setWeight(resultSet.getString(7));
				pf.setRegisteredResidence(resultSet.getString(8));
				pf.setTel(resultSet.getString(9));
				pf.setAddress(resultSet.getString(10));
				pf.setPermanentAddress(resultSet.getString(11));
				pf.setNation(resultSet.getString(12));
				pf.setCulturalLevel(resultSet.getString(13));
				pf.setOccupation(resultSet.getString(14));
				pf.setWorkingStaus(resultSet.getString(15));
				pf.setJobNature(resultSet.getString(16));
				pf.setMaritalStatus(resultSet.getString(17));
				pf.setIsChild(resultSet.getString(18));
				pf.setLifeStyle(resultSet.getString(19));
				pf.setIndependece(resultSet.getString(20));
				pf.setMedicalInsurance(resultSet.getString(21));
				pf.setOther(resultSet.getString(22));
				pf.setReligiousBelief(resultSet.getString(23));
				list.add(pf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	// /**
	// * 更新学生信息表中的报到、检录时间
	// * @param iUID
	// * @param registerTime
	// * @param checkInTime
	// * @return
	// */
	// public int ajaxUpRegisterAndCheckInTimeByIUID(String iUID, String
	// registerTime, String checkInTime) {
	//
	// String sql = "";
	// String [] params = {};
	// int result = 0;
	// if (registerTime.equals("")) {
	// sql = "update TB_Students set checkInTime = ? where IUID = ? and IsEnable
	// = 1";
	// params = new String[]{checkInTime, iUID};
	// } else if (checkInTime.equals("")) {
	// sql = "update TB_Students set registTime = ? where IUID = ? and IsEnable
	// = 1";
	// params = new String[]{registerTime, iUID};
	// }
	//
	// result = DBUtils.executeUpdate(sql, params);
	// if (result == 1) {
	// return 1;
	// }
	// return 0;
	// }
	//
	// /**
	// * 汇总学生成绩
	// * @return
	// */
	// public List<CollectScore> doCollectScore() {
	//
	// String sql = "select a.IUID, a.stuName, avg(c.score) as allscore "
	// + " from TB_Students as a "
	// + " inner join TB_School as b on a.schoolIUID = b.IUID"
	// + " left join TB_Score as c on a.IUID = c.StudentsIUID "
	// + " group by StudentsIUID, a.IUID, a.stuName order by a.iuid ";
	// String[] params = {};
	// ResultSet resultSet = null;
	// List<CollectScore> list = new ArrayList<>();
	// resultSet = DBUtils.executeQuery(sql, params);
	// if (resultSet == null) {
	// return null;
	// }else {
	// try {
	// while (resultSet.next()) {
	// CollectScore cs = new CollectScore();
	// cs.setStudentsIUID(resultSet.getInt(1));
	// cs.setStuName(resultSet.getString(2));
	//// System.out.println("获得的成绩： "+resultSet.getFloat(3));
	// cs.setTotalScore(resultSet.getFloat(3));
	// list.add(cs);
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return list;
	// }
	//
	// /**
	// * 根据学生IUID更新学生信息表中的totalScore
	// * @param studentsIUID
	// * @param totalScore
	// * @return
	// */
	// public int doUpdateStuScoreByIUID(int studentsIUID, double totalScore) {
	// String sql = "update TB_Students set totalScore = ? where IUID = ? and
	// IsEnable = 1";
	// String[] params = {Double.toString(totalScore),
	// Integer.toString(studentsIUID)};
	// int result = 0;
	// result = DBUtils.executeUpdate(sql, params);
	// if (result == 1) {
	// return 1;
	// }
	// return 0;
	// }
	//
	/**
	 * 查询患者成绩
	 * 
	 * @param idCard
	 * @param schoolName
	 * @return
	 */
	public List<Information> doAUpGrade(String round, String iuid) {
		String idC = " 1 = 1 ";
		String[] params = {};
		if (!idC.equals("")) {
			idC += " and TB_PatientInformation.iuid = ? AND TB_Record.round = ?";
			params = new String[] { iuid , round};
		}
		String s1 = "SELECT  TB_PatientInformation.name, TB_Record.round, "
				+ "TB_Questionnaire.title, TB_Grade.grade, TB_Record.start_time, "
				+ "TB_Record.end_time,TB_Grade.record_id,TB_PatientInformation.sex, "
				+ "TB_PatientInformation.birthday, TB_PatientInformation.age, TB_PatientInformation.iuid "
				+ "FROM  TB_Questionnaire INNER JOIN TB_Record ON TB_Questionnaire.iuid = TB_Record.questionnaire_iuid INNER JOIN "
				+ "TB_Grade ON TB_Record.iuid = TB_Grade.record_id INNER JOIN "
				+ "TB_PatientInformation ON TB_Grade.PatientInformation_iuid = TB_PatientInformation.iuid WHERE ";
		String sql = s1 + idC;
		System.out.println("查询成绩：   "+sql);
		List<Information> list = new ArrayList<>();
		ResultSet resultSet = null;
		resultSet = DBUtils.executeQuery(sql, params);
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					Information in = new Information();
					in.setIUID(resultSet.getString(11));
					in.setName(resultSet.getString(1));
					in.setRound(resultSet.getString(2));
					in.setTitle(resultSet.getString(3));
					in.setGrade(resultSet.getString(4));
					in.setStartTime(resultSet.getString(5));
					in.setEndTime(resultSet.getString(6));
					in.setBirthday(resultSet.getString(7));
					in.setRecordID(resultSet.getString(8));
					in.setSex(resultSet.getString(9));
					in.setAge(resultSet.getString(10));
					list.add(in);
				}
			} else {
				return list = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("没有数据。。。");
		}
		return list;
	}

	/**
	 * 查询患者成绩
	 * 
	 * @param idCard
	 * @param schoolName
	 * @return
	 */
	public List<Grade> doQueryGrade(String iuid) {
		String idC = "";
		String[] params = {};
		if (!idC.equals("")) {
			idC = " and iuid = ? ";
			params = new String[] { iuid };
		}
		String s1 = "select a.*,b.name,c.*,d.* from TB_Grade as a, TB_PatientInformation as b,TB_Record as c,TB_Questionnaire as d  where a.PatientInformation_iuid = b.IUID and a.record_id=c.iuid and c.Questionnaire_iuid=d.iuid";
		String sql = s1 + idC;
		DBUtils.executeQuery(sql, params);
		List<Grade> list = new ArrayList<>();
		ResultSet resultSet = null;
		resultSet = DBUtils.executeQuery(sql, params);
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					Grade n = new Grade();
					PatientInformation patientInformation = new PatientInformation();
					patientInformation.setName(resultSet.getString(10));
					n.setPatientInformation(patientInformation);// 患者姓名
					patientInformation.setIUID(resultSet.getString(2));
					n.setPatientInformation(patientInformation);// 患者ID
					n.setGrade(resultSet.getString(4));// 成绩
					Record c = new Record();
					c.setRound(resultSet.getString(13));
					n.setRecord(c);// 轮次
					Questionnaire questionnaire = new Questionnaire();
					questionnaire.setIUID(resultSet.getString(20));
					n.setQuestionnaire(questionnaire);// 问卷Id
					questionnaire.setTitle(resultSet.getString(21));
					n.setQuestionnaire(questionnaire);// 问卷标题
					n.setStartTime(resultSet.getString(5)); // 录入时间
					list.add(n);
				}
			} else {
				return list = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("没有数据。。。");
		}
		return list;
	}

	/**
	 * 添加多个患者
	 * 
	 * @param list
	 */
	public int addPFForList(List<Object> list) {
		// TODO Auto-generated method stub
		int rs = 0;

		for (int i = 0; i < list.size(); i++) {
			ExcelPatientInformation pf = (ExcelPatientInformation) list.get(i);

			if (!pf.getIUID().equals("")) {
				String sql = "insert into TB_PatientInformation(iuid, name, sex, birthday, " + "age, height, weight,"
						+ "registered_residence,tel,address,permanent_address,nation,"
						+ "cultural_level,occupation,working_staus,job_nature,"
						+ "marital_status,is_child,life_style,independece,"
						+ "medical_insurance,other,religious_belief) "
						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				String[] params = { pf.getIUID(), pf.getName(), pf.getSex(), pf.getBirthday(), pf.getAge(),
						pf.getHeight(), pf.getWeight(), pf.getRegisteredResidence(), pf.getTel(), pf.getAddress(),
						pf.getPermanentAddress(), pf.getNation(), pf.getCulturalLevel(), pf.getOccupation(),
						pf.getWorkingStaus(), pf.getJobNature(), pf.getMaritalStatus(), pf.getIsChild(),
						pf.getLifeStyle(), pf.getIndependece(), pf.getMedicalInsurance(), pf.getOther(),
						pf.getReligiousBelief() };
				rs = DBUtils.executeUpdate(sql, params);

				if (rs != 1) {
					System.out.println("第" + i + "数据添加不成功。");
				}
			}
			rs = 1;
		}
		return rs;
	}

	/**
	 * 添加多个患者病史
	 * 
	 * @param list
	 */
	public int addIllnessForList(List<Object> list) {
		// TODO Auto-generated method stub
		int rs = 0;
		for (int i = 0; i < list.size(); i++) {
			ExcelIllness il = (ExcelIllness) list.get(i);
			if (!il.getIUID().equals("")) {
				String sql = "insert into TB_Illness(patientInformation_iuid,smoke,"
						+ "drink,high_salinity,high_fat,piquancy,high_glucose,sleep_quality,"
						+ "diabetes,glucose_control,hypertension,hyperlipemia,coronary_disease,"
						+ "short_cerebral,stroke,head_injury,major_life_events,operation,"
						+ "else_illness,drug_allergy,rely_on,daily_exercise,tea_habit,"
						+ "coffee_habit,keeping_pets,hobbies_interests,mannerism) "
						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?,?)";
				String[] params = { il.getIUID(), il.getSmoke(), il.getDrink(), il.getHighSalinity(), il.getHighFat(),
						il.getPiquancy(), il.getHighGlucose(), il.getSleepQuality(), il.getDiabetes(),
						il.getGlucoseControl(), il.getHypertension(), il.getHyperlipemia(), il.getCoronaryDisease(),
						il.getShortCerebral(), il.getStroke(), il.getHeadInjury(), il.getMajorLifeEvents(),
						il.getOperation(), il.getElseIllness(), il.getDrugAllergy(), il.getRelyOn(),
						il.getDailyExercise(), il.getTeaHabit(), il.getCoffeeHabit(), il.getKeepingPets(),
						il.getHobbiesInterests(), il.getMannerism() };
				rs = DBUtils.executeUpdate(sql, params);
				if (rs != 1) {
					System.out.println("第" + i + "数据添加不成功。");
				}
			}
			rs = 1;
		}
		return rs;
	}

	/**
	 * 添加多个影像学
	 * 
	 * @param list
	 */
	public int addIconographyForList(List<Object> list) {
		// TODO Auto-generated method stub
		int rs = 0;
		for (int i = 0; i < list.size(); i++) {
			ExcelIconography ic = (ExcelIconography) list.get(i);
			// System.out.println("获取到的影像学IUID:"+ic.getIUID());
			if (!ic.getIUID().equals("")) {
				String sql = "insert into TB_Iconography(patientInformation_iuid,hematencephalon,"
						+ "brain_infarction,angiography,Presentation) " + "values (?, ?, ?, ? ,?)";
				String[] params = { ic.getIUID(), ic.getHematencephalon(), ic.getBrainInfarction(), ic.getAngiography(),
						ic.getPresentation() };
				rs = DBUtils.executeUpdate(sql, params);
				if (rs != 1) {
					System.out.println("第" + i + "数据添加不成功。");
				}
			}
			rs = 1;
		}
		return rs;
	}

	/**
	 * 查询条件问卷
	 * 
	 * @param list
	 */
	public List<String> doQueryQuestionnaire(List<String> list) {
		// TODO Auto-generated method stub
		// 返回查到的问卷ID
		List<String> ID = new ArrayList<>();
		for (int i = 2; i < list.size(); i++) {// 从2开始跳过患者ID和轮次拿到问卷名
			if (list.get(i) != null && !list.get(i).isEmpty()) {
				String[] params = { list.get(i) };
				String sql = "select iuid from TB_Questionnaire where title=?";
				ResultSet resultSet = null;
				// System.out.println(list.get(i));
				resultSet = DBUtils.executeQuery(sql, params);
				// System.out.println(list.get(i));
				try {
					while (resultSet.next()) {
						ID.add(resultSet.getString(1));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ID;
	}

	/**
	 * 导入Excel成绩
	 * @param list
	 */
	public List<Integer> doAddGrade(List<List<String>> list) {
		//拿到问卷ID
		List<String> qId = doQueryQuestionnaire(list.get(0));
		List<List<String>> rId = new ArrayList<>();
		List<List<String>> grade = new ArrayList<>();
		List<List<String>> pId = new ArrayList<>();
 		System.out.println(list.get(1));
		for(int i = 1;i < list.size();i++){//从1开始去掉表头
			rId.add(doRecordID(list.get(i), qId));//拿到所有的记录ID
		}
		for(int i = 1;i < list.size();i++){//从1开始去掉表头
			grade.add(doAllGrade(list.get(i)));//拿到所有的成绩
		}
		for(int i = 1;i < list.size();i++){//从1开始去掉表头
			pId.add(doPatientInformationId(list.get(i)));
		}
		System.out.println("个人的身份证:" + pId.toString());
//		System.out.println("dan grade: "+grade.get(0));//取出单个grade
		
		for(int i = 0;i < pId.size();i++){//根据获取的身份证个数保存成绩
			if(pId.get(i)!=null && !pId.get(i).isEmpty()){
				for(int j = 0;j < rId.get(i).size();j++){//每个记录Id所对应的问卷的成绩
					int rs = 0;
					String sql = "insert into TB_Grade(PatientInformation_iuid,record_id,grade) "
							+ "values (?, ? ,?)";
					String[] params = {pId.get(i).get(0),rId.get(i).get(j),grade.get(i).get(j)};
					System.out.println("PID : "+pId.get(i).get(0)
							+"   RID: " + rId.get(i).get(j)+ "   Grade " + grade.get(i).get(j));
					rs = DBUtils.executeUpdate(sql, params);
					if (rs != 1) {
						System.out.println("第" + i + "数据添加不成功。");	
					}
				}
			}
		}
		
//		for (int i=0; i<rId.size(); i++) {//取出每个集合
//			for(int j = 0;j<rId.get(i).size();j++){//根据每条记录ID写入成绩
//				int rs = 0;
//				String sql = "insert into TB_Iconography(PatientInformation_iuid,record_id) "
//						+ "values (?, ?)";
//				String[] params = {};
//				rs = DBUtils.executeUpdate(sql, params);
//				if (rs != 1) {
//					System.out.println("第" + i + "数据添加不成功。");	
//				}
//			}
//		}
//		System.out.println("grade:"+grade.toString());
//		System.out.println("rID："+rId.toString());
		return null;
	}

	/**
	 * 查询获取记录ID
	 * 
	 * @param list
	 */
	public List<String> doRecordID(List<String> list, List<String> qId) {
		// TODO Auto-generated method stub
		// 返回查到的问卷ID
		int rs = 0;
		List<String> rId = new ArrayList<>();
		for (int i = 0; i < qId.size(); i++) {
			if (!list.get(1).equals("")) {
				String sql = "insert into TB_Record(questionnaire_iuid,round) " + "values (?, ?)";
				String[] params = { qId.get(i), list.get(1) };
				rs = DBUtils.executeUpdate(sql, params);
				if (rs != 1) {
					System.out.println("第" + i + "数据添加不成功。");
				}
				String[] param = {};
				String sq = "select top 1 * from TB_Record ORDER BY iuid desc";
				ResultSet resultSet = null;
				// System.out.println(list.get(i));
				resultSet = DBUtils.executeQuery(sq, param);
				// System.out.println(list.get(i));
				try {
					while (resultSet.next()) {
						rId.add(resultSet.getString(1));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return rId;
	}

	/**
	 * 查询获取记录ID
	 * 
	 * @param list
	 */
	public List<String> doAllGrade(List<String> list) {
		// System.out.println("ALLGrade:" + list);
		List<String> grade = new ArrayList<>();
		for (int i = 2; i < list.size(); i++) {// 从2开始，去掉患者ID和轮次
			grade.add(list.get(i));
		}
		return grade;
	}
	
	/**
	 * 查询获取单个患者ID
	 * 
	 * @param list
	 */
	public List<String> doPatientInformationId(List<String> list) {
		// System.out.println("ALLGrade:" + list);
		List<String> pId = new ArrayList<>();
		pId.add(list.get(0));
		return pId;
	}
	
}
