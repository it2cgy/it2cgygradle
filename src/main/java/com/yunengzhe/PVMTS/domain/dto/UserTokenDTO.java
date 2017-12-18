package com.yunengzhe.PVMTS.domain.dto;

public class UserTokenDTO {


	public static int TYPE_ANDROID = 1;
	public static int TYPE_IOS = 2;
	private Integer userId;
	private Integer push;
	private Integer sound;
	private Integer vibrate;
	private String deviceToken;
	private Integer deviceType;
	
	
	
	
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
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
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
}
