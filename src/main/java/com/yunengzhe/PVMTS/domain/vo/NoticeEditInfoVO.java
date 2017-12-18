package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;

public class NoticeEditInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private String name;
    private String contentHtml;
    private String remarks;
    private Integer type;
   	public Integer getType() {
   		return type;
   	}
   	public void setType(Integer type) {
   		this.type = type;
   	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentHtml() {
		return contentHtml;
	}
	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
