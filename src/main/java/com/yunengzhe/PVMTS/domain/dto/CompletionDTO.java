package com.yunengzhe.PVMTS.domain.dto;


/**
 * @ClassName:CompletionDTO
 * @Description:TODO(七天完成工作量)
 * @author chenguiyang
 * @date 2017年6月7日上午10:23:53
 */
public class CompletionDTO {
	
	private String startDate;//开始时间
	private String endDate;//结束时间 yyyy-MM-dd
	private int userId;//用户id
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	
}
