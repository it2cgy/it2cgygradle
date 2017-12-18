package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class CustomCurvePointInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer curveId;
    private Integer analoginputId;
    private String name;
    private Integer colorId;
    private Integer yaxisId;
    private Integer lineTypeId;
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
	public Integer getColorId() {
		return colorId;
	}
	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}
	public Integer getYaxisId() {
		return yaxisId;
	}
	public void setYaxisId(Integer yaxisId) {
		this.yaxisId = yaxisId;
	}
	public Integer getLineTypeId() {
		return lineTypeId;
	}
	public void setLineTypeId(Integer lineTypeId) {
		this.lineTypeId = lineTypeId;
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
