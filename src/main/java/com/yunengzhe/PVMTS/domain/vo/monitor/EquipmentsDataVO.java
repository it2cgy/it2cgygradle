package com.yunengzhe.PVMTS.domain.vo.monitor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipmentsDataVO {
	 private List<EquipmentDataVO> equipments;

	public List<EquipmentDataVO> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<EquipmentDataVO> equipments) {
		this.equipments = equipments;
	}
	 
	 
}
