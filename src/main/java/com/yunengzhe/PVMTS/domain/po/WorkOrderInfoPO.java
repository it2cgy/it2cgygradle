package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class WorkOrderInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String orderNumber;
    private Integer createUser;
    private java.util.Date createTime;
    private Integer maintenanceUser;
    private java.util.Date allocateTime;
    private Integer state;
    private java.util.Date updateTime;
    private String remarks;
    private Integer physicalState;
    private Integer departureState;
    private Integer powerstationId;
    private String powerstationName;
    private String	maintenanceName;
    
	public String getMaintenanceName() {
		return maintenanceName;
	}
	public void setMaintenanceName(String maintenanceName) {
		this.maintenanceName = maintenanceName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public Integer getMaintenanceUser() {
		return maintenanceUser;
	}
	public void setMaintenanceUser(Integer maintenanceUser) {
		this.maintenanceUser = maintenanceUser;
	}
	public java.util.Date getAllocateTime() {
		return allocateTime;
	}
	public void setAllocateTime(java.util.Date allocateTime) {
		this.allocateTime = allocateTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getPhysicalState() {
		return physicalState;
	}
	public void setPhysicalState(Integer physicalState) {
		this.physicalState = physicalState;
	}
	public Integer getDepartureState() {
		return departureState;
	}
	public void setDepartureState(Integer departureState) {
		this.departureState = departureState;
	}
	public Integer getPowerstationId() {
		return powerstationId;
	}
	public void setPowerstationId(Integer powerstationId) {
		this.powerstationId = powerstationId;
	}
	public String getPowerstationName() {
		return powerstationName;
	}
	public void setPowerstationName(String powerstationName) {
		this.powerstationName = powerstationName;
	}

    
    
    

}
