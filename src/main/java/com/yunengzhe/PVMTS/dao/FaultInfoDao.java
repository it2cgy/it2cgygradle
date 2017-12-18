package com.yunengzhe.PVMTS.dao;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.FaultInfoPO;
import com.yunengzhe.PVMTS.domain.po.WorkOrderInfoPO;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.common.tool.scaffold.util.PageBean;

public interface FaultInfoDao extends BaseDao<FaultInfoPO>{

	int getFaultProcess(Integer powerStationId);

	int getFaultSum(Integer powerStationId);

	List<FaultInfoPO> getFaultListBySearch(String search);

	List<FaultInfoPO> selectHistoryList(String search);

}

