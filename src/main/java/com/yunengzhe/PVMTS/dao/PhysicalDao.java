package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.PhysicalPO;

public interface PhysicalDao extends BaseDao<PhysicalPO>{
	
	List<PhysicalPO>  getPhysicalList(String orderNum);
	
	
}

