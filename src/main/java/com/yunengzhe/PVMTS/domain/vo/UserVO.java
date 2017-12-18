package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;

public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private String username;	//用户名称
    private Integer sex;		//用户性别
    private String nickname;	//用户昵称
    private String headshot;		//用户手机
    private String telephone;	//固定电话
    private String email;		//email
    private java.util.Date createTime;//创建时间
    private java.util.Date updateTime;//更新时间
    private Integer status;		//状态	 禁用 启用?
    private Integer companyId;	//公司id
    private String departmentName;//部门
//    private Integer roleId;
//    private String  roleName;
    private List<UserAndRolePO> roleList;
    private HashMap<String,RolesmenuHandleVO> menuList;  
    private List<Integer> powerStationList;
    private String token;//用来请求用户信息
    private Integer userid;
    /**
     * 微信关联
     */
    private String wxnickname;
    private String wxheadimgurl;
    private String wxopenId;
    private String wxsex;
    private String wxunionId;
    private Integer createUserid;
    private String inputCompany;
    
    
    
    
	public Integer getCreateUserid() {
		return createUserid;
	}
	public void setCreateUserid(Integer createUserid) {
		this.createUserid = createUserid;
	}
	public String getInputCompany() {
		return inputCompany;
	}
	public void setInputCompany(String inputCompany) {
		this.inputCompany = inputCompany;
	}
	public HashMap<String, RolesmenuHandleVO> getMenuList() {
		return menuList;
	}
	public void setMenuList(HashMap<String, RolesmenuHandleVO> menuList) {
		this.menuList = menuList;
	}
	public List<Integer> getPowerStationList() {
		return powerStationList;
	}
	public void setPowerStationList(List<Integer> powerStationList) {
		this.powerStationList = powerStationList;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public List<UserAndRolePO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<UserAndRolePO> roleList) {
		this.roleList = roleList;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getWxnickname() {
		return wxnickname;
	}
	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}
	public String getWxheadimgurl() {
		return wxheadimgurl;
	}
	public void setWxheadimgurl(String wxheadimgurl) {
		this.wxheadimgurl = wxheadimgurl;
	}
	public String getWxopenId() {
		return wxopenId;
	}
	public void setWxopenId(String wxopenId) {
		this.wxopenId = wxopenId;
	}
	public String getWxsex() {
		return wxsex;
	}
	public void setWxsex(String wxsex) {
		this.wxsex = wxsex;
	}
	public String getWxunionId() {
		return wxunionId;
	}
	public void setWxunionId(String wxunionId) {
		this.wxunionId = wxunionId;
	}
	
	
}

