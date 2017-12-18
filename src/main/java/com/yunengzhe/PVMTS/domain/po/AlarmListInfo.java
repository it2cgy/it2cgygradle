package com.yunengzhe.PVMTS.domain.po;

import java.util.List;

public class AlarmListInfo {

	private List<AlarmInfoPO> findByMap;
	private int count;
	public List<AlarmInfoPO> getFindByMap() {
		return findByMap;
	}
	public void setFindByMap(List<AlarmInfoPO> findByMap) {
		this.findByMap = findByMap;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public AlarmListInfo(List<AlarmInfoPO> findByMap, int count) {
		super();
		this.findByMap = findByMap;
		this.count = count;
	}
	public AlarmListInfo() {
		super();
	}
	@Override
	public String toString() {
		return "AlarmListInfo [findByMap=" + findByMap + ", count=" + count
				+ "]";
	}
	
}
