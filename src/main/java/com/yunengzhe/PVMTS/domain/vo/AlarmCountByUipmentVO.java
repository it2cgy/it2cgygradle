package com.yunengzhe.PVMTS.domain.vo;
/**
 * 
 * @author ynz1
 *根据设备类型返回1，2，3级的报警数量
 */
public class AlarmCountByUipmentVO {
	private int alarmGrade;
	private int count;
	public int getAlarmGrade() {
		return alarmGrade;
	}
	public void setAlarmGrade(int alarmGrade) {
		this.alarmGrade = alarmGrade;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public AlarmCountByUipmentVO(int alarmGrade, int count) {
		super();
		this.alarmGrade = alarmGrade;
		this.count = count;
	}
	public AlarmCountByUipmentVO() {
		super();
	}
	@Override
	public String toString() {
		return "AlarmCountByUipmentVO [alarmGrade=" + alarmGrade + ", count="
				+ count + "]";
	}
	
}
