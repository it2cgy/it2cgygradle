package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;


/**
 * @ClassName:WorkOrderFaultPO
 * @Description:TODO(工单与故障信息映射表)
 * @author chenguiyang
 * @date 2017年6月7日上午10:51:29
 */
public class WorkOrderFaultPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String  workorderId;//工单编号
    private Integer faultId;//故障信息id
    private Integer isSolve;//是否已处理
    private String  fault_message;//故障说明

	public String getFault_message() {
		return fault_message;
	}
	public void setFault_message(String fault_message) {
		this.fault_message = fault_message;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWorkorderId() {
		return workorderId;
	}
	public void setWorkorderId(String workorderId) {
		this.workorderId = workorderId;
	}
	public Integer getFaultId() {
		return faultId;
	}
	public void setFaultId(Integer faultId) {
		this.faultId = faultId;
	}
	public Integer getIsSolve() {
		return isSolve;
	}
	public void setIsSolve(Integer isSolve) {
		this.isSolve = isSolve;
	}
    
    
    


}
