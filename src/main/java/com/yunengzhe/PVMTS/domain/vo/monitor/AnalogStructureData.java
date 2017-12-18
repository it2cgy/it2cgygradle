package com.yunengzhe.PVMTS.domain.vo.monitor;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalogStructureData {
	private int avaiabletag;
	private int qualitycode;
	private long time;
	private long mtime;
	private String measuerName=null; //测量名称
	public float value;	//测量值
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
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getMtime() {
		return mtime;
	}
	public void setMtime(long mtime) {
		this.mtime = mtime;
	}
	public int getQualitycode() {
		return qualitycode;
	}
	public void setQualitycode(int qualitycode) {
		this.qualitycode = qualitycode;
	}
	public int getAvaiabletag() {
		return avaiabletag;
	}
	public void setAvaiabletag(int avaiabletag) {
		this.avaiabletag = avaiabletag;
	} 
	
	
}
