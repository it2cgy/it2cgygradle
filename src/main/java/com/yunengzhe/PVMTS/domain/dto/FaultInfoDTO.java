package com.yunengzhe.PVMTS.domain.dto;

import java.io.Serializable;

/**
 * @ClassName:FaultInfoDTO
 * @Description:TODO(生成工单时 故障反馈)
 * @author chenguiyang
 * @date 2017年6月6日下午6:56:35
 */
public class FaultInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int faultId;

	public int getFaultId() {
		return faultId;
	}

	public void setFaultId(int faultId) {
		this.faultId = faultId;
	}
	
	
	
	
	
}
