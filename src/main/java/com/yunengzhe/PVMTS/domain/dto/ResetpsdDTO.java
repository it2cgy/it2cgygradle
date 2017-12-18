package com.yunengzhe.PVMTS.domain.dto;

/**
 * @ClassName:ResetpsdDTO
 * @Description:TODO(描述)
 * @author chenguiyang
 * @date 2017年6月28日上午10:49:29
 */
public class ResetpsdDTO {

		private String oldPassword;//旧密码
		private String newPassword;//新密码
		
		public String getOldPassword() {
			return oldPassword;
		}
		public void setOldPassword(String oldPassword) {
			this.oldPassword = oldPassword;
		}
		public String getNewPassword() {
			return newPassword;
		}
		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
	
		
	
	
}
