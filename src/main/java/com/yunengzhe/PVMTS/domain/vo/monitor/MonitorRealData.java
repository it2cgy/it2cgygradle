package com.yunengzhe.PVMTS.domain.vo.monitor;

public class MonitorRealData {
	private int pointid;
	//秒
	private  long time;
	//数值
	private double value;
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getPointid() {
		return pointid;
	}
	public void setPointid(int pointid) {
		this.pointid = pointid;
	}
	
	
}
