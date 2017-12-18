package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class MeasurementBoxPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer framid;
    private Integer inverterId;
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
	public Integer getFramid() {
		return framid;
	}
	public void setFramid(Integer framid) {
		this.framid = framid;
	}
	public Integer getInverterId() {
		return inverterId;
	}
	public void setInverterId(Integer inverterId) {
		this.inverterId = inverterId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
