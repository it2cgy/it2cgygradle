package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class DeviceRatedPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String equipmentId;
    private String equipmentType;
    private String equipmentDescription;
    private String ratedPower;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getEquipmentDescription() {
		return equipmentDescription;
	}
	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}
	public String getRatedPower() {
		return ratedPower;
	}
	public void setRatedPower(String ratedPower) {
		this.ratedPower = ratedPower;
	}


}
