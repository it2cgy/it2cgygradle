package com.yunengzhe.PVMTS.domain.vo;

public class AlarmVO {
	private String alarmGrade;
	private String alarmMessage;
	private String beforeTime;
	private String lateTime;
	private String powerStationId;
	private Integer page;
	private Integer pagesize;
	private Integer status;
	public String getAlarmGrade() {
		return alarmGrade;
	}
	public void setAlarmGrade(String alarmGrade) {
		this.alarmGrade = alarmGrade;
	}
	public String getAlarmMessage() {
		return alarmMessage;
	}
	public void setAlarmMessage(String alarmMessage) {
		this.alarmMessage = alarmMessage;
	}
	public String getBeforeTime() {
		return beforeTime;
	}
	public void setBeforeTime(String beforeTime) {
		this.beforeTime = beforeTime;
	}
	public String getLateTime() {
		return lateTime;
	}
	public void setLateTime(String lateTime) {
		this.lateTime = lateTime;
	}
	public String getPowerStationId() {
		return powerStationId;
	}
	public void setPowerStationId(String powerStationId) {
		this.powerStationId = powerStationId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
