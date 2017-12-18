package com.yunengzhe.PVMTS.domain.vo;
/**
 * 根据电站信息返回电站报警数量
 * @author ynz1
 *
 */
public class AlarmCountVO {

	private int powerStationId;
	private int powerAlarmCount;
	public int getPowerStationId() {
		return powerStationId;
	}
	public void setPowerStationId(int powerStationId) {
		this.powerStationId = powerStationId;
	}
	public int getPowerAlarmCount() {
		return powerAlarmCount;
	}
	public void setPowerAlarmCount(int powerAlarmCount) {
		this.powerAlarmCount = powerAlarmCount;
	}
	public AlarmCountVO(int powerStationId, int powerAlarmCount) {
		super();
		this.powerStationId = powerStationId;
		this.powerAlarmCount = powerAlarmCount;
	}
	public AlarmCountVO() {
		super();
	}
	@Override
	public String toString() {
		return "AlarmCountVO [powerStationId=" + powerStationId
				+ ", powerAlarmCount=" + powerAlarmCount + "]";
	}
	
}
