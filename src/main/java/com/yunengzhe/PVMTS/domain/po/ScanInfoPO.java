package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

/**
 * @ClassName:ScanInfoPO
 * @Description:TODO(扫描上传处理)
 * @author chenguiyang
 * @date 2017年6月16日上午10:13:48
 */
public class ScanInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer equipmentId;
    private String equipmentType;
    private String equipmentInfo;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
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
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}


}
