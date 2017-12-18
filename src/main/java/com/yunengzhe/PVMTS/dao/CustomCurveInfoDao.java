package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;
import java.util.Map;

import com.yunengzhe.PVMTS.domain.po.CustomCurveInfoPO;
import com.yunengzhe.PVMTS.domain.vo.CustomCurveInfoVO;

public interface CustomCurveInfoDao extends BaseDao<CustomCurveInfoPO>{

	List<CustomCurveInfoVO> findCurveList(Map map);
}

