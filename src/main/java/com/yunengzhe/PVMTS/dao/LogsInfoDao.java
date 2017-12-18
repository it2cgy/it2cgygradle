package com.yunengzhe.PVMTS.dao;

import java.util.List;
import java.util.Map;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.domain.po.LogsInfoPO;

public interface LogsInfoDao extends BaseDao<LogsInfoPO>{

	List<LogsInfoPO> selectLogsSearch(Map<String, Object> map);

	int selectLogsSearchCount(Map<String, Object> map);

	List<LogsInfoPO> selectThirdLogsSearch(Map<String, Object> map);
	int selectThirdLogsSearchCount(Map<String, Object> map);
}

