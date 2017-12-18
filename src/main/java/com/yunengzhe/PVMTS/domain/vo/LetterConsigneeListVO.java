package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
/**
 * 收件箱
 * @author dell
 *
 */
public class LetterConsigneeListVO {
	private List<LetterConsigneeVO> rows;
    private Integer total;
	public List<LetterConsigneeVO> getRows() {
		return rows;
	}
	public void setRows(List<LetterConsigneeVO> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
