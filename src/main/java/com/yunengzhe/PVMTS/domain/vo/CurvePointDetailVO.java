package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

public class CurvePointDetailVO {
    private Integer analoginputId;
    private String equipmentName;
    private String equipmentNumber;
    private String equipmentNameEn;
    private String equipmentNumberEn;
    private String pointName;
    private String pointEnglishName;
    private Integer yaxisType;
    private String colorCode;
	
	public String getEquipmentNameEn() {
		return equipmentNameEn;
	}
	public void setEquipmentNameEn(String equipmentNameEn) {
		this.equipmentNameEn = equipmentNameEn;
	}
	public String getEquipmentNumberEn() {
		return equipmentNumberEn;
	}
	public void setEquipmentNumberEn(String equipmentNumberEn) {
		this.equipmentNumberEn = equipmentNumberEn;
	}
	public String getPointEnglishName() {
		return pointEnglishName;
	}
	public void setPointEnglishName(String pointEnglishName) {
		this.pointEnglishName = pointEnglishName;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentNumber() {
		return equipmentNumber;
	}
	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	public Integer getAnaloginputId() {
		return analoginputId;
	}
	public void setAnaloginputId(Integer analoginputId) {
		this.analoginputId = analoginputId;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public Integer getYaxisType() {
		return yaxisType;
	}
	public void setYaxisType(Integer yaxisType) {
		this.yaxisType = yaxisType;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
    
}
