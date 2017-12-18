package com.yunengzhe.PVMTS.domain.vo;

import java.util.Map;

public class CustomPointDataHistoryVO {
	private String[] date;
	private Map<String,String[]>data;
	public String[] getDate() {
		return date;
	}
	public void setDate(String[] date) {
		this.date = date;
	}
	public Map<String, String[]> getData() {
		return data;
	}
	public void setData(Map<String, String[]> data) {
		this.data = data;
	}
	
}
