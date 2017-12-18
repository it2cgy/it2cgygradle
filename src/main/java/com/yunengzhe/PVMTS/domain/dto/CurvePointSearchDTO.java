package com.yunengzhe.PVMTS.domain.dto;

public class CurvePointSearchDTO {
	private String analoginputId;
	private String startTime;
	private String endTime;
	private String dateSpan;
	private Integer span;
	public String getAnaloginputId() {
		return analoginputId;
	}
	public void setAnaloginputId(String analoginputId) {
		this.analoginputId = analoginputId;
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
	public String getDateSpan() {
		return dateSpan;
	}
	public void setDateSpan(String dateSpan) {
		this.dateSpan = dateSpan;
	}
	public Integer getSpan() {
		return span;
	}
	public void setSpan(Integer span) {
		this.span = span;
	}
	
}
