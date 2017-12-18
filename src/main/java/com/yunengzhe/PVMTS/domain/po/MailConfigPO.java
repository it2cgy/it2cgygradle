package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class MailConfigPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String serverPath;
    private String mailType;
    private String mailPort;
    private Integer pushTime;
    private String mailUsername;
    private String mailPassword;
    private String mailAddress;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    
    private Integer usessl;
    
    
	public Integer getUsessl() {
		return usessl;
	}
	public void setUsessl(Integer usessl) {
		this.usessl = usessl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServerPath() {
		return serverPath;
	}
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	public String getMailType() {
		return mailType;
	}
	public void setMailType(String mailType) {
		this.mailType = mailType;
	}
	public String getMailPort() {
		return mailPort;
	}
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}
	public Integer getPushTime() {
		return pushTime;
	}
	public void setPushTime(Integer pushTime) {
		this.pushTime = pushTime;
	}
	public String getMailUsername() {
		return mailUsername;
	}
	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
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
