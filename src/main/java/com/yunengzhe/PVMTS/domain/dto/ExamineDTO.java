package com.yunengzhe.PVMTS.domain.dto;

import java.io.Serializable;

public class ExamineDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer checkupType;//检查项目类型
    private String checkupName;//检查项目名称
    private String checkupContent;//检查内容
    private Integer type;//0-基本检查项目  1-用户自定义检查项目
    private Integer orderNum;//工单编号
    
    
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getCheckupType() {
		return checkupType;
	}
	public void setCheckupType(Integer checkupType) {
		this.checkupType = checkupType;
	}
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
