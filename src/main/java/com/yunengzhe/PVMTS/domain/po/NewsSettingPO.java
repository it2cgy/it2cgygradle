package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class NewsSettingPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private Integer push;
    private Integer sound;
    private Integer vibrate;
    private java.util.Date createTime;
    private java.util.Date updateTime;
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
	public Integer getPush() {
		return push;
	}
	public void setPush(Integer push) {
		this.push = push;
	}
	public Integer getSound() {
		return sound;
	}
	public void setSound(Integer sound) {
		this.sound = sound;
	}
	public Integer getVibrate() {
		return vibrate;
	}
	public void setVibrate(Integer vibrate) {
		this.vibrate = vibrate;
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


}
