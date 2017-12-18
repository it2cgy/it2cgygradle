package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

/**
 * @ClassName:PowerStationPO
 * @Description:TODO(电站)
 * @author chenguiyang
 * @date 2017年6月28日上午9:53:27
 */
public class PowerStationPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;//电站名称
    private java.math.BigDecimal equipmentttypeid;
    private String description;//描述
    private java.math.BigDecimal companyId;//公司id
    private String code;//电站编号
    private java.math.BigDecimal type;
    private java.math.BigDecimal provinceId;
    private java.math.BigDecimal cityId;
    private java.math.BigDecimal countyId;
    private String location;
    private java.math.BigDecimal lng;
    private java.math.BigDecimal lat;
    private java.math.BigDecimal roofType;
    private java.math.BigDecimal buildArea;
    private java.math.BigDecimal buildGroundArea;
    private java.math.BigDecimal proAvailableArea;
    private java.math.BigDecimal installCapacity;
    private String image;
    private String investFirmName;
    private String investFirmContactName;
    private String investFirmContactPosition;
    private String investFirmContactMobile;
    private String ownerFirmName;
    private String ownerFirmContactName;
    private String ownerFirmContactPosition;
    private String ownerFirmContactMobile;
    private String operatorName;
    private String operatorMobile;
    private String maintainerName;
    private String maintainerMobile;
    private java.util.Date lastOpreateTime;
    private String picturePath;
    private java.util.Date startPruduceTime;
    private String provinceName;
    private String cityName;
    private String countyName;
    private java.math.BigDecimal effectivity;
    private Integer havetuopu;
    private Integer havebuzhi;
    private String monitoring;
    private String englishname;
    
   
	
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	public String getMonitoring() {
		return monitoring;
	}
	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}
	public Integer getHavetuopu() {
		return havetuopu;
	}
	public void setHavetuopu(Integer havetuopu) {
		this.havetuopu = havetuopu;
	}
	public Integer getHavebuzhi() {
		return havebuzhi;
	}
	public void setHavebuzhi(Integer havebuzhi) {
		this.havebuzhi = havebuzhi;
	}
	public java.math.BigDecimal getEffectivity() {
		return effectivity;
	}
	public void setEffectivity(java.math.BigDecimal effectivity) {
		this.effectivity = effectivity;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
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
	public java.math.BigDecimal getEquipmentttypeid() {
		return equipmentttypeid;
	}
	public void setEquipmentttypeid(java.math.BigDecimal equipmentttypeid) {
		this.equipmentttypeid = equipmentttypeid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public java.math.BigDecimal getCompanyId() {
		return companyId;
	}
	public void setCompanyId(java.math.BigDecimal companyId) {
		this.companyId = companyId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public java.math.BigDecimal getType() {
		return type;
	}
	public void setType(java.math.BigDecimal type) {
		this.type = type;
	}
	public java.math.BigDecimal getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(java.math.BigDecimal provinceId) {
		this.provinceId = provinceId;
	}
	public java.math.BigDecimal getCityId() {
		return cityId;
	}
	public void setCityId(java.math.BigDecimal cityId) {
		this.cityId = cityId;
	}
	public java.math.BigDecimal getCountyId() {
		return countyId;
	}
	public void setCountyId(java.math.BigDecimal countyId) {
		this.countyId = countyId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public java.math.BigDecimal getLng() {
		return lng;
	}
	public void setLng(java.math.BigDecimal lng) {
		this.lng = lng;
	}
	public java.math.BigDecimal getLat() {
		return lat;
	}
	public void setLat(java.math.BigDecimal lat) {
		this.lat = lat;
	}
	public java.math.BigDecimal getRoofType() {
		return roofType;
	}
	public void setRoofType(java.math.BigDecimal roofType) {
		this.roofType = roofType;
	}
	public java.math.BigDecimal getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(java.math.BigDecimal buildArea) {
		this.buildArea = buildArea;
	}
	public java.math.BigDecimal getBuildGroundArea() {
		return buildGroundArea;
	}
	public void setBuildGroundArea(java.math.BigDecimal buildGroundArea) {
		this.buildGroundArea = buildGroundArea;
	}
	public java.math.BigDecimal getProAvailableArea() {
		return proAvailableArea;
	}
	public void setProAvailableArea(java.math.BigDecimal proAvailableArea) {
		this.proAvailableArea = proAvailableArea;
	}
	public java.math.BigDecimal getInstallCapacity() {
		return installCapacity;
	}
	public void setInstallCapacity(java.math.BigDecimal installCapacity) {
		this.installCapacity = installCapacity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getInvestFirmName() {
		return investFirmName;
	}
	public void setInvestFirmName(String investFirmName) {
		this.investFirmName = investFirmName;
	}
	public String getInvestFirmContactName() {
		return investFirmContactName;
	}
	public void setInvestFirmContactName(String investFirmContactName) {
		this.investFirmContactName = investFirmContactName;
	}
	public String getInvestFirmContactPosition() {
		return investFirmContactPosition;
	}
	public void setInvestFirmContactPosition(String investFirmContactPosition) {
		this.investFirmContactPosition = investFirmContactPosition;
	}
	public String getInvestFirmContactMobile() {
		return investFirmContactMobile;
	}
	public void setInvestFirmContactMobile(String investFirmContactMobile) {
		this.investFirmContactMobile = investFirmContactMobile;
	}
	public String getOwnerFirmName() {
		return ownerFirmName;
	}
	public void setOwnerFirmName(String ownerFirmName) {
		this.ownerFirmName = ownerFirmName;
	}
	public String getOwnerFirmContactName() {
		return ownerFirmContactName;
	}
	public void setOwnerFirmContactName(String ownerFirmContactName) {
		this.ownerFirmContactName = ownerFirmContactName;
	}
	public String getOwnerFirmContactPosition() {
		return ownerFirmContactPosition;
	}
	public void setOwnerFirmContactPosition(String ownerFirmContactPosition) {
		this.ownerFirmContactPosition = ownerFirmContactPosition;
	}
	public String getOwnerFirmContactMobile() {
		return ownerFirmContactMobile;
	}
	public void setOwnerFirmContactMobile(String ownerFirmContactMobile) {
		this.ownerFirmContactMobile = ownerFirmContactMobile;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperatorMobile() {
		return operatorMobile;
	}
	public void setOperatorMobile(String operatorMobile) {
		this.operatorMobile = operatorMobile;
	}
	public String getMaintainerName() {
		return maintainerName;
	}
	public void setMaintainerName(String maintainerName) {
		this.maintainerName = maintainerName;
	}
	public String getMaintainerMobile() {
		return maintainerMobile;
	}
	public void setMaintainerMobile(String maintainerMobile) {
		this.maintainerMobile = maintainerMobile;
	}
	public java.util.Date getLastOpreateTime() {
		return lastOpreateTime;
	}
	public void setLastOpreateTime(java.util.Date lastOpreateTime) {
		this.lastOpreateTime = lastOpreateTime;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public java.util.Date getStartPruduceTime() {
		return startPruduceTime;
	}
	public void setStartPruduceTime(java.util.Date startPruduceTime) {
		this.startPruduceTime = startPruduceTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
