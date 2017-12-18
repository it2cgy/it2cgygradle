package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 逆变器详情对象
 * @author dell
 *
 */
public class InverterDetailsVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 逆变器名称
	 */
	private String name; 
	/**
	 * 逆变器编号
	 */
	private String serialNumber; 
	/**
	 * 所属电站id
	 */
	private Integer powerStationId; 
	/**
	 * 直流参数 mppt1\2\3
	 */
	private List<MeasurePointValue> mppt;
	/**
	 * 交流参数A\B\C相
	 */
	private List<MeasurePointValue> phaseVoltageParam;
	/**
	 * 日发电量
	 */
	private double generationDaily;
	/**
	 * 累计发电量
	 */
	private double generationGross;
	/**
	 * 月发电量
	 */
	private double generationMonth;
	/**
	 * 年发电量
	 */
	private double generationYear;
	/**
	 * 一级报警数量
	 */
	private Integer level1;
	/**
	 * 二级报警数量
	 */
	private Integer level2;
	/**
	 * 三级报警数量
	 */
	private Integer level3;
	/**
	 * 功率、温度-时间曲线数据
	 */
	private List<PowerTemperatureTime> powerTemperatureTime;
	/**
	 * 直流总功率
	 */
	private double TotalInputPower;
	/**
	 * 交流总功率
	 */
	private double ThreePhaseActivePower;
	/**
	 * 逆变器效率
	 */
	private double Efficiency;
	/**
	 * 逆变器温度
	 */
	private double Temperature;
	//实时功率ID
	private Integer threePhaseActivePowerId;
	//日发电量ID
	private Integer generationDailyId;
	private Integer generationMonthId;
	private Integer generationYearId;
	private Integer generationGrossId;
	// 累计工作时间
	private double totalWorkingHours; //小时
	
	
	/**
	 * 功率因数
	 */
	private double powerFactor;
	
	
	
	
	public Integer getGenerationGrossId() {
		return generationGrossId;
	}
	public void setGenerationGrossId(Integer generationGrossId) {
		this.generationGrossId = generationGrossId;
	}
	public double getTotalWorkingHours() {
		return totalWorkingHours;
	}
	public void setTotalWorkingHours(double totalWorkingHours) {
		this.totalWorkingHours = totalWorkingHours;
	}
	public double getPowerFactor() {
		return powerFactor;
	}
	public void setPowerFactor(double powerFactor) {
		this.powerFactor = powerFactor;
	}
	public Integer getGenerationMonthId() {
		return generationMonthId;
	}
	public void setGenerationMonthId(Integer generationMonthId) {
		this.generationMonthId = generationMonthId;
	}
	
	public Integer getGenerationYearId() {
		return generationYearId;
	}
	public void setGenerationYearId(Integer generationYearId) {
		this.generationYearId = generationYearId;
	}
	public Integer getThreePhaseActivePowerId() {
		return threePhaseActivePowerId;
	}
	public void setThreePhaseActivePowerId(Integer threePhaseActivePowerId) {
		this.threePhaseActivePowerId = threePhaseActivePowerId;
	}
	public Integer getGenerationDailyId() {
		return generationDailyId;
	}
	public void setGenerationDailyId(Integer generationDailyId) {
		this.generationDailyId = generationDailyId;
	}
	public Integer getPowerStationId() {
		return powerStationId;
	}
	public void setPowerStationId(Integer powerStationId) {
		this.powerStationId = powerStationId;
	}
	public double getGenerationYear() {
		return generationYear;
	}
	public void setGenerationYear(double generationYear) {
		this.generationYear = generationYear;
	}
	public double getTotalInputPower() {
		return TotalInputPower;
	}
	public void setTotalInputPower(double totalInputPower) {
		TotalInputPower = totalInputPower;
	}
	public double getThreePhaseActivePower() {
		return ThreePhaseActivePower;
	}
	public void setThreePhaseActivePower(double threePhaseActivePower) {
		ThreePhaseActivePower = threePhaseActivePower;
	}
	public double getEfficiency() {
		return Efficiency;
	}
	public void setEfficiency(double efficiency) {
		Efficiency = efficiency;
	}
	public double getTemperature() {
		return Temperature;
	}
	public void setTemperature(double temperature) {
		Temperature = temperature;
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
	public List<MeasurePointValue> getMppt() {
		return mppt;
	}
	public void setMppt(List<MeasurePointValue> mppt) {
		this.mppt = mppt;
	}
	public List<MeasurePointValue> getPhaseVoltageParam() {
		return phaseVoltageParam;
	}
	public void setPhaseVoltageParam(List<MeasurePointValue> phaseVoltageParam) {
		this.phaseVoltageParam = phaseVoltageParam;
	}
	public double getGenerationDaily() {
		return generationDaily;
	}
	public void setGenerationDaily(double generationDaily) {
		this.generationDaily = generationDaily;
	}
	public double getGenerationGross() {
		return generationGross;
	}
	public void setGenerationGross(double generationGross) {
		this.generationGross = generationGross;
	}
	public double getGenerationMonth() {
		return generationMonth;
	}
	public void setGenerationMonth(double generationMonth) {
		this.generationMonth = generationMonth;
	}
	public Integer getLevel1() {
		return level1;
	}
	public void setLevel1(Integer level1) {
		this.level1 = level1;
	}
	public Integer getLevel2() {
		return level2;
	}
	public void setLevel2(Integer level2) {
		this.level2 = level2;
	}
	public Integer getLevel3() {
		return level3;
	}
	public void setLevel3(Integer level3) {
		this.level3 = level3;
	}
	public List<PowerTemperatureTime> getPowerTemperatureTime() {
		return powerTemperatureTime;
	}
	public void setPowerTemperatureTime(List<PowerTemperatureTime> powerTemperatureTime) {
		this.powerTemperatureTime = powerTemperatureTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
