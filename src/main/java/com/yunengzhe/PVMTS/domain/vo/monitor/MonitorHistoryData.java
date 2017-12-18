package com.yunengzhe.PVMTS.domain.vo.monitor;

import java.util.ArrayList;
import java.util.List;

import com.yunengzhe.PVMTS.util.PointCacheUtil;
import com.yunengzhe.PVMTS.util.PointUtil;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.commons.util.BigDecimalUtils;
import com.yunengzhe.commons.util.hbase.HbaseResultData;

public class MonitorHistoryData {
	//秒
	private  long time;
	//数值
	private double value;
	//原始数据
	private double valueOriginal;
	
	
	public double getValueOriginal() {
		return valueOriginal;
	}
	public void setValueOriginal(double valueOriginal) {
		this.valueOriginal = valueOriginal;
	}
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
	
	
	public static MonitorHistoryData getHistoryData(HbaseResultData hbaseData){
		MonitorHistoryData hisData = new MonitorHistoryData();
		String key = hbaseData.getKey();
		String[] values = hbaseData.getCells().get(0).getValue().split("\\|");
		hisData.setTime(1000L*PointCacheUtil.getTimeFormKey(key));
		if(BigDecimalUtils.isNumber(values[0])){
			hisData.setValue(Double.valueOf(values[0]));
			hisData.setValueOriginal(Double.valueOf(values[0]));
		}
		if(values.length>1){
			hisData.setValueOriginal(Double.valueOf(values[1]));
		}
		return hisData;
	}
}
