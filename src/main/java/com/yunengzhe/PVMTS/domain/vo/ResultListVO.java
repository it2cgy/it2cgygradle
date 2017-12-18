package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

@SuppressWarnings("hiding")
public class ResultListVO<T> {
	int counts;
	List<T> resultList;
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	@Override
	public String toString() {
		return "ResultListVO [counts=" + counts + ", resultList=" + resultList
				+ "]";
	}
	
	
}
