package com.yunengzhe.PVMTS.domain.dto;

import java.util.List;

public class RatesDTO {
	
	private Integer tabId;
	private List<RateDTO> rates;

	
	public Integer getTabId() {
		return tabId;
	}


	public void setTabId(Integer tabId) {
		this.tabId = tabId;
	}


	public List<RateDTO> getRates() {
		return rates;
	}


	public void setRates(List<RateDTO> rates) {
		this.rates = rates;
	}


	public static class RateDTO {
		private Integer id;
		private String value;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}
