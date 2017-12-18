package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

public class PhysicalVO {

    private Integer id;
    private Integer examineId;
    private String workorderNum;
    private String checkupContent;//检查标准内容
    private String checkupName;//检查标准名称
    
    private List<ExamineInfoVO> examineInfo;
    
	public String getCheckupContent() {
		return checkupContent;
	}
	public void setCheckupContent(String checkupContent) {
		this.checkupContent = checkupContent;
	}
	public String getCheckupName() {
		return checkupName;
	}
	public void setCheckupName(String checkupName) {
		this.checkupName = checkupName;
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
	public String getWorkorderNum() {
		return workorderNum;
	}
	public void setWorkorderNum(String workorderNum) {
		this.workorderNum = workorderNum;
	}
	public List<ExamineInfoVO> getExamineInfo() {
		return examineInfo;
	}
	public void setExamineInfo(List<ExamineInfoVO> examineInfo) {
		this.examineInfo = examineInfo;
	}

    
    
}
