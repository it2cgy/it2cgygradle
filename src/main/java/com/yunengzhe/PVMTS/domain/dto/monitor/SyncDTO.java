package com.yunengzhe.PVMTS.domain.dto.monitor;

import java.util.Date;
 
import com.fasterxml.jackson.annotation.JsonFormat;

public class SyncDTO { 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+08:00")
	private Date startTime; 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+08:00")
	private Date endTime;
	 
	private String name;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	 
}
