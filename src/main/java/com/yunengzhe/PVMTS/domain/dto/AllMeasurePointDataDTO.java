package com.yunengzhe.PVMTS.domain.dto;

public class AllMeasurePointDataDTO {
	private String powerStationId;//电站id
	private String measurePoint;//筛选条件（测点名称）
	private String checkedPoint;//已选测点
	private Integer page;
	private Integer pagesize;
	public String getPowerStationId() {
		return powerStationId;
	}
	public void setPowerStationId(String powerStationId) {
		this.powerStationId = powerStationId;
	}
	public String getMeasurePoint() {
		return measurePoint;
	}
	public void setMeasurePoint(String measurePoint) {
		this.measurePoint = measurePoint;
	}
	public String getCheckedPoint() {
		return checkedPoint;
	}
	public void setCheckedPoint(String checkedPoint) {
		this.checkedPoint = checkedPoint;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	
}
