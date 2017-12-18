package com.yunengzhe.PVMTS.domain.vo.monitor;

import java.util.List;

public class MonitorHistoryPointData {
	private int analoginputId;
	private List<MonitorHistoryData> historyDatas;
	 
	public List<MonitorHistoryData> getHistoryDatas() {
		return historyDatas;
	}
	public void setHistoryDatas(List<MonitorHistoryData> historyDatas) {
		this.historyDatas = historyDatas;
	}
	public int getAnaloginputId() {
		return analoginputId;
	}
	public void setAnaloginputId(int analoginputId) {
		this.analoginputId = analoginputId;
	}
	@Override
	public String toString() {
		return "MonitorHistoryPointData [analoginputId=" + analoginputId
				+ ", historyDatas=" + historyDatas + "]";
	}
	
	
}
