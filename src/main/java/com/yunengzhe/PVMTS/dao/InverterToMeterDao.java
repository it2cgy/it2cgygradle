package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.InverterToMeterPO;
import com.yunengzhe.PVMTS.domain.vo.longi.InverterToMeterVO;

public interface InverterToMeterDao extends BaseDao<InverterToMeterPO>{

	public List<InverterToMeterVO> getAmmeterInfo(Integer id);

}

