package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class RolesMenusPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer roleId;
    private Integer menuId;
    private Integer handlePremission;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    
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
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getHandlePremission() {
		return handlePremission;
	}
	public void setHandlePremission(Integer handlePremission) {
		this.handlePremission = handlePremission;
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
