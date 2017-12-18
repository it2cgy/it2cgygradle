package com.yunengzhe.PVMTS.domain.dto;

public class AddCurvePointsDTO {
    private Integer analoginputId;
    private String name;
    private String colorCode;
    private Integer yaxisType;
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
    
}
