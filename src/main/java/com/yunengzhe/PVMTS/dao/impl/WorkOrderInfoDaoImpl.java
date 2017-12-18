package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.WorkOrderInfoDao;
import com.yunengzhe.PVMTS.domain.po.WorkOrderInfoPO;

@Repository("workOrderInfoDaoImpl")
public class WorkOrderInfoDaoImpl extends BaseDaoImpl<WorkOrderInfoPO> implements WorkOrderInfoDao {

	/**
	 * 查询时间段内的该用户已完成的工单数量
	 */
	@Override
	public int findByTime(Map<String, Object> param) {
		return getSqlSession()
				.selectOne(sqlMapNamespace + ".findByTime",param);
	}

}
