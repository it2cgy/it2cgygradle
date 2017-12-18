package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;

public class EquipmentInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private String equipmentcontainerName;//设备名称
    private String equipmentcontainerNameEn;//设备名称
    private Integer equipmentTableId;
    private Integer equipmentId;
    
	public String getEquipmentcontainerNameEn() {
		return equipmentcontainerNameEn;
	}
	public void setEquipmentcontainerNameEn(String equipmentcontainerNameEn) {
		this.equipmentcontainerNameEn = equipmentcontainerNameEn;
	}
	public String getEquipmentcontainerName() {
		return equipmentcontainerName;
	}
	public void setEquipmentcontainerName(String equipmentcontainerName) {
		this.equipmentcontainerName = equipmentcontainerName;
	}
	public Integer getEquipmentTableId() {
		return equipmentTableId;
	}
	public void setEquipmentTableId(Integer equipmentTableId) {
		this.equipmentTableId = equipmentTableId;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	
}
