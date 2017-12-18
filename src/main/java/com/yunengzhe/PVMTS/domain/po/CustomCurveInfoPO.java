package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class CustomCurveInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer powerStationId;
    private String name;
    private Integer timeSpan;
    private Integer curveType;
    private String firstYaxisName;
    private String secondYaxisName;
    private java.util.Date createTime;
    private java.util.Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPowerStationId() {
		return powerStationId;
	}
	public void setPowerStationId(Integer powerStationId) {
		this.powerStationId = powerStationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTimeSpan() {
		return timeSpan;
	}
	public void setTimeSpan(Integer timeSpan) {
		this.timeSpan = timeSpan;
	}
	public Integer getCurveType() {
		return curveType;
	}
	public void setCurveType(Integer curveType) {
		this.curveType = curveType;
	}
	public String getFirstYaxisName() {
		return firstYaxisName;
	}
	public void setFirstYaxisName(String firstYaxisName) {
		this.firstYaxisName = firstYaxisName;
	}
	public String getSecondYaxisName() {
		return secondYaxisName;
	}
	public void setSecondYaxisName(String secondYaxisName) {
		this.secondYaxisName = secondYaxisName;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
