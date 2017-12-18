package com.yunengzhe.PVMTS.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.GridarkDao;
import com.yunengzhe.PVMTS.domain.po.GridarkPO;


@Service("gridarkService")
public class GridarkService {

	private static final Logger logger = LoggerFactory.getLogger(GridarkService.class);
	
	@Autowired
	private GridarkDao gridarkDaoImpl;

	public GridarkPO getGridark(String powerStationId) {
		return gridarkDaoImpl.findUniqueBy("farmid", powerStationId);
	}
	 
}
