package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
import java.util.Date;

public class MessageUserInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer mid;
    private Integer userId;
    private Integer isread;
    private java.util.Date createTime;
    private java.util.Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIsread() {
		return isread;
	}
	public void setIsread(Integer isread) {
		this.isread = isread;
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
	public MessageUserInfoPO(Integer id, Integer mid, Integer userId,
			Integer isread, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.mid = mid;
		this.userId = userId;
		this.isread = isread;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public MessageUserInfoPO() {
		super();
	}
	@Override
	public String toString() {
		return "MessageUserInfoPO [id=" + id + ", mid=" + mid + ", userId="
				+ userId + ", isread=" + isread + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	

}
