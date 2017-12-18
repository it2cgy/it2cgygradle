package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class PhysicalPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer examineId;
    private Integer isSolve;
    private String explainInfo;
    private String workorderNum;
    private String checkupContent;//检查标准内容
    private String checkupName;//检查标准名称
    
	public String getCheckupName() {
		return checkupName;
	}
	public void setCheckupName(String checkupName) {
		this.checkupName = checkupName;
	}
	public String getCheckupContent() {
		return checkupContent;
	}
	public void setCheckupContent(String checkupContent) {
		this.checkupContent = checkupContent;
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
