package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;

public class RolesmenuHandleVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer have_menu;
	private Integer handle_premission;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHave_menu() {
		return have_menu;
	}
	public void setHave_menu(Integer have_menu) {
		this.have_menu = have_menu;
	}
	public Integer getHandle_premission() {
		return handle_premission;
	}
	public void setHandle_premission(Integer handle_premission) {
		this.handle_premission = handle_premission;
	}


}
