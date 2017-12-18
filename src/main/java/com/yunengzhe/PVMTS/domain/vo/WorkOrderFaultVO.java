package com.yunengzhe.PVMTS.domain.vo;


/**
 * @ClassName:WorkOrderFaultVO
 * @Description:TODO(工单故障列表返回参数)
 * @author chenguiyang
 * @date 2017年6月7日上午11:04:53
 */
public class WorkOrderFaultVO {
    private Integer faultId;//故障id
    private Integer isSolve;//是否已解决
    private String  faultExplain;//故障说明
    
    
	public String getFaultExplain() {
		return faultExplain;
	}
	public void setFaultExplain(String faultExplain) {
		this.faultExplain = faultExplain;
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
