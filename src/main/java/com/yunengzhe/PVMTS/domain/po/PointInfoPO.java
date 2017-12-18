package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class PointInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer measurementtypeId;
    private Integer equipmentcontainerId;
    private Integer equipmentcontainerTableid;
    private Integer powerStationId;
    private String descriptionType;
    private String measurementtypeName;
    private String measurementtypeDescription;
    private String equipmentcontainerName;
    private String equipmentcontainerNameEn;
    private String pointEnglishName;
    
    
	public String getEquipmentcontainerNameEn() {
		return equipmentcontainerNameEn;
	}
	public void setEquipmentcontainerNameEn(String equipmentcontainerNameEn) {
		this.equipmentcontainerNameEn = equipmentcontainerNameEn;
	}
	public String getPointEnglishName() {
		return pointEnglishName;
	}
	public void setPointEnglishName(String pointEnglishName) {
		this.pointEnglishName = pointEnglishName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMeasurementtypeId() {
		return measurementtypeId;
	}
	public void setMeasurementtypeId(Integer measurementtypeId) {
		this.measurementtypeId = measurementtypeId;
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
	public String getDescriptionType() {
		return descriptionType;
	}
	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}
	public String getMeasurementtypeName() {
		return measurementtypeName;
	}
	public void setMeasurementtypeName(String measurementtypeName) {
		this.measurementtypeName = measurementtypeName;
	}
	public String getMeasurementtypeDescription() {
		return measurementtypeDescription;
	}
	public void setMeasurementtypeDescription(String measurementtypeDescription) {
		this.measurementtypeDescription = measurementtypeDescription;
	}
	public String getEquipmentcontainerName() {
		return equipmentcontainerName;
	}
	public void setEquipmentcontainerName(String equipmentcontainerName) {
		this.equipmentcontainerName = equipmentcontainerName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
