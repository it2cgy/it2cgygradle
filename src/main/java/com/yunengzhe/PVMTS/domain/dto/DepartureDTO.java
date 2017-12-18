package com.yunengzhe.PVMTS.domain.dto;

import java.io.Serializable;

/**
 * @ClassName:DepartureDTO
 * @Description:TODO(生成工单 离场选择)
 * @author chenguiyang
 * @date 2017年6月6日下午7:12:04
 */
public class DepartureDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int departExamineId;

	public int getDepartExamineId() {
		return departExamineId;
	}

	public void setDepartExamineId(int departExamineId) {
		this.departExamineId = departExamineId;
	}
	
	
	

}
