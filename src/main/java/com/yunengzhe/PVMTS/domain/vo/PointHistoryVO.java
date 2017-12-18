package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;

public class PointHistoryVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private java.util.Date time;
    private double value;
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
