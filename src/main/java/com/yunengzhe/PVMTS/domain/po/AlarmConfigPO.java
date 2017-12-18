package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class AlarmConfigPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer pointId;
    private Integer equipmentcontainerId;
    private Integer equipmentcontainerTableid;
    private Integer powerStationId;
    private Integer userId;
    private Double valueOne;
    private Double valueTwo;
    private Double valueThree;
    private Integer type;
    private String message;
    private Integer forbidden;
    private java.util.Date createDate;
    private java.util.Date updateDate;
    private String descriptionType;
    private String equipmentcontainerName;
    private String measurePointDiscription;
    private String pointEnglishName;
    private String equipmentcontainerNameEn;
    
	public String getPointEnglishName() {
		return pointEnglishName;
	}
	public void setPointEnglishName(String pointEnglishName) {
		this.pointEnglishName = pointEnglishName;
	}
	public String getEquipmentcontainerNameEn() {
		return equipmentcontainerNameEn;
	}
	public void setEquipmentcontainerNameEn(String equipmentcontainerNameEn) {
		this.equipmentcontainerNameEn = equipmentcontainerNameEn;
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
	public String getMeasurePointDiscription() {
		return measurePointDiscription;
	}
	public void setMeasurePointDiscription(String measurePointDiscription) {
		this.measurePointDiscription = measurePointDiscription;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPointId() {
		return pointId;
	}
	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}
	public Integer getEquipmentcontainerId() {
		return equipmentcontainerId;
	}
	public void setEquipmentcontainerId(Integer equipmentcontainerId) {
		this.equipmentcontainerId = equipmentcontainerId;
	}
	public Integer getEquipmentcontainerTableid() {
		return equipmentcontainerTableid;
	}
	public void setEquipmentcontainerTableid(Integer equipmentcontainerTableid) {
		this.equipmentcontainerTableid = equipmentcontainerTableid;
	}
	public Integer getPowerStationId() {
		return powerStationId;
	}
	public void setPowerStationId(Integer powerStationId) {
		this.powerStationId = powerStationId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getValueOne() {
		return valueOne;
	}
	public void setValueOne(Double valueOne) {
		this.valueOne = valueOne;
	}
	public Double getValueTwo() {
		return valueTwo;
	}
	public void setValueTwo(Double valueTwo) {
		this.valueTwo = valueTwo;
	}
	public Double getValueThree() {
		return valueThree;
	}
	public void setValueThree(Double valueThree) {
		this.valueThree = valueThree;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getForbidden() {
		return forbidden;
	}
	public void setForbidden(Integer forbidden) {
		this.forbidden = forbidden;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.util.Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

    

}
