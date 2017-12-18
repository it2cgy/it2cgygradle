package com.yunengzhe.PVMTS.domain.vo;

import java.util.Date;
import java.util.List;

public class LetterSenderVO {
    private String consigneeUid;
    private Integer letterId;
    private String topic;
    private Date sendTime;
    private String consigneeName;
    
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getConsigneeUid() {
		return consigneeUid;
	}
	public void setConsigneeUid(String consigneeUid) {
		this.consigneeUid = consigneeUid;
	}
	public Integer getLetterId() {
		return letterId;
	}
	public void setLetterId(Integer letterId) {
		this.letterId = letterId;
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
