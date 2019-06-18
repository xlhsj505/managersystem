package com.picaas.manager.entity;

/*
 * 	ÏûÏ¢
 */
import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class Msg implements Serializable {
	private int flag;
	private String contents;
	private List<T> dataList;
	
	
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
