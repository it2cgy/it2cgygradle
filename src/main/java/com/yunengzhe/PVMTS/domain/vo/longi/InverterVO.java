package com.yunengzhe.PVMTS.domain.vo.longi;

import java.io.Serializable;
import java.util.List;
/**
 * 逆变器信息对象
 * @author dell
 *
 */
public class InverterVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;//逆变器id
    private String name;//逆变器名称
    private String serialNumber;//逆变器编号
    private double temperature;//逆变组件温度
    private double threePhaseActivePower;//交流功率
    private double totalInputPower;//直流功率
    private double generationDaliy;//日发电量
    private double equipmentStatus;//逆变器状态
    private double powerFactor;//功率因数
    private Integer measurementBoxId;
    private String measurementBoxName;
    private String measurementBoxSerialNumber;
    private List<AmmeterVO>ammeterList;
    private Integer drictmeterCounts;//直流电表个数
    private Integer threePhasemeterCounts;//交流电表个数
    
	public Integer getDrictmeterCounts() {
		return drictmeterCounts;
	}
	public void setDrictmeterCounts(Integer drictmeterCounts) {
		this.drictmeterCounts = drictmeterCounts;
	}
	public Integer getThreePhasemeterCounts() {
		return threePhasemeterCounts;
	}
	public void setThreePhasemeterCounts(Integer threePhasemeterCounts) {
		this.threePhasemeterCounts = threePhasemeterCounts;
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
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getThreePhaseActivePower() {
		return threePhaseActivePower;
	}
	public void setThreePhaseActivePower(double threePhaseActivePower) {
		this.threePhaseActivePower = threePhaseActivePower;
	}
	public double getTotalInputPower() {
		return totalInputPower;
	}
	public void setTotalInputPower(double totalInputPower) {
		this.totalInputPower = totalInputPower;
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
	public Integer getMeasurementBoxId() {
		return measurementBoxId;
	}
	public void setMeasurementBoxId(Integer measurementBoxId) {
		this.measurementBoxId = measurementBoxId;
	}
	public String getMeasurementBoxName() {
		return measurementBoxName;
	}
	public void setMeasurementBoxName(String measurementBoxName) {
		this.measurementBoxName = measurementBoxName;
	}
	public String getMeasurementBoxSerialNumber() {
		return measurementBoxSerialNumber;
	}
	public void setMeasurementBoxSerialNumber(String measurementBoxSerialNumber) {
		this.measurementBoxSerialNumber = measurementBoxSerialNumber;
	}
	public List<AmmeterVO> getAmmeterList() {
		return ammeterList;
	}
	public void setAmmeterList(List<AmmeterVO> ammeterList) {
		this.ammeterList = ammeterList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
