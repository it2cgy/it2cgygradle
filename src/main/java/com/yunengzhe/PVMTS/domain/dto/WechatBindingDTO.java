package com.yunengzhe.PVMTS.domain.dto;


/**
 * @ClassName:WechatBindingDTO
 * @Description:TODO(微信与普通账号绑定DTO)
 * @author chenguiyang
 * @date 2017年6月14日下午1:52:37
 */
public class WechatBindingDTO {

	private String username;//用户名称
	private String password;//用户密码
	private String wxheadurl;//微信头像地址
	private String wxnickname;//微信昵称
	private String wxopenId;//微信提供
	private String wxsex;//性别
	private String wxunionId;//微信提供
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWxheadurl() {
		return wxheadurl;
	}
	public void setWxheadurl(String wxheadurl) {
		this.wxheadurl = wxheadurl;
	}
	public String getWxnickname() {
		return wxnickname;
	}
	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}
	public String getWxopenId() {
		return wxopenId;
	}
	public void setWxopenId(String wxopenId) {
		this.wxopenId = wxopenId;
	}
	public String getWxsex() {
		return wxsex;
	}
	public void setWxsex(String wxsex) {
		this.wxsex = wxsex;
	}
	public String getWxunionId() {
		return wxunionId;
	}
	public void setWxunionId(String wxunionId) {
		this.wxunionId = wxunionId;
	}
	
	
}
