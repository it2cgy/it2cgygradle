package com.yunengzhe.PVMTS.domain.dto;

public class UserSearchKey {
	private String nameKey;
	private String nickKey;
	private String roleKey;
	private String createDateKey;
	
	public String getRoleKey() {
		return roleKey;
	}
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
	public String getNameKey() {
		return nameKey;
	}
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}
	public String getNickKey() {
		return nickKey;
	}
	public void setNickKey(String nickKey) {
		this.nickKey = nickKey;
	}
	public String getCreateDateKey() {
		return createDateKey;
	}
	public void setCreateDateKey(String createDateKey) {
		this.createDateKey = createDateKey;
	}
	 
	
}
