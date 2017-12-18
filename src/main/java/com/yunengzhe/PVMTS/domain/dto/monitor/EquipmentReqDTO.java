package com.yunengzhe.PVMTS.domain.dto.monitor;
 
public class EquipmentReqDTO {
	private String equipmentcontainerTableid;
	private String equipmentcontainerId;
	private String isThirdUser;
	
	public String getIsThirdUser() {
		return isThirdUser;
	}
	public void setIsThirdUser(String isThirdUser) {
		this.isThirdUser = isThirdUser;
	}
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

	
}
