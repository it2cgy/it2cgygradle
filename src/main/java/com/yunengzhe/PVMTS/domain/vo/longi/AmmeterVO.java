package com.yunengzhe.PVMTS.domain.vo.longi;

import java.io.Serializable;
/**
 * 电表基本信息对象
 * @author dell
 *
 */
public class AmmeterVO implements Serializable {
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
     * 总发电量
     */
    private double generationGross;
    /**
     * 日发电量
     */
    private double generationDaliy;
    /**
     * 功率
     */
    private double power;
    /**
     * 电表类型
     */
    private Integer ammeterType;
    
    
	public double getGenerationDaliy() {
		return generationDaliy;
	}
	public void setGenerationDaliy(double generationDaliy) {
		this.generationDaliy = generationDaliy;
	}
	public Integer getAmmeterType() {
		return ammeterType;
	}
	public void setAmmeterType(Integer ammeterType) {
		this.ammeterType = ammeterType;
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
	public double getGenerationGross() {
		return generationGross;
	}
	public void setGenerationGross(double generationGross) {
		this.generationGross = generationGross;
	}
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
