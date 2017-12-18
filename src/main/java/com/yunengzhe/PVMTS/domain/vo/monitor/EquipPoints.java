package com.yunengzhe.PVMTS.domain.vo.monitor;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.PointInfoPO;

public class EquipPoints {
	private Integer equipmentcontainerId;
    private Integer equipmentcontainerTableid;
    private String name;
    private String nameEn;
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	private List<PointInfoPO> points;
    
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
	public List<PointInfoPO> getPoints() {
		return points;
	}
	public void setPoints(List<PointInfoPO> points) {
		this.points = points;
	}
 
    
    
}
