package com.yunengzhe.PVMTS.domain.dto.historyAndReport;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunengzhe.PVMTS.domain.po.ReportsPointsPO;
import com.yunengzhe.PVMTS.domain.vo.CurvePointDetailVO;

public class ReportsDTO {

	private String reportname;//报表名称
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+08:00")
	private Date startTime;//开始时间
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+08:00")
	private Date endTime;//截止时间
	private Integer powerstationId;//电站id
	private Integer userId;
	private String curveImage;//曲线图
	private String columeImage;//直方图
	private String analoginputIds; 
	private long minutesSpan;//两个测点之间的测量间隔单位为分钟
	private int haveReal;
	private List<CurvePointDetailVO> curvePoint;
	private List<ReportsPointsPO> points;
	
	private String reportData;
	
	
	
	

	public String getReportData() {
		return reportData;
	}

	public void setReportData(String reportData) {
		this.reportData = reportData;
	}

	public List<CurvePointDetailVO> getCurvePoint() {
		return curvePoint;
	}

	public void setCurvePoint(List<CurvePointDetailVO> curvePoint) {
		this.curvePoint = curvePoint;
	}

	public String getAnaloginputIds() {
		return analoginputIds;
	}

	public void setAnaloginputIds(String analoginputIds) {
		this.analoginputIds = analoginputIds;
	}

	public long getMinutesSpan() {
		return minutesSpan;
	}

	public void setMinutesSpan(long minutesSpan) {
		this.minutesSpan = minutesSpan;
	}

	public int getHaveReal() {
		return haveReal;
	}

	public void setHaveReal(int haveReal) {
		this.haveReal = haveReal;
	}


	public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPowerstationId() {
		return powerstationId;
	}

	public void setPowerstationId(Integer powerstationId) {
		this.powerstationId = powerstationId;
	}

	public List<ReportsPointsPO> getPoints() {
		return points;
	}

	public void setPoints(List<ReportsPointsPO> points) {
		this.points = points;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
