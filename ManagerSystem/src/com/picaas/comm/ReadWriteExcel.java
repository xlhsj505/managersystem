package com.picaas.comm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hslf.util.SystemTimeUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.picaas.manager.entity.ExcelGrade;
import com.picaas.manager.entity.ExcelIconography;
import com.picaas.manager.entity.ExcelIllness;
import com.picaas.manager.entity.ExcelPatientInformation;
import com.sun.xml.internal.ws.handler.HandlerException;


public class ReadWriteExcel {
	
	/**
	 * ��ȡexcel�ļ�����
	 * @param request
	 * @param response
	 * @param filename  excel�ļ��ڷ������е�·��
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<Object> getPFData(HttpServletRequest request, 
			HttpServletResponse response, String filename) 
			throws FileNotFoundException {
		String errMessage = null;
		boolean flags = false;
		List<Object> list = new ArrayList<>();
		
		java.io.File f = new java.io.File(request.getServletContext().getRealPath("/upload")+"/"+filename);
		InputStream is = new FileInputStream(f);
		
		try {
			//����������
			Workbook wb = new XSSFWorkbook(is);
			for (int i = 0, len = wb.getNumberOfSheets(); i < len; i++) {
				//����������
				Sheet sheet = wb.getSheetAt(i);
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {// ��j=1��ʾ��ȥ����һ��
					if (sheet == null) {
                        return null;
                    }
					//��ȡexcel�� ��
					Row row = sheet.getRow(j);
					if (row == null) {
                        return null;
                    }
					System.out.println("����������  "+row.getLastCellNum());
					ExcelPatientInformation pf = new ExcelPatientInformation();
					// ��ȡÿһ����Ԫ��
                    for (int k = 0; k < 23; k++) {
                    	Cell cell = row.getCell(k);
                    	String str;
                    	if (cell == null) {
                            str = "";
                        }else {
                        	str = getValue(cell);
						}	
                    	
                           	if (k == 0) {
                        		pf.setIUID(str);
                        		System.out.println(str);
							} else if (k == 1) {
								pf.setName(str);
                        		System.out.println(str);
							} else if (k == 2) {
								pf.setSex(str);
                        		System.out.println(str);
							} else if (k == 3) {	
								pf.setBirthday(str);
							} else if (k == 4) {
								//String[] s = str.split("\\.");
								pf.setAge(str);	
							} else if (k == 5) {
								pf.setHeight(str);
							} else if (k == 6) {
								pf.setWeight(str);
							} else if (k == 7) {
								pf.setRegisteredResidence(str);
							} else if (k == 8) {
								pf.setTel(str);	
							} else if (k == 9) {
								pf.setAddress(str);
							} else if (k == 10) {
								pf.setPermanentAddress(str);
							} else if(k == 11){
								pf.setNation(str);
							} else if(k == 12){
								pf.setCulturalLevel(str);
							} else if(k == 13){
								pf.setOccupation(str);
							} else if(k == 14){
								pf.setWorkingStaus(str);
							} else if(k == 15){
								pf.setJobNature(str);
							} else if(k == 16){
								pf.setMaritalStatus(str);
							} else if(k == 17){
								pf.setIsChild(str);
							} else if(k == 18){
								pf.setLifeStyle(str);
							} else if(k == 19){
								pf.setIndependece(str);
							} else if(k == 20){
								pf.setMedicalInsurance(str);
							} else if(k == 21){
								pf.setOther(str);
							} else if(k == 22){
								pf.setReligiousBelief(str);
							}
							
//						}
                    		
                    }
                    list.add(pf);
				}
//				System.out.println("����ѧ�������У� "+list.size());
			}
			
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//��ȡ��Ԫ��ֵ�ķ��� 
	private String getValue(Cell cell){
		String result = "";
		switch(cell.getCellType()){
		case BOOLEAN:
			result = cell.getBooleanCellValue() + "";
            break;
		case STRING:
			result = cell.getStringCellValue();
			break;
        case FORMULA:
        	result = cell.getCellFormula();
        	break;
        case NUMERIC:
        	//��������ͨ���֣�Ҳ����������
        	if (HSSFDateUtil.isCellDateFormatted(cell)) {
				result = DateUtil.getJavaDate(cell.getNumericCellValue()).toString();
			}else {
				result = cell.getNumericCellValue()+"";
			}
        	break;
		default:
			break;
		}
		return result;
	}
	
	
	/**
     * ����Excel
     * @param sheetName sheet����
     * @param title ����
     * @param values ����
     * @param wb HSSFWorkbook����
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,
    		String []title,
    		String [][]values, HSSFWorkbook wb){

        // ��һ��������һ��HSSFWorkbook����Ӧһ��Excel�ļ�
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // �ڶ�������workbook�����һ��sheet,��ӦExcel�ļ��е�sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth((short) 18);// ���ñ��Ĭ���п��Ϊ20���ֽ�
        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������
        HSSFRow row = sheet.createRow(0);

        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // ����һ�����и�ʽ

        //�����ж���
        HSSFCell cell = null;

        //��������
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //��������
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //�����ݰ�˳�򸳸���Ӧ���ж���
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

//    /**
//     * ��ѧУ�����ɼ���
//     * @param array �洢sheet���Ƶ�����
//     * @param title ����
//     * @param sLists ����
//     * @param wb HSSFWorkbook����
//     * @return
//     */
//    public static HSSFWorkbook getHSSFWorkbookForList(String[] array,
//    		String []title,
//    		List<List<Students>> sLists, HSSFWorkbook wb){
//
//        // ��һ��������һ��HSSFWorkbook����Ӧһ��Excel�ļ�
//        if(wb == null){
//            wb = new HSSFWorkbook();
//        }
//        int index = 0;
//        // �ڶ�������workbook�����һ��sheet,��ӦExcel�ļ��е�sheet
//        for (int k=0; k<array.length;k++) {
//        	HSSFSheet sheet = wb.createSheet(array[k]);
//        	// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������
//        	sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));//�ϲ���Ԫ�񣨿�ʼ�У������У���ʼ�У������У�
//        	sheet.setDefaultColumnWidth((short) 20);// ���ñ��Ĭ���п��Ϊ20���ֽ�
//            HSSFRow row = sheet.createRow(0);
//            row.createCell(0).setCellValue(array[k]+"�ɼ���");
//            // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
//            HSSFCellStyle style = wb.createCellStyle();
//            style.setAlignment(HorizontalAlignment.CENTER); // ����һ�����и�ʽ
//    		// ����һ������
//    		HSSFFont font = wb.createFont();
//    		font.setFontHeightInPoints((short) 12);
//    		
//    		// ������Ӧ�õ���ǰ����ʽ
//    		style.setFont(font);
//            
//            //�����ж���
//            HSSFCell cell = null;
//            row = sheet.createRow(1);
//            //��������
//            for(int i=0;i<title.length;i++){	
//                cell = row.createCell(i);
//                cell.setCellValue(title[i]);
//                cell.setCellStyle(style);
//            }
//
//            //��������
//         // �����������ݣ�����������
//
//    		if (sLists != null) {    				
//				List<Students> list = sLists.get(k);
//				for (int j = 0; j < list.size(); j++) {
//					row = sheet.createRow(j+2);
//					Students students = list.get(j);
//					for (int m = 0; m < title.length; m++) {
//						cell = row.createCell(m);
//						switch (m) {
//						case 0:
//							cell.setCellValue(students.getStuName());
//							break;
//						case 1:
//							cell.setCellValue(students.getIDCard());
//							break;
//						case 2:
//							cell.setCellValue(students.getCalendarYear());
//							break;
//						case 3:
//							cell.setCellValue(students.getTotalScore());
//							break;
//						}
//					}
//				}
//       		}
//		}
//        
//       
//        return wb;
//    }
	/**
	 * ��ȡ���߲�ʷexcel�ļ�����
	 * @param request
	 * @param response
	 * @param filename  excel�ļ��ڷ������е�·��
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<Object> getIllnessData(HttpServletRequest request, 
			HttpServletResponse response, String filename) 
			throws FileNotFoundException {
		String errMessage = null;
		boolean flags = false;
		List<Object> list = new ArrayList<>();
		
		java.io.File f = new java.io.File(request.getServletContext().getRealPath("/upload")+"/"+filename);
		InputStream is = new FileInputStream(f);
		
		try {
			//����������
			Workbook wb = new XSSFWorkbook(is);
			for (int i = 0, len = wb.getNumberOfSheets(); i < len; i++) {
				//����������
				Sheet sheet = wb.getSheetAt(i);
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {// ��j=1��ʾ��ȥ����һ��
					if (sheet == null) {
                        return null;
                    }
					//��ȡexcel�� ��
					Row row = sheet.getRow(j);
					if (row == null) {
                        return null;
                    }
					System.out.println("����������  "+row.getLastCellNum());
					ExcelIllness in = new ExcelIllness();
					// ��ȡÿһ����Ԫ��
                    for (int k = 0; k < 28; k++) {
                    	Cell cell = row.getCell(k);
                    	String str;
                    	if (cell == null) {
                            str = "";
                        }else {
                        	str = getValue(cell);
						}	
                           	if (k == 0) {
                           		System.out.println(str);
                        		in.setIUID(str);
                        		System.out.println(str);
							} else if (k == 1) {
								in.setSmoke(str);
                        		System.out.println(str);
							} else if (k == 2) {
								in.setDrink(str);
                        		System.out.println(str);
							} else if (k == 3) {
								in.setHighSalinity(str);
							} else if (k == 4) {
								in.setHighFat(str);
							} else if (k == 5) {
								in.setPiquancy(str);
							} else if (k == 6) {
								in.setHighGlucose(str);
							} else if (k == 7) {
								in.setSleepQuality(str);
							} else if (k == 8) {
								in.setDiabetes(str);
							} else if (k == 9) {
								in.setGlucoseControl(str);
							} else if (k == 10) {
								in.setHypertension(str);
							} else if(k == 11){
								in.setHyperlipemia(str);
							} else if(k == 12){
								in.setCoronaryDisease(str);
							} else if(k == 13){
								in.setShortCerebral(str);
							} else if(k == 14){
								in.setStroke(str);
							} else if(k == 15){
								in.setHeadInjury(str);
							} else if(k == 16){
								in.setMajorLifeEvents(str);
							} else if(k == 17){
								in.setOperation(str);
							} else if(k == 18){
								in.setElseIllness(str);
							} else if(k == 19){
								in.setDrugAllergy(str);
							} else if(k == 20){
								in.setRelyOn(str);
							} else if(k == 21){
								in.setDailyExercise(str);
							} else if(k == 22){
								in.setTeaHabit(str);
							} else if(k == 23){
								in.setCoffeeHabit(str);
							} else if(k == 24){
								in.setKeepingPets(str);
							} else if(k == 25){
								in.setHobbiesInterests(str);
							} else if(k == 26){
								in.setMannerism(str);
							}
							
//						}
                    		
                    }
                    list.add(in);
				}
//				System.out.println("����ѧ�������У� "+list.size());
			}
			
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ��ȡӰ��ѧexcel�ļ�����
	 * @param request
	 * @param response
	 * @param filename  excel�ļ��ڷ������е�·��
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<Object> getIconographyData(HttpServletRequest request, 
			HttpServletResponse response, String filename) 
			throws FileNotFoundException {
		String errMessage = null;
		boolean flags = false;
		List<Object> list = new ArrayList<>();
		
		java.io.File f = new java.io.File(request.getServletContext().getRealPath("/upload")+"/"+filename);
		InputStream is = new FileInputStream(f);
		
		try {
			//����������
			Workbook wb = new XSSFWorkbook(is);
			for (int i = 0, len = wb.getNumberOfSheets(); i < len; i++) {
				//����������
				Sheet sheet = wb.getSheetAt(i);
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {// ��j=1��ʾ��ȥ����һ��
					if (sheet == null) {
                        return null;
                    }
					//��ȡexcel�� ��
					Row row = sheet.getRow(j);
					if (row == null) {
                        return null;
                    }
					System.out.println("����������  "+row.getLastCellNum());
					ExcelIconography ic = new ExcelIconography();
					// ��ȡÿһ����Ԫ��
                    for (int k = 0; k < 23; k++) {
                    	Cell cell = row.getCell(k);
                    	String str;
                    	if (cell == null) {
                            str = "";
                        }else {
                        	str = getValue(cell);
						}	
                           	if (k == 0) {
                        		ic.setIUID(str);
//                        		System.out.println(str);
							} else if (k == 1) {
								ic.setHematencephalon(str);
//                        		System.out.println(str);
							} else if (k == 2) {
								ic.setBrainInfarction(str);
//                        		System.out.println(str);
							} else if (k == 3) {
								ic.setAngiography(str);
							} else if (k == 4) {
								ic.setPresentation(str);
							}
//						}	
                    }
                    list.add(ic);
				}
//				System.out.println("����ѧ�������У� "+list.size());
			}
			
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ��ȡ���߳ɼ�excel�ļ�����
	 * @param request
	 * @param response
	 * @param filename  excel�ļ��ڷ������е�·��
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<ExcelGrade> getGradeData(HttpServletRequest request, 
			HttpServletResponse response, String filename) 
			throws FileNotFoundException {
		String errMessage = null;
		boolean flags = false;
		List<ExcelGrade> list = new ArrayList<>();//excel�ɼ�����
		List<List<String>> content = new ArrayList<List<String>>();//���еĳɼ�����
		ExcelGrade grade = new ExcelGrade();
		java.io.File f = new java.io.File(request.getServletContext().getRealPath("/upload")+"/"+filename);
		InputStream is = new FileInputStream(f);
		
		try {
			//����������
			Workbook wb = new XSSFWorkbook(is);
			for (int i = 0, len = wb.getNumberOfSheets(); i < len; i++) {
				//����������
				Sheet sheet = wb.getSheetAt(i);
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {// ��j=1��ʾ��ȥ����һ��
					if (sheet == null) {
                        return null;
                    }
					//��ȡexcel�� ��
					Row row = sheet.getRow(j);
					if (row == null) {
                        return null;
                    }
					List<String> li = new ArrayList<>();
					for(int k = 0;k< row.getLastCellNum();k++){
						Cell cell = row.getCell(k);
                    	String str;
                    	if (cell == null) {
                            str = "";
                        }else {
                        	str = getValue(cell);
//                        	System.out.println("cell:  " + str);
//                        	System.out.println(str);
//                        	System.out.println("-----------");
                        	li.add(str);//���������б��浽������
						}
					}
//					System.out.println("��������");
					content.add(li);//��һ�������ȫ�����浽������
				}
				//�����������б���
				grade.setContent(content);
				list.add(grade);
			}
			
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}


		
