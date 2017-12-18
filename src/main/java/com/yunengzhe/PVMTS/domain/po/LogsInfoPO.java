package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class LogsInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String topic;
    private Integer categoryId;
    private String categoryName;
    private String category;
    private java.util.Date createTime;
    private String description;
    private java.util.Date updateTime;
    private Integer logStatus;
    private Integer equipmentId;
    private Integer equipmentType;
    private String equipmentName;
    private Integer userId;
    private Integer powerStationId;
    private Integer companyId;
    private String kaleidoscope;
    private String userName;
    private String powerStationName;
    private String companyName;
    private String nickname;
    private String handling;
    private String handler;
    private String remark;
    private String corporation;
    private String responsible;
    private String englishname;
    
    
	
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getKaleidoscope() {
		return kaleidoscope;
	}
	public void setKaleidoscope(String kaleidoscope) {
		this.kaleidoscope = kaleidoscope;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getHandling() {
		return handling;
	}
	public void setHandling(String handling) {
		this.handling = handling;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public String getResponsible() {
		return responsible;
	}
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPowerStationName() {
		return powerStationName;
	}
	public void setPowerStationName(String powerStationName) {
		this.powerStationName = powerStationName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
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
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "LogsInfoPO [id=" + id + ", topic=" + topic + ", createTime="
				+ createTime + ", description=" + description + ", updateTime="
				+ updateTime + ", logStatus=" + logStatus + ", equipmentId="
				+ equipmentId + ", equipmentType=" + equipmentType
				+ ", equipmentName=" + equipmentName + ", userId=" + userId
				+ ", powerStationId=" + powerStationId + ", companyId="
				+ companyId + ", userName=" + userName + ", powerStationName="
				+ powerStationName + ", companyName=" + companyName + "]";
	}


}
