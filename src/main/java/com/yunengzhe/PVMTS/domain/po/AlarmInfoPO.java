package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;

public class AlarmInfoPO implements Serializable {
	private PowerStationBaseInfoVO powerStationBaseInfo;
    private Integer id;
    private String alarmNumber;
    private String alarmMid;
    private String alarmMessage;
    private Integer alarmGrade;
    private Integer status;
    private Integer alarmType;
    private String state;
    private Integer equipmentId;
    private Integer equipmentType;
    private Integer powerStationId;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private java.util.Date eventTime;
    private Integer userId;
    private Integer uid;
    private String description;
    private List<AlarmInfoPO> alarmInfoList;
    private List<AttachmentPO> attachmentList;
    private Double alarmValue;
    private String name;
    private String englishname;
    private String username;
    private String opreateMessage;
    private String handling;
    private String handler;
    private java.util.Date handTime;
    private String remaker;
    private String corporation;
    private String descriptionType;
    private String equipmentcontainerName;
    private String measurePointDiscription;
    private String pointEnglishName;
    
    
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	public String getPointEnglishName() {
		return pointEnglishName;
	}
	public void setPointEnglishName(String pointEnglishName) {
		this.pointEnglishName = pointEnglishName;
	}
	public String getMeasurePointDiscription() {
		return measurePointDiscription;
	}
	public void setMeasurePointDiscription(String measurePointDiscription) {
		this.measurePointDiscription = measurePointDiscription;
	}
	public String getDescriptionType() {
		return descriptionType;
	}
	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}
	public String getEquipmentcontainerName() {
		return equipmentcontainerName;
	}
	public void setEquipmentcontainerName(String equipmentcontainerName) {
		this.equipmentcontainerName = equipmentcontainerName;
	}
	public String getHandling() {
		return handling;
	}
	public void setHandling(String handling) {
		this.handling = handling;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public java.util.Date getHandTime() {
		return handTime;
	}
	public void setHandTime(java.util.Date handTime) {
		this.handTime = handTime;
	}
	public String getRemaker() {
		return remaker;
	}
	public void setRemaker(String remaker) {
		this.remaker = remaker;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public String getAlarmMid() {
		return alarmMid;
	}
	public void setAlarmMid(String alarmMid) {
		this.alarmMid = alarmMid;
	}
	public String getOpreateMessage() {
		return opreateMessage;
	}
	public void setOpreateMessage(String opreateMessage) {
		this.opreateMessage = opreateMessage;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.util.Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(java.util.Date eventTime) {
		this.eventTime = eventTime;
	}
	public Double getAlarmValue() {
		return alarmValue;
	}
	public void setAlarmValue(Double alarmValue) {
		this.alarmValue = alarmValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PowerStationBaseInfoVO getPowerStationBaseInfo() {
		return powerStationBaseInfo;
	}
	public void setPowerStationBaseInfo(PowerStationBaseInfoVO powerStationBaseInfo) {
		this.powerStationBaseInfo = powerStationBaseInfo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAlarmNumber() {
		return alarmNumber;
	}
	public void setAlarmNumber(String alarmNumber) {
		this.alarmNumber = alarmNumber;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public Integer getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
	}
	public Integer getPowerStationId() {
		return powerStationId;
	}
	public void setPowerStationId(Integer powerStationId) {
		this.powerStationId = powerStationId;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<AlarmInfoPO> getAlarmInfoList() {
		return alarmInfoList;
	}
	public void setAlarmInfoList(List<AlarmInfoPO> alarmInfoList) {
		this.alarmInfoList = alarmInfoList;
	}
	public List<AttachmentPO> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<AttachmentPO> attachmentList) {
		this.attachmentList = attachmentList;
	}
	@Override
	public String toString() {
		return "AlarmInfoPO [powerStationBaseInfo=" + powerStationBaseInfo
				+ ", id=" + id + ", alarmNumber=" + alarmNumber
				+ ", alarmMessage=" + alarmMessage + ", alarmGrade="
				+ alarmGrade + ", status=" + status + ", alarmType="
				+ alarmType + ", state=" + state + ", equipmentId="
				+ equipmentId + ", equipmentType=" + equipmentType
				+ ", powerStationId=" + powerStationId + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", userId="
				+ userId + ", description=" + description + ", alarmInfoList="
				+ alarmInfoList + ", attachmentList=" + attachmentList
				+ "]";
	}
	
}
