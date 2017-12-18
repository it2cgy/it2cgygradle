package com.yunengzhe.PVMTS.domain.vo.longi;

import java.io.Serializable;
/**
 * 电站设备数量
 * @author dell
 *
 */
public class EquipmentCounts implements Serializable {
	/**
	 * 逆变器数量
	 */
	private Integer inverterCounts;
	/**
	 * 直流电表数量
	 */
	private Integer ammeter1Counts;
	/**
	 * 单相交流电表数量
	 */
	private Integer ammeter2Counts;
	/**
	 * 三相交流电表数量
	 */
	private Integer ammeter3Counts;
	/**
	 * 测量箱数量
	 */
	private Integer measureBoxCounts;
	public Integer getInverterCounts() {
		return inverterCounts;
	}
	public void setInverterCounts(Integer inverterCounts) {
		this.inverterCounts = inverterCounts;
	}
	public Integer getAmmeter1Counts() {
		return ammeter1Counts;
	}
	public void setAmmeter1Counts(Integer ammeter1Counts) {
		this.ammeter1Counts = ammeter1Counts;
	}
	public Integer getAmmeter2Counts() {
		return ammeter2Counts;
	}
	public void setAmmeter2Counts(Integer ammeter2Counts) {
		this.ammeter2Counts = ammeter2Counts;
	}
	public Integer getAmmeter3Counts() {
		return ammeter3Counts;
	}
	public void setAmmeter3Counts(Integer ammeter3Counts) {
		this.ammeter3Counts = ammeter3Counts;
	}
	public Integer getMeasureBoxCounts() {
		return measureBoxCounts;
	}
	public void setMeasureBoxCounts(Integer measureBoxCounts) {
		this.measureBoxCounts = measureBoxCounts;
	}
	
}
