package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class IvEquipmentPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer farmid;
    private String equipnum;
    private String model;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFarmid() {
		return farmid;
	}
	public void setFarmid(Integer farmid) {
		this.farmid = farmid;
	}
	public String getEquipnum() {
		return equipnum;
	}
	public void setEquipnum(String equipnum) {
		this.equipnum = equipnum;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}


}
