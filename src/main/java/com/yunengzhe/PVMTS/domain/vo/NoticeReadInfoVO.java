package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class NoticeReadInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private String name;
    private Date publishTime;
    private String contentHtml;
   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getContentHtml() {
		return contentHtml;
	}
	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
