package com.yunengzhe.PVMTS.domain.dto;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.AttachmentPO;

/**
 * @ClassName:FaultSolveDTO
 * @Description:TODO(故障处理请求参数)
 * @author chenguiyang
 * @date 2017年6月20日下午3:57:01
 */
public class FaultSolveDTO {
	
	private Integer faultId;//故障id
	private Integer solveId;
    private Integer solveResult;//是否已解决
    private String solveInfo;//故障处理说明
    private Integer alarmId;
    
    /**
     * 附件列表
     */
    private List<AttachmentPO> fileInfo;
    
    
	public Integer getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}
	public List<AttachmentPO> getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(List<AttachmentPO> fileInfo) {
		this.fileInfo = fileInfo;
	}

	public Integer getFaultId() {
		return faultId;
	}
	public void setFaultId(Integer faultId) {
		this.faultId = faultId;
	}
	public Integer getSolveId() {
		return solveId;
	}
	public void setSolveId(Integer solveId) {
		this.solveId = solveId;
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
	

	
	
}
