package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

/**
 * @ClassName:UserGetuiPO
 * @Description:TODO(个推设置)
 * @author chenguiyang
 * @date 2017年7月3日下午2:05:07
 */
public class UserGetuiPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;//用户id
    private String clientId;//设备id
    private String clientType;//设备类型  androd 或 ios
    private java.util.Date createTime;//创建时间
    private java.util.Date updateTime;//更新时间
    
    
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
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
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
