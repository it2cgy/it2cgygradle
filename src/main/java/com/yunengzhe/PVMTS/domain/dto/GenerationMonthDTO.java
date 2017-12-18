package com.yunengzhe.PVMTS.domain.dto;

import java.io.Serializable;

public class GenerationMonthDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer formId;
    private java.util.Date startTime;
    private java.util.Date endTime;
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	public java.util.Date getStartTime() {
		return startTime;
	}
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	public java.util.Date getEndTime() {
		return endTime;
	}
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
