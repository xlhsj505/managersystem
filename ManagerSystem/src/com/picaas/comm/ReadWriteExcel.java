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
	 * 读取excel文件内容
	 * @param request
	 * @param response
	 * @param filename  excel文件在服务器中的路径
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
			//创建工作簿
			Workbook wb = new XSSFWorkbook(is);
			for (int i = 0, len = wb.getNumberOfSheets(); i < len; i++) {
				//创建工作表
				Sheet sheet = wb.getSheetAt(i);
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {// 令j=1表示除去标题一行
					if (sheet == null) {
                        return null;
                    }
					//读取excel的 行
					Row row = sheet.getRow(j);
					if (row == null) {
                        return null;
                    }
					System.out.println("最后的列数：  "+row.getLastCellNum());
					ExcelPatientInformation pf = new ExcelPatientInformation();
					// 读取每一个单元格
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
//				System.out.println("读出学生数共有： "+list.size());
			}
			
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//获取单元格值的方法 
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
        	//可能是普通数字，也可能是日期
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
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,
    		String []title,
    		String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth((short) 18);// 设置表格默认列宽度为20个字节
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

//    /**
//     * 按学校导出成绩单
//     * @param array 存储sheet名称的数组
//     * @param title 标题
//     * @param sLists 内容
//     * @param wb HSSFWorkbook对象
//     * @return
//     */
//    public static HSSFWorkbook getHSSFWorkbookForList(String[] array,
//    		String []title,
//    		List<List<Students>> sLists, HSSFWorkbook wb){
//
//        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
//        if(wb == null){
//            wb = new HSSFWorkbook();
//        }
//        int index = 0;
//        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
//        for (int k=0; k<array.length;k++) {
//        	HSSFSheet sheet = wb.createSheet(array[k]);
//        	// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
//        	sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));//合并单元格（开始行，结束行，开始列，结束列）
//        	sheet.setDefaultColumnWidth((short) 20);// 设置表格默认列宽度为20个字节
//            HSSFRow row = sheet.createRow(0);
//            row.createCell(0).setCellValue(array[k]+"成绩单");
//            // 第四步，创建单元格，并设置值表头 设置表头居中
//            HSSFCellStyle style = wb.createCellStyle();
//            style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
//    		// 生成一个字体
//    		HSSFFont font = wb.createFont();
//    		font.setFontHeightInPoints((short) 12);
//    		
//    		// 把字体应用到当前的样式
//    		style.setFont(font);
//            
//            //声明列对象
//            HSSFCell cell = null;
//            row = sheet.createRow(1);
//            //创建标题
//            for(int i=0;i<title.length;i++){	
//                cell = row.createCell(i);
//                cell.setCellValue(title[i]);
//                cell.setCellStyle(style);
//            }
//
//            //创建内容
//         // 遍历集合数据，产生数据行
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
	 * 读取患者病史excel文件内容
	 * @param request
	 * @param response
	 * @param filename  excel文件在服务器中的路径
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
			//创建工作簿
			Workbook wb = new XSSFWorkbook(is);
			for (int i = 0, len = wb.getNumberOfSheets(); i < len; i++) {
				//创建工作表
				Sheet sheet = wb.getSheetAt(i);
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {// 令j=1表示除去标题一行
					if (sheet == null) {
                        return null;
                    }
					//读取excel的 行
					Row row = sheet.getRow(j);
					if (row == null) {
                        return null;
                    }
					System.out.println("最后的列数：  "+row.getLastCellNum());
					ExcelIllness in = new ExcelIllness();
					// 读取每一个单元格
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
//				System.out.println("读出学生数共有： "+list.size());
			}
			
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 读取影像学excel文件内容
	 * @param request
	 * @param response
	 * @param filename  excel文件在服务器中的路径
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
			//创建工作簿
			Workbook wb = new XSSFWorkbook(is);
			for (int i = 0, len = wb.getNumberOfSheets(); i < len; i++) {
				//创建工作表
				Sheet sheet = wb.getSheetAt(i);
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {// 令j=1表示除去标题一行
					if (sheet == null) {
                        return null;
                    }
					//读取excel的 行
					Row row = sheet.getRow(j);
					if (row == null) {
                        return null;
                    }
					System.out.println("最后的列数：  "+row.getLastCellNum());
					ExcelIconography ic = new ExcelIconography();
					// 读取每一个单元格
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
//				System.out.println("读出学生数共有： "+list.size());
			}
			
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 读取患者成绩excel文件内容
	 * @param request
	 * @param response
	 * @param filename  excel文件在服务器中的路径
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<ExcelGrade> getGradeData(HttpServletRequest request, 
			HttpServletResponse response, String filename) 
			throws FileNotFoundException {
		String errMessage = null;
		boolean flags = false;
		List<ExcelGrade> list = new ArrayList<>();//excel成绩集合
		List<List<String>> content = new ArrayList<List<String>>();//所有的成绩的行
		ExcelGrade grade = new ExcelGrade();
		java.io.File f = new java.io.File(request.getServletContext().getRealPath("/upload")+"/"+filename);
		InputStream is = new FileInputStream(f);
		
		try {
			//创建工作簿
			Workbook wb = new XSSFWorkbook(is);
			for (int i = 0, len = wb.getNumberOfSheets(); i < len; i++) {
				//创建工作表
				Sheet sheet = wb.getSheetAt(i);
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {// 令j=1表示除去标题一行
					if (sheet == null) {
                        return null;
                    }
					//读取excel的 行
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
                        	li.add(str);//将读出的行保存到集合中
						}
					}
//					System.out.println("跳出来了");
					content.add(li);//将一个表的行全部保存到集合中
				}
				//将读出的所有表返回
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


		
