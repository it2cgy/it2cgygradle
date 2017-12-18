package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class LetterPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String topic;
    private String content;
    private java.util.Date sendTime;
    private Integer senderUid;
    private String consigneeUid;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String senderName;
    private String consigneeName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getConsigneeUid() {
		return consigneeUid;
	}
	public void setConsigneeUid(String consigneeUid) {
		this.consigneeUid = consigneeUid;
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
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
