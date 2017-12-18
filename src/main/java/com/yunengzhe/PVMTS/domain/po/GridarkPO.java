package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class GridarkPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer equipmenttypeid;
    private String description;
    private Integer type;
    private String model;
    private String englishname;
    private String serialNumber;
    private Integer farmid;
    private Integer collectorId;
    private Integer parentId;
    private Integer meterType;
    
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
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
	public Integer getEquipmenttypeid() {
		return equipmenttypeid;
	}
	public void setEquipmenttypeid(Integer equipmenttypeid) {
		this.equipmenttypeid = equipmenttypeid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
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
	public Integer getFarmid() {
		return farmid;
	}
	public void setFarmid(Integer farmid) {
		this.farmid = farmid;
	}
	public Integer getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(Integer collectorId) {
		this.collectorId = collectorId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getMeterType() {
		return meterType;
	}
	public void setMeterType(Integer meterType) {
		this.meterType = meterType;
	}


}
