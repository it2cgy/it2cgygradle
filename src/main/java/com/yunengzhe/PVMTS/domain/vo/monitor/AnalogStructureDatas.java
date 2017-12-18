package com.yunengzhe.PVMTS.domain.vo.monitor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalogStructureDatas {
	 private List<AnalogStructureData> data = null;

	public List<AnalogStructureData> getData() {
		return data;
	}

	public void setData(List<AnalogStructureData> data) {
		this.data = data;
	}
	 
}
