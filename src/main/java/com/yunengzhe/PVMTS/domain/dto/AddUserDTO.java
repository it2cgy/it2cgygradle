package com.yunengzhe.PVMTS.domain.dto;


/**
 * @ClassName:AddUserDTO
 * @Description:TODO(添加运维公司用户dto)
 * @author chenguiyang
 * @date 2017年6月14日上午10:45:13
 */
public class AddUserDTO {
	private Integer userId;
	private String headshot ;//用户头像
	private String username;//用户昵称
	private int companyId;//用户所属公司
	private String roleId;//用户分配角色
	private String realname;
	private String email;
	private String inputCompany;
	private String telephone;
	private Integer createUserId;
	
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getInputCompany() {
		return inputCompany;
	}
	public void setInputCompany(String inputCompany) {
		this.inputCompany = inputCompany;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getHeadshot() {
		return headshot;
	}
	public void setHeadshot(String headshot) {
		this.headshot = headshot;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
}
