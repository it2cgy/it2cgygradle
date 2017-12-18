package com.yunengzhe.PVMTS.domain.vo;

import java.util.Date;
import java.util.List;

public class LetterSenderReadVO {
    private String consigneeUid;
    private String topic;
    private Date sendTime;
    private String content;
    private String consigneeName;
    
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getConsigneeUid() {
		return consigneeUid;
	}
	public void setConsigneeUid(String consigneeUid) {
		this.consigneeUid = consigneeUid;
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
