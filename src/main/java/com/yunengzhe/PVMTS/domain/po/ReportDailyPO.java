package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class ReportDailyPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String reportname;
    private String url;
    private Integer equipmentid;
    private Integer equipmenttableid;
    private java.util.Date reporttime;
    private java.util.Date createtime;
    private java.util.Date updatetime;
    
    
	public Integer getEquipmentid() {
		return equipmentid;
	}
	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}
	public Integer getEquipmenttableid() {
		return equipmenttableid;
	}
	public void setEquipmenttableid(Integer equipmenttableid) {
		this.equipmenttableid = equipmenttableid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public java.util.Date getReporttime() {
		return reporttime;
	}
	public void setReporttime(java.util.Date reporttime) {
		this.reporttime = reporttime;
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

	@Override
	public String toString() {
		return "ReportDailyPO [id=" + id + ", reportname=" + reportname
				+ ", url=" + url + ", equipmentid=" + equipmentid
				+ ", equipmenttableid=" + equipmenttableid + ", reporttime="
				+ reporttime + ", createtime=" + createtime + ", updatetime="
				+ updatetime + "]";
	}

}
