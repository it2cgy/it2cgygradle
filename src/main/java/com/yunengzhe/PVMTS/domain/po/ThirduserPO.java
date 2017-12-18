package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class ThirduserPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private Integer equipmentType;
    private Integer equipmentId;
    private String measurementtypeId;
    private Integer powerStationId;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String aliasname;
    
	public String getAliasname() {
		return aliasname;
	}
	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getMeasurementtypeId() {
		return measurementtypeId;
	}
	public void setMeasurementtypeId(String measurementtypeId) {
		this.measurementtypeId = measurementtypeId;
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


}
