package com.yunengzhe.PVMTS.domain.vo;

/**
 * @ClassName:MaintenanceUserVO
 * @Description:TODO(生成工单加载运维人员返回参数实体类)
 * @author chenguiyang
 * @date 2017年6月13日下午2:54:51
 */
public class MaintenanceUserVO {

	private int userId;//用户id
	private String username;//用户名称
	private int isFree;//是否空闲
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIsFree() {
		return isFree;
	}
	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}
	
	
	
}
