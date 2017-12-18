package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;

public class CustomCurvePointInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer curveId;
    private Integer analoginputId;
    private String name;
    private Integer colorId;
    private Integer yaxisId;
    private Integer lineTypeId;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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


}
