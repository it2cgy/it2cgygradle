package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class PushMailPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer roleId;
    private Integer push;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getPush() {
		return push;
	}
	public void setPush(Integer push) {
		this.push = push;
	}


}
