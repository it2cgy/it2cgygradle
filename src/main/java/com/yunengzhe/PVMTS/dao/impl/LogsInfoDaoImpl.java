package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.LogsInfoDao;
import com.yunengzhe.PVMTS.domain.po.LogsInfoPO;

@Repository("logsInfoDaoImpl")
public class LogsInfoDaoImpl extends BaseDaoImpl<LogsInfoPO> implements LogsInfoDao {

	@Override
	public List<LogsInfoPO> selectLogsSearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".selectLogsSearch",map);
	}

	@Override
	public int selectLogsSearchCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".selectLogsSearchCount",map);
	}
	public List<LogsInfoPO> selectThirdLogsSearch(Map<String, Object> map){
		return getSqlSession().selectList(sqlMapNamespace + ".selectThirdLogsSearch",map);
	}
	public int selectThirdLogsSearchCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".selectThirdLogsSearchCount",map);
	}
}
