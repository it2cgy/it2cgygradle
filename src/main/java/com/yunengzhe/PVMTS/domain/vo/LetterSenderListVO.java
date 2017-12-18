package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class LetterSenderListVO {
	private List<LetterSenderVO> rows;
    private Integer total;
	public List<LetterSenderVO> getRows() {
		return rows;
	}
	public void setRows(List<LetterSenderVO> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
