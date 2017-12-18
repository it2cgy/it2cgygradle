package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class UserPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;
    private Integer sex;
    private String nickname;
    private String headshot;
    private String telephone;
    private String email;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Integer status;
    private Integer companyId;
    private String wxheadurl;
    private String wxsex;
    private String wxnickname;
    private String wxopenid;
    private String wxunionid;
    private String companyname;
    private Integer createUserid;
    private String createNickname;
    private String inputCompany;
    private int isDelete;
    
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getInputCompany() {
		return inputCompany;
	}
	public void setInputCompany(String inputCompany) {
		this.inputCompany = inputCompany;
	}
 
	
	public Integer getCreateUserid() {
		return createUserid;
	}
	public void setCreateUserid(Integer createUserid) {
		this.createUserid = createUserid;
	}
	public String getCreateNickname() {
		return createNickname;
	}
	public void setCreateNickname(String createNickname) {
		this.createNickname = createNickname;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
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
	public String getWxheadurl() {
		return wxheadurl;
	}
	public void setWxheadurl(String wxheadurl) {
		this.wxheadurl = wxheadurl;
	}
	public String getWxsex() {
		return wxsex;
	}
	public void setWxsex(String wxsex) {
		this.wxsex = wxsex;
	}
	public String getWxnickname() {
		return wxnickname;
	}
	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}
	public String getWxopenid() {
		return wxopenid;
	}
	public void setWxopenid(String wxopenid) {
		this.wxopenid = wxopenid;
	}
	public String getWxunionid() {
		return wxunionid;
	}
	public void setWxunionid(String wxunionid) {
		this.wxunionid = wxunionid;
	}


}
