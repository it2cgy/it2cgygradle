package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

/**
 * @ClassName:RoleAndPowerPO
 * @Description:TODO(电站与角色映射表)
 * @author chenguiyang
 * @date 2017年6月30日下午1:52:24
 */
public class RoleAndPowerPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer roleId;
    private Integer powerstationId;
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
	public Integer getPowerstationId() {
		return powerstationId;
	}
	public void setPowerstationId(Integer powerstationId) {
		this.powerstationId = powerstationId;
	}


    
    
}
