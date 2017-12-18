package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class FaultSolvePO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer solveUser;//运维处理人员id1
    private java.util.Date solveTime;//处理时间
    private Integer solveResult;//是否已解决
    private String solveInfo;//故障处理说明
    private String workorderNum;//工单编号
    private Integer faultId;//故障id
    private Integer dispatcherId;//调度员id
    private String dispatcherState;//调度员说明
    private java.util.Date dispatcherTime;//调度员分配时间
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSolveUser() {
		return solveUser;
	}
	public void setSolveUser(Integer solveUser) {
		this.solveUser = solveUser;
	}
	public java.util.Date getSolveTime() {
		return solveTime;
	}
	public void setSolveTime(java.util.Date solveTime) {
		this.solveTime = solveTime;
	}
	public Integer getSolveResult() {
		return solveResult;
	}
	public void setSolveResult(Integer solveResult) {
		this.solveResult = solveResult;
	}
	public String getSolveInfo() {
		return solveInfo;
	}
	public void setSolveInfo(String solveInfo) {
		this.solveInfo = solveInfo;
	}
	public String getWorkorderNum() {
		return workorderNum;
	}
	public void setWorkorderNum(String workorderNum) {
		this.workorderNum = workorderNum;
	}
	public Integer getFaultId() {
		return faultId;
	}
	public void setFaultId(Integer faultId) {
		this.faultId = faultId;
	}
	public Integer getDispatcherId() {
		return dispatcherId;
	}
	public void setDispatcherId(Integer dispatcherId) {
		this.dispatcherId = dispatcherId;
	}
	public String getDispatcherState() {
		return dispatcherState;
	}
	public void setDispatcherState(String dispatcherState) {
		this.dispatcherState = dispatcherState;
	}
	public java.util.Date getDispatcherTime() {
		return dispatcherTime;
	}
	public void setDispatcherTime(java.util.Date dispatcherTime) {
		this.dispatcherTime = dispatcherTime;
	}
    
    
    


}
