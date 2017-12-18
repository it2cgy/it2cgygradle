package com.yunengzhe.PVMTS.domain.dto;

import java.util.List;

public class AddCurveDTO {
    private Integer powerStationId;
    private String name;
    private Integer timeSpan;
    private Integer type;
    private String firstYaxisName;
    private String secondYaxisName;
    private List<AddCurvePointsDTO> pointData;
    
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public List<AddCurvePointsDTO> getPointData() {
		return pointData;
	}
	public void setPointData(List<AddCurvePointsDTO> pointData) {
		this.pointData = pointData;
	}
    
}
