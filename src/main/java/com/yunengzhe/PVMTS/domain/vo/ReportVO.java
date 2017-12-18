package com.yunengzhe.PVMTS.domain.vo;

public class ReportVO {
    private Integer id;
    private String reportname;
    private java.util.Date starttime;
    private java.util.Date endtime;
    private java.util.Date createtime;
    private java.util.Date updatetime;
    private Integer powerstationid;
    private Integer createuser;
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
	public java.util.Date getStarttime() {
		return starttime;
	}
	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}
	public java.util.Date getEndtime() {
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
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
	public Integer getPowerstationid() {
		return powerstationid;
	}
	public void setPowerstationid(Integer powerstationid) {
		this.powerstationid = powerstationid;
	}
	public Integer getCreateuser() {
		return createuser;
	}
	public void setCreateuser(Integer createuser) {
		this.createuser = createuser;
	}
    
    
}
