package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class UserAndPowerPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer powerstationId;
    private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPowerstationId() {
		return powerstationId;
	}
	public void setPowerstationId(Integer powerstationId) {
		this.powerstationId = powerstationId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserAndPowerPO [id=" + id + ", powerstationId="
				+ powerstationId + ", userId=" + userId + "]";
	}


}
