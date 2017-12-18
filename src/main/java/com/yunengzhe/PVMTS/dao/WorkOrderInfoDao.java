package com.yunengzhe.PVMTS.dao;

import java.util.List;
import java.util.Map;

import com.yunengzhe.PVMTS.domain.po.WorkOrderInfoPO;
import com.yunengzhe.common.base.dao.BaseDao;

public interface WorkOrderInfoDao extends BaseDao<WorkOrderInfoPO>{
	
	/**
	 * @Title:findByTime
	 * @Description:TODO(查询输入的时间段内该用户的所有已完成工单) 
	 * @param @param param
	 * @param @return
	 * @return List<WorkOrderInfoPO>
	 * @throws
	 */
	int findByTime(Map<String,Object> param);

}

