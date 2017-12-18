package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;

public class PointInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;//analogintID
    private String measurementtypeDescription;//测点描述
    private String equipmentcontainerName;//设备名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMeasurementtypeDescription() {
		return measurementtypeDescription;
	}
	public void setMeasurementtypeDescription(String measurementtypeDescription) {
		this.measurementtypeDescription = measurementtypeDescription;
	}
	public String getEquipmentcontainerName() {
		return equipmentcontainerName;
	}
	public void setEquipmentcontainerName(String equipmentcontainerName) {
		this.equipmentcontainerName = equipmentcontainerName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
