package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.CurveInfoPO;
import com.yunengzhe.PVMTS.domain.vo.CurveDetailVO;

public interface CurveInfoDao extends BaseDao<CurveInfoPO>{

	List<CurveDetailVO> getCurveDetail(String ids);

}

