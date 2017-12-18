package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
/**
 * @ClassName:UserTrackPO
 * @Description:TODO(用户轨迹)
 * @author chenguiyang
 * @date 2017年6月16日上午10:14:38
 */
public class UserTrackPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private String lng;
    private String lat;
    private java.util.Date time;
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
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
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
