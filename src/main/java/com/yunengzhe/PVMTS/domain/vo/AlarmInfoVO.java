package com.yunengzhe.PVMTS.domain.vo;


public class AlarmInfoVO {

	
	private Integer id;
    private String alarmMessage;
    private Integer alarmGrade;
    private Integer status;
    private Integer alarmType;
    private Integer powerStationId;
    private java.util.Date eventTime;
    private Integer userId;
    private double alarmValue;
	private String address;
	private String name;
	private String opreateMessage;
	private int booread;
    
	public String getOpreateMessage() {
		return opreateMessage;
	}
	public void setOpreateMessage(String opreateMessage) {
		this.opreateMessage = opreateMessage;
	}
	public int getBooread() {
		return booread;
	}
	public void setBooread(int booread) {
		this.booread = booread;
	}		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAlarmMessage() {
		return alarmMessage;
	}
	public void setAlarmMessage(String alarmMessage) {
		this.alarmMessage = alarmMessage;
	}
	public Integer getAlarmGrade() {
		return alarmGrade;
	}
	public void setAlarmGrade(Integer alarmGrade) {
		this.alarmGrade = alarmGrade;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}
	public Integer getPowerStationId() {
		return powerStationId;
	}
	public void setPowerStationId(Integer powerStationId) {
		this.powerStationId = powerStationId;
	}
	public java.util.Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(java.util.Date eventTime) {
		this.eventTime = eventTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public double getAlarmValue() {
		return alarmValue;
	}
	public void setAlarmValue(double alarmValue) {
		this.alarmValue = alarmValue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "AlarmInfoVO [id=" + id + ", alarmMessage=" + alarmMessage
				+ ", alarmGrade=" + alarmGrade + ", status=" + status
				+ ", alarmType=" + alarmType + ", powerStationId="
				+ powerStationId + ", eventTime=" + eventTime + ", userId="
				+ userId + ", alarmValue=" + alarmValue + ", address="
				+ address + ", name=" + name + "]";
	}
	
	
}
