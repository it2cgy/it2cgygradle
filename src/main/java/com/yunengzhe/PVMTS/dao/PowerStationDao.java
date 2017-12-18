package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;
import java.util.Map;

import com.yunengzhe.PVMTS.domain.po.PowerStationPO;
import com.yunengzhe.PVMTS.domain.vo.EquipmentTypeVO;

public interface PowerStationDao extends BaseDao<PowerStationPO>{

	List getEquipmentNum(Map<String,Object> param);

	List<EquipmentTypeVO> getEquipmentsList(Integer powerStationId);
}

