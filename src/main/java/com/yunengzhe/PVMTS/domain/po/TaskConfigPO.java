package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class TaskConfigPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String configTime;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Integer type;
    private String defaultTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConfigTime() {
		return configTime;
	}
	public void setConfigTime(String configTime) {
		this.configTime = configTime;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDefaultTime() {
		return defaultTime;
	}
	public void setDefaultTime(String defaultTime) {
		this.defaultTime = defaultTime;
	}


}
