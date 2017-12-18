package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class CompanyPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String companyName;
    private String address;
    private String ownerUsername;
    private String ownerPosition;
    private String ownerMobile;
    private java.util.Date createTime;
    private String remarks;
    private java.util.Date udpateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOwnerUsername() {
		return ownerUsername;
	}
	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}
	public String getOwnerPosition() {
		return ownerPosition;
	}
	public void setOwnerPosition(String ownerPosition) {
		this.ownerPosition = ownerPosition;
	}
	public String getOwnerMobile() {
		return ownerMobile;
	}
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public java.util.Date getUdpateTime() {
		return udpateTime;
	}
	public void setUdpateTime(java.util.Date udpateTime) {
		this.udpateTime = udpateTime;
	}


}
