package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
import java.util.Date;

public class MessageSystemInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private String content;
    private Integer messageType;
    private Integer messageId;
    private Integer massageStatus;
    private String addressUser;
    private Integer forcedPush;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Integer userId;
    private Integer isread;
    private Integer alarmgrade;
    
	public Integer getAlarmgrade() {
		return alarmgrade;
	}
	public void setAlarmgrade(Integer alarmgrade) {
		this.alarmgrade = alarmgrade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getMassageStatus() {
		return massageStatus;
	}
	public void setMassageStatus(Integer massageStatus) {
		this.massageStatus = massageStatus;
	}
	public String getAddressUser() {
		return addressUser;
	}
	public void setAddressUser(String addressUser) {
		this.addressUser = addressUser;
	}
	public Integer getForcedPush() {
		return forcedPush;
	}
	public void setForcedPush(Integer forcedPush) {
		this.forcedPush = forcedPush;
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
	public MessageSystemInfoPO() {
		super();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIsread() {
		return isread;
	}
	public void setIsread(Integer isread) {
		this.isread = isread;
	}
	public MessageSystemInfoPO(Integer id, String title, String content,
			Integer messageType, Integer messageId, Integer massageStatus,
			String addressUser, Integer forcedPush, Date createTime,
			Date updateTime, MessageUserInfoPO messageSystemUser) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.messageType = messageType;
		this.messageId = messageId;
		this.massageStatus = massageStatus;
		this.addressUser = addressUser;
		this.forcedPush = forcedPush;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "MessageSystemInfoPO [id=" + id + ", title=" + title
				+ ", content=" + content + ", messageType=" + messageType
				+ ", messageId=" + messageId + ", massageStatus="
				+ massageStatus + ", addressUser=" + addressUser
				+ ", forcedPush=" + forcedPush + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", ";
	}
	
 
}
