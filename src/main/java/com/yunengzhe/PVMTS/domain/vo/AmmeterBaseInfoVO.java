package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
/**
 * 电表基本信息对象
 * @author dell
 *
 */
public class AmmeterBaseInfoVO implements Serializable {
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
    private java.math.BigDecimal meterType;
	public java.math.BigDecimal getMeterType() {
		return meterType;
	}
	public void setMeterType(java.math.BigDecimal meterType) {
		this.meterType = meterType;
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
