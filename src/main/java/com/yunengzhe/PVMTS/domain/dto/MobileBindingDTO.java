package com.yunengzhe.PVMTS.domain.dto;

/**
 * @ClassName:MobileBindingDTO
 * @Description:TODO(检测用户微信账号是否与手机号绑定)
 * @author chenguiyang
 * @date 2017年6月14日下午4:03:18
 */
public class MobileBindingDTO {
	private String wxopenId;
	private String wxunionId;
	
	public String getWxopenId() {
		return wxopenId;
	}
	public void setWxopenId(String wxopenId) {
		this.wxopenId = wxopenId;
	}
	public String getWxunionId() {
		return wxunionId;
	}
	public void setWxunionId(String wxunionId) {
		this.wxunionId = wxunionId;
	}
	
	

}
