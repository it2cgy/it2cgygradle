package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;
import java.util.Map;

import com.yunengzhe.PVMTS.domain.po.RolesMenusPO;

public interface RolesMenusDao extends BaseDao<RolesMenusPO>{

	String findRoleMenuList(int roleId);
	
	List<RolesMenusPO> getRoleMenuList(Map<String,Object> param);
}

