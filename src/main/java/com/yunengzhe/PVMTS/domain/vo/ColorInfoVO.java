package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;

public class ColorInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String colorCode;
    private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
