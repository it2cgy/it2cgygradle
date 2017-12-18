package com.yunengzhe.PVMTS.domain.vo;

public class AmmeterDetailsVO {
	private String name;
	private String serialNumber;
	private Integer meterType;
	private Integer powerId;
	private Integer reverseActivePDailyId;
	private Integer reverseActivePMonthId;
	private Integer reverseActivePYearId;
	private Integer reverseActivePGrossId;
	private double phaseVoltageA;
	private double phaseVoltageB;
	private double phaseVoltageC;
	private double phaseCurrentA;
	private double phaseCurrentB;
	private double phaseCurrentC;
	private double activePowerA;
	private double activePowerB;
	private double activePowerC;
	private double threePhaseActivePower;
	private double threePhaseReactivePower;
	private double powerFactor;
	private double frequency;
	private double positiveActiveFlatP;
	private double positiveActivePeakP;
	private double positiveActiveValleyP;
	private double positiveActiveSharpP;
	private double reverseActiveFlatP;
	private double reverseActivePeakP;
	private double reverseActiveValleyP;
	private double reverseActiveSharpP;
	private double positiveActivePDaily;
	private double reverseActivePDaily;
	private double positiveActivePMonth;
	private double reverseActivePMonth;
	private double positiveActivePYear;
	private double reverseActivePYear;
	private double positiveActiveP;
	private double reverseActiveP;
	//直流
	private double voltage;
	private double current;
	private double generatedActivePower;
	/**
	 * 三相功率
	 * @return
	 */
	private double lookedAtEachOtherInPowerA;
	private double lookedAtEachOtherInPowerB;
	private double lookedAtEachOtherInPowerC;
	private double reactivePowerA;
	private double reactivePowerB;
	private double reactivePowerC;
	/**
	 * 单相
	 * @return
	 */
	private double totalApparentPower;
	
