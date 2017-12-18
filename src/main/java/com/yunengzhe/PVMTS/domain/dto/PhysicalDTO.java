package com.yunengzhe.PVMTS.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.yunengzhe.PVMTS.domain.po.AttachmentPO;

/**
 * @ClassName:PhysicalDTO
 * @Description:TODO(电站体检)
 * @author chenguiyang
 * @date 2017年6月6日下午7:12:33
 */
public class PhysicalDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;//id
    private Integer examineId;//检查标砖id
    private Integer isSolve;//是否需要上报调度中心
    private String  explainInfo;//检查说明
    private String  workorderNum;//工单编号
	//    
//    /**
//     * 附件列表
//     */
    private List<AttachmentPO> fileInfo;
    
	public List<AttachmentPO> getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(List<AttachmentPO> fileInfo) {
		this.fileInfo = fileInfo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getExamineId() {
		return examineId;
	}
	public void setExamineId(Integer examineId) {
		this.examineId = examineId;
	}
	public Integer getIsSolve() {
		return isSolve;
	}
	public void setIsSolve(Integer isSolve) {
		this.isSolve = isSolve;
	}
	public String getExplainInfo() {
		return explainInfo;
	}
	public void setExplainInfo(String explainInfo) {
		this.explainInfo = explainInfo;
	}
	public String getWorkorderNum() {
		return workorderNum;
	}
	public void setWorkorderNum(String workorderNum) {
		this.workorderNum = workorderNum;
	}


	
}

