package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
import java.util.List;

public class UserInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;	//用户名称
    private String password;	//用户密码
    private Integer sex;		//用户性别
    private String nickname;	//用户昵称
    private String headshot;		//用户头像
    private String telephone;	//联系方式
    private String email;		//email
    private java.util.Date createTime;//创建时间
    private java.util.Date updateTime;//更新时间
    private Integer status;		//状态	 禁用 启用?
    private Integer companyId;	//公司id
    private List<RolePO> roleList;
	
	public List<RolePO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RolePO> roleList) {
		this.roleList = roleList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadshot() {
		return headshot;
	}
	public void setHeadshot(String headshot) {
		this.headshot = headshot;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}


}
