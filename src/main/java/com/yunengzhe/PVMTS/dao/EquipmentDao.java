package com.yunengzhe.PVMTS.dao;

import java.util.List;
import java.util.Map;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.domain.po.EquipmentPO;

public interface EquipmentDao extends BaseDao<EquipmentPO>{

	List<EquipmentPO> findBySourse(Map<String, Object> map);

	int getCount(Map<String, Object> map);

	List<EquipmentPO> checkEquipmentNumber(Map<String, Object> map);

}

