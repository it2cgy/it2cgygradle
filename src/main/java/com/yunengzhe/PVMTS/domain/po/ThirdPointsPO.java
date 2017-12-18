package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class ThirdPointsPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String pointType;
    private Integer equipmentId;
    private String name;
    private Integer equipmentType;
    private Integer pointId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPointType() {
		return pointType;
	}
	public void setPointType(String pointType) {
		this.pointType = pointType;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
	}
	public Integer getPointId() {
		return pointId;
	}
	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}


}
