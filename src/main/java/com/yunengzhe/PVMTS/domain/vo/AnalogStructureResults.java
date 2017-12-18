package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalogStructureResults {
	private List<AnalogStructureData> result;

	public List<AnalogStructureData> getResult() {
		return result;
	}

	public void setResult(List<AnalogStructureData> result) {
		this.result = result;
	}

}
