package com.yunengzhe.PVMTS.domain.dto;

/**
 * @ClassName:UserTrackDTO
 * @Description:TODO(用户轨迹更新处理)
 * @author chenguiyang
 * @date 2017年6月16日下午1:51:51
 */
public class UserTrackDTO {

	private int userId;
	private String lng;
	private String lat;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
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
	
	
}
