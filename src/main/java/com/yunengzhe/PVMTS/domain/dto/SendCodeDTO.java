package com.yunengzhe.PVMTS.domain.dto;

/**
 * @ClassName:SendCodeDTO
 * @Description:TODO(发送短信验证码dto)
 * @author chenguiyang
 * @date 2017年6月28日上午10:31:48
 */
public class SendCodeDTO {
	
	private String phone;
	private String imageCode;
	private String imageTag;

	public String getImageTag() {
		return imageTag;
	}

	public void setImageTag(String imageTag) {
		this.imageTag = imageTag;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	

}
