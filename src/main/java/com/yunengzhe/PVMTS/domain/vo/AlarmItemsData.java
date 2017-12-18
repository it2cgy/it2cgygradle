package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude; 

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlarmItemsData {
	  private List<AlarmItemData> result;

	public List<AlarmItemData> getResult() {
		return result;
	}

	public void setResult(List<AlarmItemData> result) {
		this.result = result;
	}
		
	
}
