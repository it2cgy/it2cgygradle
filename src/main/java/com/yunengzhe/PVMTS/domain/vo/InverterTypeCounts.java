package com.yunengzhe.PVMTS.domain.vo;
/**
 * 某电站下逆变器总数和停机、通讯异常状态个数。
 * @author dell
 *
 */
public class InverterTypeCounts {
	private int abNormalstop;
	private int run;
	private int other;
	/**
	 * 停机数
	 */
	private int normalstop;
	/**
	 * 通讯异常个数
	 */
	private int exception;
	/**
	 * 逆变器总数
	 */
	private int total;
	/**
	 * 逆变器总数
	 */
	private int faultOperation;
	
	public int getAbNormalstop() {
		return abNormalstop;
	}
	public void setAbNormalstop(int abNormalstop) {
		this.abNormalstop = abNormalstop;
	}
	public int getRun() {
		return run;
	}
	public void setRun(int run) {
		this.run = run;
	}
	public int getOther() {
		return other;
	}
	public void setOther(int other) {
		this.other = other;
	}
	public int getNormalstop() {
		return normalstop;
	}
	public void setNormalstop(int normalstop) {
		this.normalstop = normalstop;
	}
	public int getFaultOperation() {
		return faultOperation;
	}
	public void setFaultOperation(int faultOperation) {
		this.faultOperation = faultOperation;
	}
	public int getException() {
		return exception;
	}
	public void setException(int exception) {
		this.exception = exception;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
