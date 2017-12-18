package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class VersionInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String versionType;
    private String versionNumber;
    private Integer versionStatus;
    private String dowloadUrl;
    private String versionMessage;
    private java.util.Date craeteTime;
    private java.util.Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVersionType() {
		return versionType;
	}
	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}
	public String getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	public Integer getVersionStatus() {
		return versionStatus;
	}
	public void setVersionStatus(Integer versionStatus) {
		this.versionStatus = versionStatus;
	}
	public String getDowloadUrl() {
		return dowloadUrl;
	}
	public void setDowloadUrl(String dowloadUrl) {
		this.dowloadUrl = dowloadUrl;
	}
	public String getVersionMessage() {
		return versionMessage;
	}
	public void setVersionMessage(String versionMessage) {
		this.versionMessage = versionMessage;
	}
	public java.util.Date getCraeteTime() {
		return craeteTime;
	}
	public void setCraeteTime(java.util.Date craeteTime) {
		this.craeteTime = craeteTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "VersionInfoPO [id=" + id + ", versionType=" + versionType
				+ ", versionNumber=" + versionNumber + ", versionStatus="
				+ versionStatus + ", dowloadUrl=" + dowloadUrl
				+ ", versionMessage=" + versionMessage + ", craeteTime="
				+ craeteTime + ", updateTime=" + updateTime + "]";
	}

    

}
