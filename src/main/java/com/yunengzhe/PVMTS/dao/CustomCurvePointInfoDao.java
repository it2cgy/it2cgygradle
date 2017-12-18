package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.domain.po.CustomCurvePointInfoPO;

public interface CustomCurvePointInfoDao extends BaseDao<CustomCurvePointInfoPO>{
	public int deleteByIds(String Ids);
}

