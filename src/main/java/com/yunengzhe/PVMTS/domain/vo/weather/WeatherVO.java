package com.yunengzhe.PVMTS.domain.vo.weather;

import java.util.Date;
import java.util.List;

public class WeatherVO {
	private String error;
	private String status;
	private Date requestDate;
	private String date;
	private List<WeatherResult> results;
	
	
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<WeatherResult> getResults() {
		return results;
	}
	public void setResults(List<WeatherResult> results) {
		this.results = results;
	}
	  
}
