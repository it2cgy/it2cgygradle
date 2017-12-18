package com.yunengzhe.PVMTS.domain.vo;

public class PowerStationListVO {

	
	private Integer id;
    private String powerStationName;//电站名称
    private String powerstationCode;//电站编号
    private String address;
	private java.math.BigDecimal lng;//经度
    private java.math.BigDecimal lat;//纬度
    private java.math.BigDecimal installCapacity;//电站容量
    private String investFirmName;//投资单位
    private String investFirmContactName;//投资单位负责人
    private java.util.Date createtime;
    private String provinceName;
    private String cityName;
    private String countyName;
    private String location;
    private java.math.BigDecimal effectivity;//启用状态
    private String imgUrl ;
    private String description;
    private String monitoring;
    private java.util.Date startProduceTime;//投产日期
    
	
	public java.util.Date getStartProduceTime() {
		return startProduceTime;
	}
	public void setStartProduceTime(java.util.Date startProduceTime) {
		this.startProduceTime = startProduceTime;
	}
	public String getMonitoring() {
		return monitoring;
	}
	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public java.math.BigDecimal getEffectivity() {
		return effectivity;
	}
	public void setEffectivity(java.math.BigDecimal effectivity) {
		this.effectivity = effectivity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public java.util.Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPowerStationName() {
		return powerStationName;
	}
	public void setPowerStationName(String powerStationName) {
		this.powerStationName = powerStationName;
	}
	public String getPowerstationCode() {
		return powerstationCode;
	}
	public void setPowerstationCode(String powerstationCode) {
		this.powerstationCode = powerstationCode;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public java.math.BigDecimal getInstallCapacity() {
		return installCapacity;
	}
	public void setInstallCapacity(java.math.BigDecimal installCapacity) {
		this.installCapacity = installCapacity;
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
    
    
    
}
