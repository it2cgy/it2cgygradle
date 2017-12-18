package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class MeasurementtypePO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String aliasname;
    private String description;
    private String name;
    private String pathname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAliasname() {
		return aliasname;
	}
	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPathname() {
		return pathname;
	}
	public void setPathname(String pathname) {
		this.pathname = pathname;
	}


}
