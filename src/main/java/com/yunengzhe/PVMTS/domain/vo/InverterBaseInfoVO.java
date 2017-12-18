package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
/**
 * 逆变器基本信息对象
 * @author dell
 *
 */
public class InverterBaseInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 逆变器ID
	 */
    private Integer id;
    /**
     * 逆变器名称
     */
    private String name;
    /**
     * 逆变器型号
     */
    private String model;
    /**
     * 逆变器编号
     */
    private String serialNumber;
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
