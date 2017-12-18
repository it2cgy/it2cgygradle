package com.yunengzhe.PVMTS.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.SpecificationDao;
import com.yunengzhe.PVMTS.domain.po.SpecificationPO;


@Service("specificationService")
public class SpecificationService {

	private static final Logger logger = LoggerFactory.getLogger(SpecificationService.class);
	
	@Autowired
	private SpecificationDao specificationDaoImpl;
	 
}
