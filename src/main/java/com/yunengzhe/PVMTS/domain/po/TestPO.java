package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class TestPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String description;
    private java.util.Date datetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public java.util.Date getDatetime() {
		return datetime;
	}
	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}


}
