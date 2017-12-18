package com.yunengzhe.PVMTS.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.MeasurementtypeDao;
import com.yunengzhe.PVMTS.domain.po.MeasurementtypePO;


@Service("measurementtypeService")
public class MeasurementtypeService {

	private static final Logger logger = LoggerFactory.getLogger(MeasurementtypeService.class);
	
	@Autowired
	private MeasurementtypeDao measurementtypeDaoImpl;

	public MeasurementtypePO get(String measurementtypeId) {
		// TODO Auto-generated method stub
		return measurementtypeDaoImpl.get(measurementtypeId);
	}
	 
}
