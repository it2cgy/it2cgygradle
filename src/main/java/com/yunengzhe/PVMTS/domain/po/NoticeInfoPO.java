package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class NoticeInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer companyId;
    private String name;
    private Integer typeId;
    private Integer publisher;
    private java.util.Date publishTime;
    private String thumbnallUrl;
    private String content;
    private String contentHtml;
    private Integer counts;
    private Integer publishStatus;
    private Integer noticeStatus;
    private String remarks;
    private java.util.Date createTime;
    private java.util.Date updateTime;
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
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getPublisher() {
		return publisher;
	}
	public void setPublisher(Integer publisher) {
		this.publisher = publisher;
	}
	public java.util.Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getThumbnallUrl() {
		return thumbnallUrl;
	}
	public void setThumbnallUrl(String thumbnallUrl) {
		this.thumbnallUrl = thumbnallUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentHtml() {
		return contentHtml;
	}
	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public Integer getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}
	public Integer getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(Integer noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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


}
