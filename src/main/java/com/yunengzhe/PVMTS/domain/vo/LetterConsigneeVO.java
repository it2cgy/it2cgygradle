package com.yunengzhe.PVMTS.domain.vo;

import java.util.Date;

public class LetterConsigneeVO {
    private Integer senderUid;
    private String senderName;
    private Integer letterId;
    private Integer type;
    private String topic;
    private Date sendTime;
    
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public Integer getSenderUid() {
		return senderUid;
	}
	public void setSenderUid(Integer senderUid) {
		this.senderUid = senderUid;
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
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
}
