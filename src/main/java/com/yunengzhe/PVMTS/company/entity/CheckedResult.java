/** 
 * 项目:PVMTS 
 * 文件名:CheckedResult.java 
 * 包名:com.yunengzhe.PVMTS.company.entity 
 * 创建日期:2017年5月4日下午1:48:18 
 */ 
package com.yunengzhe.PVMTS.company.entity;

import java.util.List;

/**
 * 网络校验结果实体类
 * 作者： 刘超帅  
 * 修改日期：2017年5月4日下午1:50:45 
 * 描述：
 * 版本：1.0
 */
public class CheckedResult {
	int id;
	int configId;
	String startTime;
	String remarks;
	int type;
	String stopTime;
	int start;
	int end;
	int total;
	String configIdstr;
	String typestr;
	List<CheckedResult> list;
	
	public String getConfigIdstr() {
		return configIdstr;
	}
	public void setConfigIdstr(String configIdstr) {
		this.configIdstr = configIdstr;
	}
	public String getTypestr() {
		return typestr;
	}
	public void setTypestr(String typestr) {
		this.typestr = typestr;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public List<CheckedResult> getList() {
		return list;
	}
	public void setList(List<CheckedResult> list) {
		this.list = list;
	}
	public int getConfigId() {
		return configId;
	}
	public void setConfigId(int configId) {
		this.configId = configId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
