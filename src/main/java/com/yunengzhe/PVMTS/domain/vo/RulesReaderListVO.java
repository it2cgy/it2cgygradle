package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

public class RulesReaderListVO {
    private List<RulesReaderVO> rows;
    private Integer total;
    
	public List<RulesReaderVO> getRows() {
		return rows;
	}
	public void setRows(List<RulesReaderVO> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
