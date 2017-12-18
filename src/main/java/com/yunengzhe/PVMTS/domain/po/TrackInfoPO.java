package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class TrackInfoPO implements Serializable {
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
    public double qinvalue;
    public double fangvalue;
    public double lxqinvalue;
    public double lxfangvalue;
    public double status;
    
    
	
	public double getLxqinvalue() {
		return lxqinvalue;
	}
	public void setLxqinvalue(double lxqinvalue) {
		this.lxqinvalue = lxqinvalue;
	}
	public double getLxfangvalue() {
		return lxfangvalue;
	}
	public void setLxfangvalue(double lxfangvalue) {
		this.lxfangvalue = lxfangvalue;
	}
	public double getStatus() {
		return status;
	}
	public void setStatus(double status) {
		this.status = status;
	}
	public double getQinvalue() {
		return qinvalue;
	}
	public void setQinvalue(double qinvalue) {
		this.qinvalue = qinvalue;
	}
	public double getFangvalue() {
		return fangvalue;
	}
	public void setFangvalue(double fangvalue) {
		this.fangvalue = fangvalue;
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
	@Override
	public String toString() {
		return "TrackInfoPO [id=" + id + ", name=" + name
				+ ", equipmenttypeid=" + equipmenttypeid + ", description="
				+ description + ", model=" + model + ", serialNumber="
				+ serialNumber + ", manufacturer=" + manufacturer + ", farmid="
				+ farmid + ", collectorId=" + collectorId + "]";
	}


}
