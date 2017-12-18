package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;

/**
 * @author dell
 *
 */
public class LogsInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String topic;
    private Integer categoryId;
    private String categoryName;
    private String category;
    private String powerStationName;
    private Integer powerStationId;
    private java.util.Date createTime;
    private String userName;
    private String companyName;
    private Integer companyId;
    private String kaleidoscope;
    private String description;
    private Integer equipmentType;
    private Integer equipmentId;
    private String equipmentName;
    private String nickname;
    private String handler;
    private String handling;
    private String remark;
    private String corporation;
    private String responsible;
    private Integer logStatus;
    
    
	
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
	public Integer getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
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
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
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
	public String getPowerStationName() {
		return powerStationName;
	}
	public void setPowerStationName(String powerStationName) {
		this.powerStationName = powerStationName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public Integer getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	@Override
	public String toString() {
		return "LogsInfoVO [id=" + id + ", topic=" + topic
				+ ", powerStationName=" + powerStationName
				+ ", powerStationId=" + powerStationId + ", createTime="
				+ createTime + ", userName=" + userName + ", companyName="
				+ companyName + ", companyId=" + companyId + ", description="
				+ description + ", equipmentType=" + equipmentType
				+ ", equipmentId=" + equipmentId + "]";
	}
	
}
