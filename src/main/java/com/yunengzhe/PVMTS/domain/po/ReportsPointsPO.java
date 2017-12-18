package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class ReportsPointsPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;//主键id
    private Integer analoginputId;//测点id
    private String analoginputColor;//测点颜色
    private String analoginputName;//测点名称
    private java.util.Date createTime;//创建时间
    private java.util.Date updateTime;//更新时间
    private Integer reportid;//报表id
    private String pointEnglishName;
   	public String getPointEnglishName() {
   		return pointEnglishName;
   	}
   	public void setPointEnglishName(String pointEnglishName) {
   		this.pointEnglishName = pointEnglishName;
   	}
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnaloginputId() {
		return analoginputId;
	}
	public void setAnaloginputId(Integer analoginputId) {
		this.analoginputId = analoginputId;
	}
	public String getAnaloginputColor() {
		return analoginputColor;
	}
	public void setAnaloginputColor(String analoginputColor) {
		this.analoginputColor = analoginputColor;
	}
	public String getAnaloginputName() {
		return analoginputName;
	}
	public void setAnaloginputName(String analoginputName) {
		this.analoginputName = analoginputName;
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
	public Integer getReportid() {
		return reportid;
	}
	public void setReportid(Integer reportid) {
		this.reportid = reportid;
	}


    
    
}
