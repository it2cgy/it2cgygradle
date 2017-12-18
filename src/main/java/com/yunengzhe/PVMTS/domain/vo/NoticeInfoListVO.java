package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

public class NoticeInfoListVO {
    private List<NoticeReaderVO> rows;
    private Integer total;
    
	public List<NoticeReaderVO> getRows() {
		return rows;
	}
	public void setRows(List<NoticeReaderVO> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
