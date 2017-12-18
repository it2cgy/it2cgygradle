package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class InverterToMeterPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer inverterId;
    private Integer electricMeterId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInverterId() {
		return inverterId;
	}
	public void setInverterId(Integer inverterId) {
		this.inverterId = inverterId;
	}
	public Integer getElectricMeterId() {
		return electricMeterId;
	}
	public void setElectricMeterId(Integer electricMeterId) {
		this.electricMeterId = electricMeterId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
