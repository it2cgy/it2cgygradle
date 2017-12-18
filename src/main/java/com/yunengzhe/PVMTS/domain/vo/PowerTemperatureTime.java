package com.yunengzhe.PVMTS.domain.vo;

import java.util.Date;

public class PowerTemperatureTime {
private double power;//组件功率
private double temperature;//组件温度
private Date time;//时间
public double getPower() {
	return power;
}
public void setPower(double power) {
	this.power = power;
}
public double getTemperature() {
	return temperature;
}
public void setTemperature(double temperature) {
	this.temperature = temperature;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
}
