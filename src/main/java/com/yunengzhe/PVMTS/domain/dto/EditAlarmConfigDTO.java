package com.yunengzhe.PVMTS.domain.dto;



public class EditAlarmConfigDTO {
	private Integer id;  
	private Integer pointid;  
	private Double valueOne;
	private Double valueTwo;
	private Double valueThree;
	private Integer type;
	private String message;
	private Integer userid;
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Integer getPointid() {
		return pointid;
	}
	public void setPointid(Integer pointid) {
		this.pointid = pointid;
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
