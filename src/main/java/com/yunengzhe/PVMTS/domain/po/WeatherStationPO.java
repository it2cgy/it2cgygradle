package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class WeatherStationPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private java.math.BigDecimal equipmenttypeid;
    private String description;
    private String model;
    private String serialNumber;
    private String manufacturer;
    private java.math.BigDecimal farmid;
    private java.math.BigDecimal collectorId;
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
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
