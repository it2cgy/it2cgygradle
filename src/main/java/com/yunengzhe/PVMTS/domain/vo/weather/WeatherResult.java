package com.yunengzhe.PVMTS.domain.vo.weather;

import java.util.List;

public class WeatherResult {
	private String currentCity;
	private String pm25;
	private List<IndexInfo> index;
	private List<DataInfo> weather_data;
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	public List<IndexInfo> getIndex() {
		return index;
	}
	public void setIndex(List<IndexInfo> index) {
		this.index = index;
	}
	public List<DataInfo> getWeather_data() {
		return weather_data;
	}
	public void setWeather_data(List<DataInfo> weather_data) {
		this.weather_data = weather_data;
	}
	
}
