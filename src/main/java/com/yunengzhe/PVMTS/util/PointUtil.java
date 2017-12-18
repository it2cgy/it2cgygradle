package com.yunengzhe.PVMTS.util;
 

import org.apache.commons.lang.StringUtils;
 
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorRealData;

public class PointUtil {
 
	
	public static String getKey(Integer equipmentcontainerTableid,Integer equipmentcontainerId){
		 
		return equipmentcontainerTableid+"_"+equipmentcontainerId;
	}
	
	public static String getKey(Integer equipmentcontainerTableid,Integer equipmentcontainerId,String measurementtypeName){
		return equipmentcontainerTableid+"_"+equipmentcontainerId+"_"+measurementtypeName;
	}
	
	public static String getKey(String pointid,long dataTime){
		String newId = addZeroBack(pointid,10)+"_"+dataTime;
		return newId;
	}
	
	

	
	public static String getCachePointValue(Integer equipmentcontainerTableid,Integer equipmentcontainerId,long time,double value){
		return equipmentcontainerTableid+"_"+equipmentcontainerId+"_"+time+"_"+value;
	}
	
	public static MonitorRealData getCachePointValue(String valueString){ 
		MonitorRealData data = new MonitorRealData();
		data.setTime(0);
		data.setValue(0); 
		
		if(StringUtils.isBlank(valueString)){
			return data;
		}
		String[] aa = valueString.split("_");
		if(aa.length<4){
			return data;
		} 

		if(StringUtils.isNumeric(aa[2]) && BigDecimalUtils.isNumber(aa[3])){
			data.setTime(1000L*Long.valueOf( aa[2]));
			data.setValue(Double.valueOf( aa[3])); 
			return data;
		}
		return data;
	}
	
	
	/**
	 * 从key获取时间 秒
	 * @param key
	 * @return
	 */
	public static long getTimeFormKey(String key){
		if(StringUtils.isBlank(key)){
			return 0;
		}
		String[] keys = key.split("_");
		if(keys.length<2){
			return 0;
		}
		String timeStr = keys[1];
		if(! StringUtils.isNumeric(timeStr)){
			return 0;
		}
		 
		return Long.valueOf(timeStr);
	}
	/**
	 * 
	 * @param old
	 * @param strLength
	 * @return
	 */
	public static String addZeroBack(String old,int strLength){
		int strLen = old.length();
		StringBuffer sb = null;
		while(strLen<strLength){
			sb = new StringBuffer();
			sb.append(0).append(old);

			old = sb.toString();
			strLen = old.length();
		}
		return old;
	}
}
