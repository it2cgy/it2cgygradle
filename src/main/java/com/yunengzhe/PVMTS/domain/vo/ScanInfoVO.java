package com.yunengzhe.PVMTS.domain.vo;


/**
 * @ClassName:ScanInfoVO
 * @Description:TODO(上传扫描返回处理)
 * @author chenguiyang
 * @date 2017年6月16日上午10:45:28
 */
public class ScanInfoVO {
   private int equipmentId;
   private String equipmentType;
   private String equipmentInfo;
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
