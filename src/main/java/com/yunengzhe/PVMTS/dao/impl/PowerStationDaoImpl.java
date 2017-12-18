package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.PowerStationDao;
import com.yunengzhe.PVMTS.domain.po.PowerStationPO;
import com.yunengzhe.PVMTS.domain.vo.EquipmentTypeVO;

@Repository("powerStationDaoImpl")
public class PowerStationDaoImpl extends BaseDaoImpl<PowerStationPO> implements PowerStationDao {

	@Override
	public List getEquipmentNum(Map<String,Object> param) {
		return getSqlSession().selectList(sqlMapNamespace + ".getEquipmentNum",param);
	}
	public List<EquipmentTypeVO> getEquipmentsList(Integer powerStationId){
		return getSqlSession().selectList(sqlMapNamespace + ".getEquipmentsList",powerStationId);
	}
}
