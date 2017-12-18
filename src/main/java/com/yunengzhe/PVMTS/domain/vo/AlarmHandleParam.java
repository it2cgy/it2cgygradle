package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

public class AlarmHandleParam {
	private long alarmid;//报警id
	private String username; //操作人姓名
	public long getAlarmid() {
		return alarmid;
	}
	public void setAlarmid(long alarmid) {
		this.alarmid = alarmid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
