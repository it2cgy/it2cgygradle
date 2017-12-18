package com.yunengzhe.PVMTS.domain.vo.longi;

import java.io.Serializable;

public class InverterToMeterVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer inverterId;
    private Integer electricMeterId;
    /**
     * 电表名称
     */
    private String meterName;
    /**
     * 电表型号
     */
    private String meterModel;
    /**
     * 电表编号
     */
    private String meterSerialNumber;
    /**
     * 电表类型
     */
    private Integer meterType;
    
	public Integer getMeterType() {
		return meterType;
	}
	public void setMeterType(Integer meterType) {
		this.meterType = meterType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInverterId() {
		return inverterId;
	}
	public void setInverterId(Integer inverterId) {
		this.inverterId = inverterId;
	}
	public Integer getElectricMeterId() {
		return electricMeterId;
	}
	public void setElectricMeterId(Integer electricMeterId) {
		this.electricMeterId = electricMeterId;
	}
	public String getMeterName() {
		return meterName;
	}
	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}
	public String getMeterModel() {
		return meterModel;
	}
	public void setMeterModel(String meterModel) {
		this.meterModel = meterModel;
	}
	public String getMeterSerialNumber() {
		return meterSerialNumber;
	}
	public void setMeterSerialNumber(String meterSerialNumber) {
		this.meterSerialNumber = meterSerialNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
