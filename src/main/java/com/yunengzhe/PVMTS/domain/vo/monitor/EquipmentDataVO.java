package com.yunengzhe.PVMTS.domain.vo.monitor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipmentDataVO {
	private String equipmentcontainerTableid;
	private String equipmentcontainerId;
	private List<AnalogStructureData> data;
	
	public String getEquipmentcontainerTableid() {
		return equipmentcontainerTableid;
	}
	public void setEquipmentcontainerTableid(String equipmentcontainerTableid) {
		this.equipmentcontainerTableid = equipmentcontainerTableid;
	}
	public String getEquipmentcontainerId() {
		return equipmentcontainerId;
	}
	public void setEquipmentcontainerId(String equipmentcontainerId) {
		this.equipmentcontainerId = equipmentcontainerId;
	}
	public List<AnalogStructureData> getData() {
		return data;
	}
	public void setData(List<AnalogStructureData> data) {
		this.data = data;
	} 
	
}
