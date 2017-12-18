package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.RolesMenusDao;
import com.yunengzhe.PVMTS.domain.po.RolesMenusPO;

@Repository("rolesMenusDaoImpl")
public class RolesMenusDaoImpl extends BaseDaoImpl<RolesMenusPO> implements RolesMenusDao {

	@Override
	public String findRoleMenuList(int roleId) {
		return getSqlSession().selectOne(sqlMapNamespace + ".findRoleMenuList",roleId);
	}

	@Override
	public List<RolesMenusPO> getRoleMenuList(Map<String,Object> param) {
		return getSqlSession().selectList(sqlMapNamespace + ".getRoleMenuList",param);
	}

}
