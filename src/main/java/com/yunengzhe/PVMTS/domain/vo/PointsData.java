package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yunengzhe.PVMTS.domain.po.PointInfoPO;

 

public class PointsData implements Serializable {
	
 
	private static final long serialVersionUID = 1L;
	private Map<String,PointInfoPO> pointInfoMap = null; //测点id和测点信息映射
	private Map<String,String>  equipPowerMap = null;//电站和设备映射
	private Map<String,String> pointMap = null; //保存 设备类型+设备id+测量类型和测点id的映射
	List<AnalogStructureParam> analogStructureList =  null;//保存 需要抓取的对象
	
	
	public Map<String, PointInfoPO> getPointInfoMap() {
		return pointInfoMap;
	}
	public void setPointInfoMap(Map<String, PointInfoPO> pointInfoMap) {
		this.pointInfoMap = pointInfoMap;
	}
	public List<AnalogStructureParam> getAnalogStructureList() {
		return analogStructureList;
	}
	public void setAnalogStructureList(List<AnalogStructureParam> analogStructureList) {
		this.analogStructureList = analogStructureList;
	}
	public Map<String, String> getPointMap() {
		return pointMap;
	}
	public void setPointMap(Map<String, String> pointMap) {
		this.pointMap = pointMap;
	}
	public Map<String, String> getEquipPowerMap() {
		return equipPowerMap;
	}
	public void setEquipPowerMap(Map<String, String> equipPowerMap) {
		this.equipPowerMap = equipPowerMap;
	}
	 
}
