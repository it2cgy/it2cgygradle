package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
import java.util.Date;
/**
 * 电表信息对象
 * @author dell
 *
 */
public class AmmeterInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 电表ID
	 */
    private Integer id;
    /**
     * 电表名称
     */
    private String name;
    /**
     * 电表型号
     */
    private String model;
    /**
     * 电表编号
     */
    private String serialNumber;
    /**
     * 总用功功率
     */
    private double threePhaseActivePower;
    /**
     * 总电能
     */
    private double ReverseActiveP;
    private double generationDaily;
    private double generationMonth;
    private double generationYear;
    private double generationGross;
    private double unifiedPowerGenerationDaily;
    private double unifiedPowerGenerationMonth;
    private double unifiedPowerGenerationYear;
    private double unifiedPositiveActiveP;//总归化值
    private double ratedPower;//装机容量
	
	
	public double getRatedPower() {
		return ratedPower;
	}
	public void setRatedPower(double ratedPower) {
		this.ratedPower = ratedPower;
	}
    
    public double getUnifiedPositiveActiveP() {
		return unifiedPositiveActiveP;
	}
	public void setUnifiedPositiveActiveP(double unifiedPositiveActiveP) {
		this.unifiedPositiveActiveP = unifiedPositiveActiveP;
	}
	public double getGenerationDaily() {
		return generationDaily;
	}
	public void setGenerationDaily(double generationDaily) {
		this.generationDaily = generationDaily;
	}
	public double getGenerationMonth() {
		return generationMonth;
	}
	public void setGenerationMonth(double generationMonth) {
		this.generationMonth = generationMonth;
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
	public double getThreePhaseActivePower() {
		return threePhaseActivePower;
	}
	public void setThreePhaseActivePower(double threePhaseActivePower) {
		this.threePhaseActivePower = threePhaseActivePower;
	}
	public double getReverseActiveP() {
		return ReverseActiveP;
	}
	public void setReverseActiveP(double reverseActiveP) {
		ReverseActiveP = reverseActiveP;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	private Date updateTime;
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
