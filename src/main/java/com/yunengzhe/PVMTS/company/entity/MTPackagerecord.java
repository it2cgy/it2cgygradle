/** 
 * 项目:PVMTS 
 * 文件名:MTPackagerecord.java 
 * 包名:com.yunengzhe.PVMTS.company.entity 
 * 创建日期:2017年3月23日下午3:10:07 
 */ 
package com.yunengzhe.PVMTS.company.entity;

public class MTPackagerecord {
private Integer id;
private Integer company_id;
private String packageStatus;
private Integer packageStatusV;
private String createTime;
private String updateTime;
private String startTime;
private String endTime;
private String type;
private Integer interval;
private Integer accountCount;
private String  stoptime;
private String  number;
//用户表添加版权类型字段
private String account_type;  
private String company_name;
private String remarks;

public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public String getCompany_name() {
	return company_name;
}
public void setCompany_name(String company_name) {
	this.company_name = company_name;
}
public Integer getPackageStatusV() {
	return packageStatusV;
}
public void setPackageStatusV(Integer packageStatusV) {
	this.packageStatusV = packageStatusV;
}
//版权类型
public String getAccount_type() {
	return account_type;
}
public void setAccount_type(String account_type) {
	this.account_type = account_type;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getPackageStatus() {
	return packageStatus;
}
public void setPackageStatus(String packageStatus) {
	this.packageStatus = packageStatus;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getCompany_id() {
	return company_id;
}
public void setCompany_id(Integer company_id) {
	this.company_id = company_id;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public Integer getInterval() {
	return interval;
}
public void setInterval(Integer interval) {
	this.interval = interval;
}
public Integer getAccountCount() {
	return accountCount;
}
public void setAccountCount(Integer accountCount) {
	this.accountCount = accountCount;
}
public String getStoptime() {
	return stoptime;
}
public void setStoptime(String stoptime) {
	this.stoptime = stoptime;
}
}
