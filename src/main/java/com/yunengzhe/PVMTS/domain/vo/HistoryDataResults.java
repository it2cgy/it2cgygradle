package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

import com.yunengzhe.PVMTS.domain.vo.HistoryDataResult;

public class HistoryDataResults {
	private int total;
	private List<HistoryDataResult> result;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<HistoryDataResult> getResult() {
		return result;
	}
	public void setResult(List<HistoryDataResult> result) {
		this.result = result;
	}
	
	
}
