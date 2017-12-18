package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

public class CurveDetailVO {
	private Integer id;
    private Integer powerStationId;
    private String name;
    private Integer timeSpan;
    private Integer type;
    private String firstYaxisName;
    private String secondYaxisName;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String nickname;
    private String updateuser;
    
    
    private List<CurvePointDetailVO> pointList;
    
    
    
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
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
	public List<CurvePointDetailVO> getPointList() {
		return pointList;
	}
	public void setPointList(List<CurvePointDetailVO> pointList) {
		this.pointList = pointList;
	}
    }
