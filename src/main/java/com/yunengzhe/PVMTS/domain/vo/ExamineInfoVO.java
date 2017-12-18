package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.AttachmentPO;

public class ExamineInfoVO {

    private Integer isSolve;
    private String explainInfo;
    private List<AttachmentPO> attachmentList;
    
    
	public List<AttachmentPO> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<AttachmentPO> attachmentList) {
		this.attachmentList = attachmentList;
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
    
}
