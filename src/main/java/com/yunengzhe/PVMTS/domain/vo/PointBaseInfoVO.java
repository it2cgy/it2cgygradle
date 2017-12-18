package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;

public class PointBaseInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;//analogintID 
    private Integer measurementtypeId;
    private Integer equipmentcontainerId;
    private Integer equipmentcontainerTableid;
    private String measurementtypeName; 
    private String equipmentcontainerName; 
    private String name;//测点描述
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
	public Integer getMeasurementtypeId() {
		return measurementtypeId;
	}
	public void setMeasurementtypeId(Integer measurementtypeId) {
		this.measurementtypeId = measurementtypeId;
	}
	public Integer getEquipmentcontainerId() {
		return equipmentcontainerId;
	}
	public void setEquipmentcontainerId(Integer equipmentcontainerId) {
		this.equipmentcontainerId = equipmentcontainerId;
	}
	public Integer getEquipmentcontainerTableid() {
		return equipmentcontainerTableid;
	}
	public void setEquipmentcontainerTableid(Integer equipmentcontainerTableid) {
		this.equipmentcontainerTableid = equipmentcontainerTableid;
	}
	 
	public String getMeasurementtypeName() {
		return measurementtypeName;
	}
	public void setMeasurementtypeName(String measurementtypeName) {
		this.measurementtypeName = measurementtypeName;
	}
	public String getEquipmentcontainerName() {
		return equipmentcontainerName;
	}
	public void setEquipmentcontainerName(String equipmentcontainerName) {
		this.equipmentcontainerName = equipmentcontainerName;
	}
}
