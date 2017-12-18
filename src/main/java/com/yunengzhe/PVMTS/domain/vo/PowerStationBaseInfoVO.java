package com.yunengzhe.PVMTS.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 电站基本信息
 * @author dell
 *
 */
public class PowerStationBaseInfoVO {
	private Integer id;
	private String address;
	private String name;
	private String code;
	private String manager;
	private java.math.BigDecimal lng;
	private java.math.BigDecimal lat;
	private java.math.BigDecimal installCapacity;
	private Date startPruduceTime;
	private String investFirmName;
	private String managerMobile;
	private String imgUrl;
	private Integer havebuzhi;
	private Integer havetuopu;
	private String  description;
	private String  monitoring;
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMonitoring() {
		return monitoring;
	}
	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getHavebuzhi() {
		return havebuzhi;
	}
	public void setHavebuzhi(Integer havebuzhi) {
		this.havebuzhi = havebuzhi;
	}
	public Integer getHavetuopu() {
		return havetuopu;
	}
	public void setHavetuopu(Integer havetuopu) {
		this.havetuopu = havetuopu;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getManagerMobile() {
		return managerMobile;
	}
	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}
	public String getInvestFirmName() {
		return investFirmName;
	}
	public void setInvestFirmName(String investFirmName) {
		this.investFirmName = investFirmName;
	}
	public Date getStartPruduceTime() {
		return startPruduceTime;
	}
	public void setStartPruduceTime(Date startPruduceTime) {
		this.startPruduceTime = startPruduceTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
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
	public PowerStationBaseInfoVO(Integer id, String address, String name,
			String manager, BigDecimal lng, BigDecimal lat,
			BigDecimal installCapacity) {
		super();
		this.id = id;
		this.address = address;
		this.name = name;
		this.manager = manager;
		this.lng = lng;
		this.lat = lat;
		this.installCapacity = installCapacity;
	}
	public PowerStationBaseInfoVO() {
		super();
	}
	
}
