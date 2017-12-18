package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.ReportsPointsDao;
import com.yunengzhe.PVMTS.domain.po.ReportsPointsPO;


@Service("reportsPointsService")
public class ReportsPointsService {

	private static final Logger logger = LoggerFactory.getLogger(ReportsPointsService.class);
	
	@Autowired
	private ReportsPointsDao reportsPointsDaoImpl;
	 
	
	/**
	 * @Title:getPointsList
	 * @Description:TODO(根据报表id 查询报表下的所有测点详情) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public List<ReportsPointsPO> getPointsList(String ids){
		List<ReportsPointsPO> result = reportsPointsDaoImpl.getPointsList(ids);
		return result;
	}
}
