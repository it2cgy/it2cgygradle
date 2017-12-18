package com.yunengzhe.PVMTS.domain.dto.alarm;

import java.util.List;

public class AddAlarmConfigDTO {
	private List<Integer> pointids;  
	private Double valueOne;
	private Double valueTwo;
	private Double valueThree;
	private Integer type;
	private String message;
	private Integer userid;
	
	
	 
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public List<Integer> getPointids() {
		return pointids;
	}
	public void setPointids(List<Integer> pointids) {
		this.pointids = pointids;
	}
	public Double getValueOne() {
		return valueOne;
	}
	public void setValueOne(Double valueOne) {
		this.valueOne = valueOne;
	}
	public Double getValueTwo() {
		return valueTwo;
	}
	public void setValueTwo(Double valueTwo) {
		this.valueTwo = valueTwo;
	}
	public Double getValueThree() {
		return valueThree;
	}
	public void setValueThree(Double valueThree) {
		this.valueThree = valueThree;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
	
	
}
