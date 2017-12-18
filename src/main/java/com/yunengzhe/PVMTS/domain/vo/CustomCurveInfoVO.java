package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
import java.util.List;

public class CustomCurveInfoVO implements Serializable {
    private Integer id;
    private Integer powerStationId;
    private String name;
    private Integer timeSpan;
    private Integer curveType;
    private String firstYaxisName;
    private String secondYaxisName;
    private List<CustomCurvePointInfoVO> curvePoint;
	public List<CustomCurvePointInfoVO> getCurvePoint() {
		return curvePoint;
	}
	public void setCurvePoint(List<CustomCurvePointInfoVO> curvePoint) {
		this.curvePoint = curvePoint;
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


}
