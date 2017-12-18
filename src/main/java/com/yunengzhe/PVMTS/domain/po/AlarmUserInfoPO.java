package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
import java.util.Date;

public class AlarmUserInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer alarmId;
    private Integer userId;
    private String remark;
    private java.util.Date createTime;
    private java.util.Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public AlarmUserInfoPO(Integer id, Integer alarmId, Integer userId,
			String remark, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.alarmId = alarmId;
		this.userId = userId;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public AlarmUserInfoPO() {
		super();
	}
	@Override
	public String toString() {
		return "AlarmUserInfoPO [id=" + id + ", alarmId=" + alarmId
				+ ", userId=" + userId + ", remark=" + remark + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}


}
