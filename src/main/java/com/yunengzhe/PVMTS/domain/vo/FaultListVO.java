package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

/**
 * @ClassName:WorkOrderFaultVO
 * @Description:TODO(工单与故障信息列表返回参数实体类)
 * @author chenguiyang
 * @date 2017年6月7日上午11:00:48
 */
public class FaultListVO {

	
	private Integer orderNum;//工单编号
    private Integer powerstationId;//电站id
    private String powerstationName;//电站名称
    private java.util.Date allocateTime;//分配时间
    private List<WorkOrderFaultVO> faultInfo;//故障列表信息
    
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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
	public java.util.Date getAllocateTime() {
		return allocateTime;
	}
	public void setAllocateTime(java.util.Date allocateTime) {
		this.allocateTime = allocateTime;
	}
	public List<WorkOrderFaultVO> getFaultInfo() {
		return faultInfo;
	}
	public void setFaultInfo(List<WorkOrderFaultVO> faultInfo) {
		this.faultInfo = faultInfo;
	}
    
    
    
}
