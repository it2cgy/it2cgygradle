package com.yunengzhe.PVMTS.domain.dto;

public class LetterDTO {
	private String consigneeIds;
	private String letterBody;
	private String topic;
	public String getConsigneeIds() {
		return consigneeIds;
	}
	public void setConsigneeIds(String consigneeIds) {
		this.consigneeIds = consigneeIds;
	}
	public String getLetterBody() {
		return letterBody;
	}
	public void setLetterBody(String letterBody) {
		this.letterBody = letterBody;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
}
