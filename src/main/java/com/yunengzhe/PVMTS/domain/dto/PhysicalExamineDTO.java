package com.yunengzhe.PVMTS.domain.dto;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.AttachmentPO;

public class PhysicalExamineDTO {

    private Integer examineId;
    private Integer isSolve;
    private String  explainInfo;
    private List<AttachmentPO> attachmentList;

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

	public List<AttachmentPO> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<AttachmentPO> attachmentList) {
		this.attachmentList = attachmentList;
	}
    
    
    
}
