package com.yunengzhe.PVMTS.domain.dto.role;

import java.util.List;


/**
 * @ClassName:AddRoleDTO
 * @Description:TODO(新增角色dto)
 * @author chenguiyang
 * @date 2017年8月2日上午11:03:42
 */
public class RoleDTO {
	
	private Integer roleId;//角色id
	private String roleName;//角色名称
	private String powerList;//电站列表
	private Integer pushId;
	private boolean pushConfig; //推送配置
	private List<MenuConfig> menuConfig;
	
	
	
	
	public Integer getPushId() {
		return pushId;
	}
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}
	public boolean isPushConfig() {
		return pushConfig;
	}
	public void setPushConfig(boolean pushConfig) {
		this.pushConfig = pushConfig;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getPowerList() {
		return powerList;
	}
	public void setPowerList(String powerList) {
		this.powerList = powerList;
	}

	public List<MenuConfig> getMenuConfig() {
		return menuConfig;
	}
	public void setMenuConfig(List<MenuConfig> menuConfig) {
		this.menuConfig = menuConfig;
	}


	/**
	 * 静态内部类
	 * 故障列表
	 */
	public static class MenuConfig{
		private int id;//故障id
		private boolean premis;//故障处理方法
		private boolean handle;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public boolean isPremis() {
			return premis;
		}
		public void setPremis(boolean premis) {
			this.premis = premis;
		}
		public boolean isHandle() {
			return handle;
		}
		public void setHandle(boolean handle) {
			this.handle = handle;
		}
		
		
	}
}
