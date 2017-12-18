package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
import java.util.List;

import com.yunengzhe.PVMTS.domain.po.WorkOrderInfoPO;

/**
 * @ClassName:WorkOrderInfoVO
 * @Description:TODO(工单处理返回参数)
 * @author chenguiyang
 * @date 2017年6月6日上午9:48:43
 */
public class WorkOrderInfoVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer orderId;//工单id
    private String orderNumber;		//工单编号
    private Integer createUser;			//创建人员
    private java.util.Date createTime;	//创建时间
    private Integer maintenanceUser;	//运维人员
    private java.util.Date allocateTime;//分配时间
    private Integer state;				// 工单状态 0-未发布 1-进行中 2已完成
    private Integer physicalState;		//电站体检状态
    private Integer departureState;		//离场申请状态
    private Integer faultNum;
    private Integer faultState;
    private Integer powerstationId;//d电站id
    private String powerstationName;//电站名称
    private String maintenanceName;//运维人员名称
    private String remarks;
    
    
    
    
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMaintenanceName() {
		return maintenanceName;
	}
	public void setMaintenanceName(String maintenanceName) {
		this.maintenanceName = maintenanceName;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public Integer getFaultNum() {
		return faultNum;
	}
	public void setFaultNum(Integer faultNum) {
		this.faultNum = faultNum;
	}
	public Integer getFaultState() {
		return faultState;
	}
	public void setFaultState(Integer faultState) {
		this.faultState = faultState;
	}
    
    
	
    
    
}
