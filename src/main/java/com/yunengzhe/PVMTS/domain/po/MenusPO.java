package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class MenusPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private java.util.Date createTime;
    private java.util.Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
