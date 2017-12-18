package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

public class AnalogStructureParam {
	/**
	 * 获取量的名字的集合，注意此名字要严格和实时库中的名字保持一致  ;
	 */
	private String measuerNames;
	/**
	 * 所属容器id
	 */
	private int equipcontainerID; 
	/**
	 * 所属容器表id 
	 */
	private int equipcontainerTableID;
	public String getMeasuerNames() {
		return measuerNames;
	}
	public void setMeasuerNames(String measuerNames) {
		this.measuerNames = measuerNames;
	}
	public int getEquipcontainerID() {
		return equipcontainerID;
	}
	public void setEquipcontainerID(int equipcontainerID) {
		this.equipcontainerID = equipcontainerID;
	}
	public int getEquipcontainerTableID() {
		return equipcontainerTableID;
	}
	public void setEquipcontainerTableID(int equipcontainerTableID) {
		this.equipcontainerTableID = equipcontainerTableID;
	}
 
	
}
