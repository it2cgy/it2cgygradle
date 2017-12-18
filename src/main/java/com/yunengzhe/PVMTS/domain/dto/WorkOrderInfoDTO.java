package com.yunengzhe.PVMTS.domain.dto;

import java.io.Serializable;

/**
 * @ClassName:WorkOrderInfoDTO
 * @Description:TODO(工单处理参数)
 * @author chenguiyang
 * @date 2017年6月6日上午9:48:16
 */
public class WorkOrderInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId ;//用户id
	private String state  ;//工单状态 1-已完成 0-进行中
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	
	
    
    

}
