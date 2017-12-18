package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class SparepartPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String sparepartName;
    private java.util.Date createTime;
    private java.util.Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSparepartName() {
		return sparepartName;
	}
	public void setSparepartName(String sparepartName) {
		this.sparepartName = sparepartName;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}


}
