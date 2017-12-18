package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class AmmeterPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private java.math.BigDecimal equipmenttypeid;
    private String description;
    private java.math.BigDecimal type;
    private String model;
    private String serialNumber;
    private java.math.BigDecimal farmid;
    private java.math.BigDecimal collectorId;
    private java.math.BigDecimal parentId;
    private java.math.BigDecimal meterType;
	public java.math.BigDecimal getMeterType() {
		return meterType;
	}
	public void setMeterType(java.math.BigDecimal meterType) {
		this.meterType = meterType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.math.BigDecimal getEquipmenttypeid() {
		return equipmenttypeid;
	}
	public void setEquipmenttypeid(java.math.BigDecimal equipmenttypeid) {
		this.equipmenttypeid = equipmenttypeid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public java.math.BigDecimal getType() {
		return type;
	}
	public void setType(java.math.BigDecimal type) {
		this.type = type;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public java.math.BigDecimal getFarmid() {
		return farmid;
	}
	public void setFarmid(java.math.BigDecimal farmid) {
		this.farmid = farmid;
	}
	public java.math.BigDecimal getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(java.math.BigDecimal collectorId) {
		this.collectorId = collectorId;
	}
	public java.math.BigDecimal getParentId() {
		return parentId;
	}
	public void setParentId(java.math.BigDecimal parentId) {
		this.parentId = parentId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
