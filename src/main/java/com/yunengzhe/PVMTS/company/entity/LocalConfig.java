/** 
 * 项目:PVMTS 
 * 文件名:MTPackagerecord.java 
 * 包名:com.yunengzhe.PVMTS.company.entity 
 * 创建日期:2017年3月23日下午3:10:07 
 */ 
package com.yunengzhe.PVMTS.company.entity;

import java.util.List;

public class LocalConfig {
private Integer id;
private String endTime;
private String type;
private Integer accountCount;
private String  number;
private Integer systype;
private String accountType;  
private String remarks;
private String producttype;
private String ip;
private String mac;
private String url;
private String companyName;
private String startRow;
private String endRow;
private List<LocalConfig> list;
private String total;
private String createTime;
private Boolean flag;

public Boolean getFlag() {
	return flag;
}
public void setFlag(Boolean flag) {
	this.flag = flag;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getTotal() {
	return total;
}
public void setTotal(String total) {
	this.total = total;
}
public String getStartRow() {
	return startRow;
}
public void setStartRow(String startRow) {
	this.startRow = startRow;
}
public String getEndRow() {
	return endRow;
}
public void setEndRow(String endRow) {
	this.endRow = endRow;
}
public List<LocalConfig> getList() {
	return list;
}
public void setList(List<LocalConfig> list) {
	this.list = list;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public Integer getSystype() {
	return systype;
}
public void setSystype(Integer systype) {
	this.systype = systype;
}
public String getAccountType() {
	return accountType;
}
public void setAccountType(String accountType) {
	this.accountType = accountType;
}
public String getProducttype() {
	return producttype;
}
public void setProducttype(String producttype) {
	this.producttype = producttype;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public String getMac() {
	return mac;
}
public void setMac(String mac) {
	this.mac = mac;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
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
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public Integer getAccountCount() {
	return accountCount;
}
public void setAccountCount(Integer accountCount) {
	this.accountCount = accountCount;
}
}
