package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;
import java.util.Map;

import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.vo.EquipmentInfoVO;

public interface PointInfoDao extends BaseDao<PointInfoPO>{
	
	List<PointInfoPO> getPowerStationEquioments(Integer powerStationId);
	List<EquipmentInfoVO> getEquioments(Map<String, Object> map);
}

