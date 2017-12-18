package com.yunengzhe.PVMTS.domain.dto;

public class SearchMeansurePointDataDTO {
	private String startTime;
	private String endTime;
	private String measurePointName;
	private String equipmentId;
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
	public String getMeasurePointName() {
		return measurePointName;
	}
	public void setMeasurePointName(String measurePointName) {
		this.measurePointName = measurePointName;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
}
