package com.yunengzhe.PVMTS.domain.vo;


/**
 * @ClassName:UserAndRoleVO
 * @Description:TODO(返回role列表与选中的role)
 * @author chenguiyang
 * @date 2017年6月21日下午8:02:40
 */
public class UserAndRoleVO {
    private Integer id;
    private String roleName;
    private Boolean selected;
    
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
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	

	


}
