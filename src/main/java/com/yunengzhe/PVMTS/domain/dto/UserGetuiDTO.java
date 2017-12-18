package com.yunengzhe.PVMTS.domain.dto;

/**
 * @ClassName:UserGetuiDTO
 * @Description:TODO(个推用户请求参数dto)
 * @author chenguiyang
 * @date 2017年7月3日下午3:07:54
 */
public class UserGetuiDTO {
	private String clientId;//设备id
	private String clientType;//设备类型 androd ios
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
}
