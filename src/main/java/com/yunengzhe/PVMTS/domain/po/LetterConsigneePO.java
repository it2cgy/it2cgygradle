package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class LetterConsigneePO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private Integer letterId;
    private Integer type;
    private java.util.Date readTime;
    private Integer disabled;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String topic;
    private java.util.Date sendTime;
    private Integer senderUid;
    private String senderName;
    
    
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public java.util.Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getSenderUid() {
		return senderUid;
	}
	public void setSenderUid(Integer senderUid) {
		this.senderUid = senderUid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLetterId() {
		return letterId;
	}
	public void setLetterId(Integer letterId) {
		this.letterId = letterId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public java.util.Date getReadTime() {
		return readTime;
	}
	public void setReadTime(java.util.Date readTime) {
		this.readTime = readTime;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
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
