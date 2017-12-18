package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
/**
 * 逆变器信息对象
 * @author dell
 *
 */
public class InverterInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;//逆变器id
    private String name;//逆变器名称
    private String model;//逆变器型号
    private String serialNumber;//逆变器编号
    private double Temperature;//逆变组件温度
    private double ThreePhaseActivePower;//交流功率
    private Integer alarms;//报警数
    private double totalInputPower;//直流功率
    private double generationDaliy;//日发电量
    private double generationMonth;//月发电量
    private double generationYear;//日发电量
    private double generationGross;//日发电量
    private double equipmentStatus;//逆变器状态
    private double powerFactor;//功率因数
    private double unifiedPowerGenerationDaily;//归一化
	private double unifiedPowerGenerationMonth;//归一化
	private double unifiedPowerGenerationYear;//归一化
	private double unifiedGenerationGross;//归一化
	private double ratedPower;//装机容量
	
	
	public double getRatedPower() {
		return ratedPower;
	}
	public void setRatedPower(double ratedPower) {
		this.ratedPower = ratedPower;
	}
	public double getGenerationYear() {
		return generationYear;
	}
	public void setGenerationYear(double generationYear) {
		this.generationYear = generationYear;
	}
	public double getGenerationGross() {
		return generationGross;
	}
	public void setGenerationGross(double generationGross) {
		this.generationGross = generationGross;
	}
	public double getUnifiedPowerGenerationDaily() {
		return unifiedPowerGenerationDaily;
	}
	public void setUnifiedPowerGenerationDaily(double unifiedPowerGenerationDaily) {
		this.unifiedPowerGenerationDaily = unifiedPowerGenerationDaily;
	}
	public double getUnifiedPowerGenerationMonth() {
		return unifiedPowerGenerationMonth;
	}
	public void setUnifiedPowerGenerationMonth(double unifiedPowerGenerationMonth) {
		this.unifiedPowerGenerationMonth = unifiedPowerGenerationMonth;
	}
	public double getUnifiedPowerGenerationYear() {
		return unifiedPowerGenerationYear;
	}
	public void setUnifiedPowerGenerationYear(double unifiedPowerGenerationYear) {
		this.unifiedPowerGenerationYear = unifiedPowerGenerationYear;
	}
	public double getUnifiedGenerationGross() {
		return unifiedGenerationGross;
	}
	public void setUnifiedGenerationGross(double unifiedGenerationGross) {
		this.unifiedGenerationGross = unifiedGenerationGross;
	}
	public double getGenerationDaliy() {
		return generationDaliy;
	}
	public void setGenerationDaliy(double generationDaliy) {
		this.generationDaliy = generationDaliy;
	}
	public double getEquipmentStatus() {
		return equipmentStatus;
	}
	public void setEquipmentStatus(double equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	public double getPowerFactor() {
		return powerFactor;
	}
	public void setPowerFactor(double powerFactor) {
		this.powerFactor = powerFactor;
	}
	public double getTemperature() {
		return Temperature;
	}
	public void setTemperature(double temperature) {
		Temperature = temperature;
	}
	public double getThreePhaseActivePower() {
		return ThreePhaseActivePower;
	}
	public void setThreePhaseActivePower(double threePhaseActivePower) {
		ThreePhaseActivePower = threePhaseActivePower;
	}
	public Integer getAlarms() {
		return alarms;
	}
	public void setAlarms(Integer alarms) {
		this.alarms = alarms;
	}
	public double getGenerationMonth() {
		return generationMonth;
	}
	public void setGenerationMonth(double generationMonth) {
		this.generationMonth = generationMonth;
	}
	public double getTotalInputPower() {
		return totalInputPower;
	}
	public void setTotalInputPower(double totalInputPower) {
		this.totalInputPower = totalInputPower;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
