package com.yunengzhe.PVMTS.domain.vo;

public class SaveReportDailyVO {

	private Integer reportId;
	private Integer powerId;
	private String powerName;
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Integer getPowerId() {
		return powerId;
	}
	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}
	public String getPowerName() {
		return powerName;
	}
	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}
	@Override
	public String toString() {
		return "SaveReportDailyVO [reportId=" + reportId + ", powerId="
				+ powerId + ", powerName=" + powerName + "]";
	}
	
	
}
