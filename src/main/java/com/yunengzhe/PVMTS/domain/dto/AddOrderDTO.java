package com.yunengzhe.PVMTS.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.yunengzhe.PVMTS.domain.po.DeparturePO;
import com.yunengzhe.PVMTS.domain.po.PhysicalPO;

/**
 * @ClassName:AddOrderDTO
 * @Description:TODO(新增工单dto)
 * @author chenguiyang
 * @date 2017年6月6日下午6:48:04
 */
public class AddOrderDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String orderNumber;//工单编号  日期时间+id  201706060503+静态常量++   递增
    private Integer createUser;//创建人员
    private java.util.Date createTime;//创建时间
    private Integer maintenanceUser;//分配的运维人员
    private java.util.Date allocateTime;//分配时间
    private Integer state;//工单状态  设置默认
    private java.util.Date updateTime;//更新时间
    private String remarks;//备注
    private Integer physicalState;//电站体检状态
    private Integer departureState;//离场申请状态
    private Integer powerstationId;//电站id
    private String powerstationName;//电站名称
    private List<FaultInfoDTO> faultInfo;//选择的故障信息
    private List<PhysicalPO>  physical;//电站体检
    private List<DeparturePO> departture;//离场申请
    
    
	public List<FaultInfoDTO> getFaultInfo() {
		return faultInfo;
	}
	public void setFaultInfo(List<FaultInfoDTO> faultInfo) {
		this.faultInfo = faultInfo;
	}

	public List<DeparturePO> getDepartture() {
		return departture;
	}
	public void setDepartture(List<DeparturePO> departture) {
		this.departture = departture;
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
	public List<PhysicalPO> getPhysical() {
		return physical;
	}
	public void setPhysical(List<PhysicalPO> physical) {
		this.physical = physical;
	}
    
    
    
    
    
}
