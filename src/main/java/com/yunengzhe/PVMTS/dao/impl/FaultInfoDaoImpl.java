package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yunengzhe.PVMTS.dao.FaultInfoDao;
import com.yunengzhe.PVMTS.domain.po.FaultInfoPO;
import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.common.tool.scaffold.util.PageBean;

@Repository("faultInfoDaoImpl")
public class FaultInfoDaoImpl extends BaseDaoImpl<FaultInfoPO> implements FaultInfoDao {

	@Override
	public int getFaultProcess(Integer powerStationId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".getFaultProcess",powerStationId);
	}

	@Override
	public int getFaultSum(Integer powerStationId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".getFaultSum",powerStationId);
	}

	@Override
	public List<FaultInfoPO> getFaultListBySearch(String search) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".getFaultListBySearch",search);
	}

	@Override
	public List<FaultInfoPO> selectHistoryList(String search) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".selectHistoryList",search);
	}
}
