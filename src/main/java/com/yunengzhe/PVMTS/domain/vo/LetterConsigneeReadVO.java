package com.yunengzhe.PVMTS.domain.vo;

import java.util.Date;
/**
 * 收件箱信件查看
 * @author dell
 *
 */
public class LetterConsigneeReadVO {
    private int senderUid;
    private String topic;
    private Date sendTime;
    private String content;
    private String senderName;
    private String userName;
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSenderUid() {
		return senderUid;
	}
	public void setSenderUid(int senderUid) {
		this.senderUid = senderUid;
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
