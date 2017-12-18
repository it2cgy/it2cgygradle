package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class LetterSenderPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private Integer letterId;
    private Integer disabled;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String topic;
    private java.util.Date sendTime;
    private String consigneeUid;
    private String consigneeName;
    
   	public String getConsigneeName() {
   		return consigneeName;
   	}
   	public void setConsigneeName(String consigneeName) {
   		this.consigneeName = consigneeName;
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
	public String getConsigneeUid() {
		return consigneeUid;
	}
	public void setConsigneeUid(String consigneeUid) {
		this.consigneeUid = consigneeUid;
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
