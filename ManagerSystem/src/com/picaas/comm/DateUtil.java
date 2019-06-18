package com.picaas.comm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getCurrentYear(){
		Date currentTime = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString.substring(0, 4);
	}
	
	public static String getCurrentTime(){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		return dateString;
	}
	public static Date StringToDate(String dateStr,String formatStr){		
		DateFormat dd=new SimpleDateFormat(formatStr);		
		Date date=null;		
		try {			
			date = dd.parse(dateStr);		
			} catch (ParseException e) {
				e.printStackTrace();		
			}		return date;	
	}
	
	public static String formateString(String s){
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataString = format.format(StringToDate(s, "yyyy-MM-dd HH:mm:ss"));
		return dataString;
	}
	
	
	
}