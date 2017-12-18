package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.MeasurementBoxPO;
import com.yunengzhe.PVMTS.domain.vo.longi.MeasurementBoxAndInverterVO;

public interface MeasurementBoxDao extends BaseDao<MeasurementBoxPO>{

	public List<MeasurementBoxAndInverterVO> findMeasureInverter(String ids);

}

