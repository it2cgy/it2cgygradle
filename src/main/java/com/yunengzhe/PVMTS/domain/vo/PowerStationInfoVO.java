package com.yunengzhe.PVMTS.domain.vo;

import java.util.Date;
import java.util.List;

/**
 * 电站信息
 * @author dell
 *
 */
public class PowerStationInfoVO {
	private Integer id;//电站ID
	private String address;//电站地址
	private String name;//电站名称
	private String code;//电站编号
	private String manager;//负责人
	private java.math.BigDecimal lng;//纬度
	private java.math.BigDecimal lat;//经度
	private java.math.BigDecimal installCapacity;//装机容量
	private Integer alarms;//报警数量
	private Integer communicationAnomaly;//通讯异常数量
	private Integer stop;//停机数
	private double pr;//系统效率
	private Date lastPhysicalExaminationTime;//最后一次体检时间
	//投资单位
	private String investFirmName;
	//温度
	private double temperature;
	//辐照度
	private double irradiance;
	 /**
     * 功率
     */
    private double generatedActivePower;
    private int generatedActivePowerId;
    /**
     * 日发电量analoginputId
     */
    private Integer generationDailyId;
    private double generationGross;
    private double generationGrossId;
    private double generationDaily;
    private double radiantExposure;
	
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getGenerationGrossId() {
		return generationGrossId;
	}
	public void setGenerationGrossId(double generationGrossId) {
		this.generationGrossId = generationGrossId;
	}
	public int getGeneratedActivePowerId() {
		return generatedActivePowerId;
	}
	public void setGeneratedActivePowerId(int generatedActivePowerId) {
		this.generatedActivePowerId = generatedActivePowerId;
	}
	private String imgUrl;
    
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public double getRadiantExposure() {
		return radiantExposure;
	}
	public void setRadiantExposure(double radiantExposure) {
		this.radiantExposure = radiantExposure;
	}
	public String getInvestFirmName() {
		return investFirmName;
	}
	public void setInvestFirmName(String investFirmName) {
		this.investFirmName = investFirmName;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getIrradiance() {
		return irradiance;
	}
	public void setIrradiance(double irradiance) {
		this.irradiance = irradiance;
	}
	public double getGeneratedActivePower() {
		return generatedActivePower;
	}
	public void setGeneratedActivePower(double generatedActivePower) {
		this.generatedActivePower = generatedActivePower;
	}
	
	public Integer getGenerationDailyId() {
		return generationDailyId;
	}
	public void setGenerationDailyId(Integer generationDailyId) {
		this.generationDailyId = generationDailyId;
	}
	public double getGenerationGross() {
		return generationGross;
	}
	public void setGenerationGross(double generationGross) {
		this.generationGross = generationGross;
	}
	public double getGenerationDaily() {
		return generationDaily;
	}
	public void setGenerationDaily(double generationDaily) {
		this.generationDaily = generationDaily;
	}
	public Date getLastPhysicalExaminationTime() {
		return lastPhysicalExaminationTime;
	}
	public void setLastPhysicalExaminationTime(Date lastPhysicalExaminationTime) {
		this.lastPhysicalExaminationTime = lastPhysicalExaminationTime;
	}
	public Integer getAlarms() {
		return alarms;
	}
	public void setAlarms(Integer alarms) {
		this.alarms = alarms;
	}
	public Integer getCommunicationAnomaly() {
		return communicationAnomaly;
	}
	public void setCommunicationAnomaly(Integer communicationAnomaly) {
		this.communicationAnomaly = communicationAnomaly;
	}
	public Integer getStop() {
		return stop;
	}
	public void setStop(Integer stop) {
		this.stop = stop;
	}
	public double getPr() {
		return pr;
	}
	public void setPr(double pr) {
		this.pr = pr;
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
	
}
