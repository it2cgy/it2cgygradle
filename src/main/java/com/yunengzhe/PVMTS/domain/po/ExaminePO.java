package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class ExaminePO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer checkupType;
    private String checkupName;
    private String checkupContent;
    private Integer type;//0-基本检查项目  1-用户自定义检查项目
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
