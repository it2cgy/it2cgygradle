package com.yunengzhe.PVMTS.domain.vo.role;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.RoleAndPowerPO;

/**
 * @ClassName:RoleVO
 * @Description:TODO(用户角色vo)
 * @author chenguiyang
 * @date 2017年8月2日上午11:31:05
 */
public class RoleVO {
	
    private Integer id;
    private String roleName;
    private Integer roleType;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String remarks;
    private Integer companyId;
    private List<RoleAndPowerPO> roleAndPowers;
    
	public List<RoleAndPowerPO> getRoleAndPowers() {
		return roleAndPowers;
	}
	public void setRoleAndPowers(List<RoleAndPowerPO> roleAndPowers) {
		this.roleAndPowers = roleAndPowers;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleType() {
		return roleType;
	}
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
    
    
    

}
