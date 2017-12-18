package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
import java.util.List;

import com.yunengzhe.PVMTS.domain.po.RolePO;

public class UserBaseInfoVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private String username;	//用户名称
    private String nickname;	//用户昵称
    private String telephone;		//用户手机
    private Integer userid;
    private String rolesName;
    private List<RolePO> roleList;
    
	
	
	public String getRolesName() {
		return rolesName;
	}
	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}
	public List<RolePO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RolePO> roleList) {
		this.roleList = roleList;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	
}

