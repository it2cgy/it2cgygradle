package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class CurvePointInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer curveId;
    private Integer analoginputId;
    private String name;
    private String colorCode;
    private Integer yaxisType;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCurveId() {
		return curveId;
	}
	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}
	public Integer getAnaloginputId() {
		return analoginputId;
	}
	public void setAnaloginputId(Integer analoginputId) {
		this.analoginputId = analoginputId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public Integer getYaxisType() {
		return yaxisType;
	}
	public void setYaxisType(Integer yaxisType) {
		this.yaxisType = yaxisType;
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
