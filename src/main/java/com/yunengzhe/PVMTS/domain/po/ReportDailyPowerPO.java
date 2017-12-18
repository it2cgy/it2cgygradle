package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class ReportDailyPowerPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer powerId;
    private Integer iscreate;
    private java.util.Date createtime;
    private java.util.Date updatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPowerId() {
		return powerId;
	}
	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}
	public Integer getIscreate() {
		return iscreate;
	}
	public void setIscreate(Integer iscreate) {
		this.iscreate = iscreate;
	}
	public java.util.Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	public java.util.Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}


}
