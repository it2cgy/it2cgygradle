package com.yunengzhe.PVMTS.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalogStructureData {
	private String measuerName=null; //测量名称
	private float value;	//测量值
	public String getMeasuerName() {
		return measuerName;
	}
	public void setMeasuerName(String measuerName) {
		this.measuerName = measuerName;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	} 
}
