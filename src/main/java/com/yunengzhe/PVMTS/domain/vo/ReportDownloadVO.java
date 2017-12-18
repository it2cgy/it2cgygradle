package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryPointData;

public class ReportDownloadVO {

	private String name;
	private String time;
	private String value;
	private String equipmentName;
	private String equipmentType;
	private String curveImage;//曲线图
	private String columeImage;//直方图
	private String reportname;
	
	
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCurveImage() {
		return curveImage;
	}
	public void setCurveImage(String curveImage) {
		this.curveImage = curveImage;
	}
	public String getColumeImage() {
		return columeImage;
	}
	public void setColumeImage(String columeImage) {
		this.columeImage = columeImage;
	}
	
	
}
