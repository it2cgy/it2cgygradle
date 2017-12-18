package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.ReportsPointsPO;

public interface ReportsPointsDao extends BaseDao<ReportsPointsPO>{

	/**
	 * @Title:getPointsList
	 * @Description:TODO(获取报表下的所有测点信息 根据id) 
	 * @param @param ids
	 * @param @return
	 * @return List<ReportsPointsPO>
	 * @throws
	 */
	public List<ReportsPointsPO> getPointsList(String ids);
}

