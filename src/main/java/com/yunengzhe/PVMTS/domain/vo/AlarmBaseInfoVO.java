package com.yunengzhe.PVMTS.domain.vo;

import com.yunengzhe.PVMTS.domain.po.AlarmInfoPO;

public class AlarmBaseInfoVO {
	private AlarmInfoPO aInfo;
	private PowerStationListVO pwInfo;
	public AlarmInfoPO getaInfo() {
		return aInfo;
	}
	public void setaInfo(AlarmInfoPO aInfo) {
		this.aInfo = aInfo;
	}
	public PowerStationListVO getPwInfo() {
		return pwInfo;
	}
	public void setPwInfo(PowerStationListVO pwInfo) {
		this.pwInfo = pwInfo;
	}
	
}
