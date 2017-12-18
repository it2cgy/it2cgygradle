package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class RulesInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer companyId;
    private String name;
    private String version;
    private Integer publisherId;
    private java.util.Date publishTime;
    private String fileName;
    private String fileUrl;
    private Integer publishStatus;
    private String remarks;
    private Integer ruleStatus;
    private java.util.Date createTime;
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	public java.util.Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Integer getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getRuleStatus() {
		return ruleStatus;
	}
	public void setRuleStatus(Integer ruleStatus) {
		this.ruleStatus = ruleStatus;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private java.util.Date updateTime;


}
