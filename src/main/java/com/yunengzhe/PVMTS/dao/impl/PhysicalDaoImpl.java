package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.PhysicalDao;
import com.yunengzhe.PVMTS.domain.po.PhysicalPO;

@Repository("physicalDaoImpl")
public class PhysicalDaoImpl extends BaseDaoImpl<PhysicalPO> implements PhysicalDao {

	@Override
	public List<PhysicalPO> getPhysicalList(String orderNum) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".getPhysicalList",orderNum);
	}

}
