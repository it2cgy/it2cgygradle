package com.yunengzhe.PVMTS.domain.dto;

/**
 * @ClassName:ScanInfoDTO
 * @Description:TODO(扫码上传请求参数dto)
 * @author chenguiyang
 * @date 2017年6月16日上午10:18:36
 */
public class ScanInfoDTO {
	private int equipmentId;//设备id
	private String equipmentType;//设备类型
	private String equipmentInfo;//设备信息
	
	
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getEquipmentInfo() {
		return equipmentInfo;
	}
	public void setEquipmentInfo(String equipmentInfo) {
		this.equipmentInfo = equipmentInfo;
	}
	
	
	
}
