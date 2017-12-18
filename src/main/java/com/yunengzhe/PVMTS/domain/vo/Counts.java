package com.yunengzhe.PVMTS.domain.vo;

public class Counts {

	private int alarmSum;
	private int faultSum;
	private int faultProcess;
	public int getAlarmSum() {
		return alarmSum;
	}
	public void setAlarmSum(int alarmSum) {
		this.alarmSum = alarmSum;
	}
	public int getFaultSum() {
		return faultSum;
	}
	public void setFaultSum(int faultSum) {
		this.faultSum = faultSum;
	}
	public int getFaultProcess() {
		return faultProcess;
	}
	public void setFaultProcess(int faultProcess) {
		this.faultProcess = faultProcess;
	}
	public Counts(int alarmSum, int faultSum, int faultProcess) {
		super();
		this.alarmSum = alarmSum;
		this.faultSum = faultSum;
		this.faultProcess = faultProcess;
	}
	@Override
	public String toString() {
		return "Counts [alarmSum=" + alarmSum + ", faultSum=" + faultSum
				+ ", faultProcess=" + faultProcess + "]";
	}
	public Counts() {
		super();
	}
	
}
