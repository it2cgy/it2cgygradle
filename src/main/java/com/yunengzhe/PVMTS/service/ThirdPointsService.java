package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.ThirdPointsDao;
import com.yunengzhe.PVMTS.domain.po.ThirdPointsPO;


@Service("thirdPointsService")
public class ThirdPointsService {

	private static final Logger logger = LoggerFactory.getLogger(ThirdPointsService.class);
	
	@Autowired
	private ThirdPointsDao thirdPointsDaoImpl;

	public List<ThirdPointsPO> findThirdPoint() {
		return thirdPointsDaoImpl.findAll();
	}
	 
}