	public double getTotalApparentPower() {
		return totalApparentPower;
	}
	public void setTotalApparentPower(double totalApparentPower) {
		this.totalApparentPower = totalApparentPower;
	}
	public double getLookedAtEachOtherInPowerA() {
		return lookedAtEachOtherInPowerA;
	}
	public void setLookedAtEachOtherInPowerA(double lookedAtEachOtherInPowerA) {
		this.lookedAtEachOtherInPowerA = lookedAtEachOtherInPowerA;
	}
	public double getLookedAtEachOtherInPowerB() {
		return lookedAtEachOtherInPowerB;
	}
	public void setLookedAtEachOtherInPowerB(double lookedAtEachOtherInPowerB) {
		this.lookedAtEachOtherInPowerB = lookedAtEachOtherInPowerB;
	}
	public double getLookedAtEachOtherInPowerC() {
		return lookedAtEachOtherInPowerC;
	}
	public void setLookedAtEachOtherInPowerC(double lookedAtEachOtherInPowerC) {
		this.lookedAtEachOtherInPowerC = lookedAtEachOtherInPowerC;
	}
	public double getReactivePowerA() {
		return reactivePowerA;
	}
	public void setReactivePowerA(double reactivePowerA) {
		this.reactivePowerA = reactivePowerA;
	}
	public double getReactivePowerB() {
		return reactivePowerB;
	}
	public void setReactivePowerB(double reactivePowerB) {
		this.reactivePowerB = reactivePowerB;
	}
	public double getReactivePowerC() {
		return reactivePowerC;
	}
	public void setReactivePowerC(double reactivePowerC) {
		this.reactivePowerC = reactivePowerC;
	}
	public Integer getReverseActivePGrossId() {
		return reverseActivePGrossId;
	}
	public void setReverseActivePGrossId(Integer reverseActivePGrossId) {
		this.reverseActivePGrossId = reverseActivePGrossId;
	}
	public Integer getReverseActivePDailyId() {
		return reverseActivePDailyId;
	}
	public void setReverseActivePDailyId(Integer reverseActivePDailyId) {
		this.reverseActivePDailyId = reverseActivePDailyId;
	}
	public Integer getReverseActivePMonthId() {
		return reverseActivePMonthId;
	}
	public void setReverseActivePMonthId(Integer reverseActivePMonthId) {
		this.reverseActivePMonthId = reverseActivePMonthId;
	}
	public Integer getReverseActivePYearId() {
		return reverseActivePYearId;
	}
	public void setReverseActivePYearId(Integer reverseActivePYearId) {
		this.reverseActivePYearId = reverseActivePYearId;
	}
	public Integer getPowerId() {
		return powerId;
	}
	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}
	public double getVoltage() {
		return voltage;
	}
	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}
	public double getCurrent() {
		return current;
	}
	public void setCurrent(double current) {
		this.current = current;
	}
	public double getGeneratedActivePower() {
		return generatedActivePower;
	}
	public void setGeneratedActivePower(double generatedActivePower) {
		this.generatedActivePower = generatedActivePower;
	}
	public double getPositiveActiveP() {
		return positiveActiveP;
	}
	public void setPositiveActiveP(double positiveActiveP) {
		this.positiveActiveP = positiveActiveP;
	}
	public double getReverseActiveP() {
		return reverseActiveP;
	}
	public void setReverseActiveP(double reverseActiveP) {
		this.reverseActiveP = reverseActiveP;
	}
	public double getPositiveActivePDaily() {
		return positiveActivePDaily;
	}
	public void setPositiveActivePDaily(double positiveActivePDaily) {
		this.positiveActivePDaily = positiveActivePDaily;
	}
	public double getReverseActivePDaily() {
		return reverseActivePDaily;
	}
	public void setReverseActivePDaily(double reverseActivePDaily) {
		this.reverseActivePDaily = reverseActivePDaily;
	}
	public double getPositiveActivePMonth() {
		return positiveActivePMonth;
	}
	public void setPositiveActivePMonth(double positiveActivePMonth) {
		this.positiveActivePMonth = positiveActivePMonth;
	}
	public double getReverseActivePMonth() {
		return reverseActivePMonth;
	}
	public void setReverseActivePMonth(double reverseActivePMonth) {
		this.reverseActivePMonth = reverseActivePMonth;
	}
	public double getPositiveActivePYear() {
		return positiveActivePYear;
	}
	public void setPositiveActivePYear(double positiveActivePYear) {
		this.positiveActivePYear = positiveActivePYear;
	}
	public double getReverseActivePYear() {
		return reverseActivePYear;
	}
	public void setReverseActivePYear(double reverseActivePYear) {
		this.reverseActivePYear = reverseActivePYear;
	}
	public Integer getMeterType() {
		return meterType;
	}
	public void setMeterType(Integer meterType) {
		this.meterType = meterType;
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
	public double getPhaseVoltageA() {
		return phaseVoltageA;
	}
	public void setPhaseVoltageA(double phaseVoltageA) {
		this.phaseVoltageA = phaseVoltageA;
	}
	public double getPhaseVoltageB() {
		return phaseVoltageB;
	}
	public void setPhaseVoltageB(double phaseVoltageB) {
		this.phaseVoltageB = phaseVoltageB;
	}
	public double getPhaseVoltageC() {
		return phaseVoltageC;
	}
	public void setPhaseVoltageC(double phaseVoltageC) {
		this.phaseVoltageC = phaseVoltageC;
	}
	public double getPhaseCurrentA() {
		return phaseCurrentA;
	}
	public void setPhaseCurrentA(double phaseCurrentA) {
		this.phaseCurrentA = phaseCurrentA;
	}
	public double getPhaseCurrentB() {
		return phaseCurrentB;
	}
	public void setPhaseCurrentB(double phaseCurrentB) {
		this.phaseCurrentB = phaseCurrentB;
	}
	public double getPhaseCurrentC() {
		return phaseCurrentC;
	}
	public void setPhaseCurrentC(double phaseCurrentC) {
		this.phaseCurrentC = phaseCurrentC;
	}
	public double getActivePowerA() {
		return activePowerA;
	}
	public void setActivePowerA(double activePowerA) {
		this.activePowerA = activePowerA;
	}
	public double getActivePowerB() {
		return activePowerB;
	}
	public void setActivePowerB(double activePowerB) {
		this.activePowerB = activePowerB;
	}
	public double getActivePowerC() {
		return activePowerC;
	}
	public void setActivePowerC(double activePowerC) {
		this.activePowerC = activePowerC;
	}
	public double getThreePhaseActivePower() {
		return threePhaseActivePower;
	}
	public void setThreePhaseActivePower(double threePhaseActivePower) {
		this.threePhaseActivePower = threePhaseActivePower;
	}
	public double getThreePhaseReactivePower() {
		return threePhaseReactivePower;
	}
	public void setThreePhaseReactivePower(double threePhaseReactivePower) {
		this.threePhaseReactivePower = threePhaseReactivePower;
	}
	public double getPowerFactor() {
		return powerFactor;
	}
	public void setPowerFactor(double powerFactor) {
		this.powerFactor = powerFactor;
	}
	public double getFrequency() {
		return frequency;
	}
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	public double getPositiveActiveFlatP() {
		return positiveActiveFlatP;
	}
	public void setPositiveActiveFlatP(double positiveActiveFlatP) {
		this.positiveActiveFlatP = positiveActiveFlatP;
	}
	public double getPositiveActivePeakP() {
		return positiveActivePeakP;
	}
	public void setPositiveActivePeakP(double positiveActivePeakP) {
		this.positiveActivePeakP = positiveActivePeakP;
	}
	public double getPositiveActiveValleyP() {
		return positiveActiveValleyP;
	}
	public void setPositiveActiveValleyP(double positiveActiveValleyP) {
		this.positiveActiveValleyP = positiveActiveValleyP;
	}
	public double getPositiveActiveSharpP() {
		return positiveActiveSharpP;
	}
	public void setPositiveActiveSharpP(double positiveActiveSharpP) {
		this.positiveActiveSharpP = positiveActiveSharpP;
	}
	public double getReverseActiveFlatP() {
		return reverseActiveFlatP;
	}
	public void setReverseActiveFlatP(double reverseActiveFlatP) {
		this.reverseActiveFlatP = reverseActiveFlatP;
	}
	public double getReverseActivePeakP() {
		return reverseActivePeakP;
	}
	public void setReverseActivePeakP(double reverseActivePeakP) {
		this.reverseActivePeakP = reverseActivePeakP;
	}
	public double getReverseActiveValleyP() {
		return reverseActiveValleyP;
	}
	public void setReverseActiveValleyP(double reverseActiveValleyP) {
		this.reverseActiveValleyP = reverseActiveValleyP;
	}
	public double getReverseActiveSharpP() {
		return reverseActiveSharpP;
	}
	public void setReverseActiveSharpP(double reverseActiveSharpP) {
		this.reverseActiveSharpP = reverseActiveSharpP;
	}
	
}
