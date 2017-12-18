package com.yunengzhe.PVMTS.domain.vo.longi;

import java.io.Serializable;
import java.util.List;
/**
 * 测量箱和逆变器基本信息
 * @author dell
 *
 */
public class MeasurementBoxAndInverterVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;//测量箱id
    private String name;//测量箱名称
    private String englishname;//测量箱名称
    private String powerStationId;//电站id
    private Integer inverterId;
    private String inverterName;
    private String inverterSerialNumber;
    private String inverterModel;
    
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
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
	public String getPowerStationId() {
		return powerStationId;
	}
	public void setPowerStationId(String powerStationId) {
		this.powerStationId = powerStationId;
	}
	public Integer getInverterId() {
		return inverterId;
	}
	public void setInverterId(Integer inverterId) {
		this.inverterId = inverterId;
	}
	public String getInverterName() {
		return inverterName;
	}
	public void setInverterName(String inverterName) {
		this.inverterName = inverterName;
	}
	public String getInverterSerialNumber() {
		return inverterSerialNumber;
	}
	public void setInverterSerialNumber(String inverterSerialNumber) {
		this.inverterSerialNumber = inverterSerialNumber;
	}
	public String getInverterModel() {
		return inverterModel;
	}
	public void setInverterModel(String inverterModel) {
		this.inverterModel = inverterModel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
