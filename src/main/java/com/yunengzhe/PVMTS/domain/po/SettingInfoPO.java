package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class SettingInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer system;
    private Integer letter;
    private Integer twoAlarm;
    private Integer threeAlarm;
    private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSystem() {
		return system;
	}
	public void setSystem(Integer system) {
		this.system = system;
	}
	public Integer getLetter() {
		return letter;
	}
	public void setLetter(Integer letter) {
		this.letter = letter;
	}
	public Integer getTwoAlarm() {
		return twoAlarm;
	}
	public void setTwoAlarm(Integer twoAlarm) {
		this.twoAlarm = twoAlarm;
	}
	public Integer getThreeAlarm() {
		return threeAlarm;
	}
	public void setThreeAlarm(Integer threeAlarm) {
		this.threeAlarm = threeAlarm;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public SettingInfoPO(Integer id, Integer system, Integer letter,
			Integer twoAlarm, Integer threeAlarm, Integer userId) {
		super();
		this.id = id;
		this.system = system;
		this.letter = letter;
		this.twoAlarm = twoAlarm;
		this.threeAlarm = threeAlarm;
		this.userId = userId;
	}
	public SettingInfoPO() {
		super();
	}
	@Override
	public String toString() {
		return "SettingInfoPO [id=" + id + ", system=" + system + ", letter="
				+ letter + ", twoAlarm=" + twoAlarm + ", threeAlarm="
				+ threeAlarm + ", userId=" + userId + "]";
	}


}
