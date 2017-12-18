package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

public class HistoryDataResult { 
	private String startTime;
	private String endTime;
	private String span; //时间跨度的余数 应该为0
	private String equipcontainerID;
	private String equipcontainerTableID;
	private String dateSpan; //时间跨度 300的整数倍
	private String measuerName;
	private List<HistoryData> result;
	
	 
	public String getEquipcontainerID() {
		return equipcontainerID;
	}
	public void setEquipcontainerID(String equipcontainerID) {
		this.equipcontainerID = equipcontainerID;
	}
	public String getEquipcontainerTableID() {
		return equipcontainerTableID;
	}
	public void setEquipcontainerTableID(String equipcontainerTableID) {
		this.equipcontainerTableID = equipcontainerTableID;
	}
	public String getMeasuerName() {
		return measuerName;
	}
	public void setMeasuerName(String measuerName) {
		this.measuerName = measuerName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSpan() {
		return span;
	}
	public void setSpan(String span) {
		this.span = span;
	} 
	public String getDateSpan() {
		return dateSpan;
	}
	public void setDateSpan(String dateSpan) {
		this.dateSpan = dateSpan;
	}
	public List<HistoryData> getResult() {
		return result;
	}
	public void setResult(List<HistoryData> result) {
		this.result = result;
	}
	
	
}
