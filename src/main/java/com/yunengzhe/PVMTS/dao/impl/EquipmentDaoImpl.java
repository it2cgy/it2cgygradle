package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.EquipmentDao;
import com.yunengzhe.PVMTS.domain.po.EquipmentPO;

@Repository("equipmentDaoImpl")
public class EquipmentDaoImpl extends BaseDaoImpl<EquipmentPO> implements EquipmentDao {

	@Override
	public List<EquipmentPO> findBySourse(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".findBySourse",map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".getCount",map);
	}

	@Override
	public List<EquipmentPO> checkEquipmentNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".checkEquipmentNumber",map);
	}

}
 