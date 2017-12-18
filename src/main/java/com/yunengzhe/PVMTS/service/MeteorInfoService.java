package com.yunengzhe.PVMTS.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.MeteorInfoDao;
import com.yunengzhe.PVMTS.domain.po.MeteorInfoPO;


@Service("meteorInfoService")
public class MeteorInfoService {

	private static final Logger logger = LoggerFactory.getLogger(MeteorInfoService.class);
	
	@Autowired
	private MeteorInfoDao meteorInfoDaoImpl;

	public MeteorInfoPO getMeteor(int powerStationId) {
		// TODO Auto-generated method stub
		return meteorInfoDaoImpl.findUniqueBy("farmid", powerStationId);
	}
	 
}
