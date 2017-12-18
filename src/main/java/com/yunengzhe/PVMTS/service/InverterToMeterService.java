package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.InverterToMeterDao;
import com.yunengzhe.PVMTS.domain.po.InverterToMeterPO;
import com.yunengzhe.PVMTS.domain.vo.longi.InverterToMeterVO;


@Service("inverterToMeterService")
public class InverterToMeterService {

	private static final Logger logger = LoggerFactory.getLogger(InverterToMeterService.class);
	
	@Autowired
	private InverterToMeterDao inverterToMeterDaoImpl;
	/**
	 * 获取指定逆变器下电表
	 * @param id
	 * @return 
	 */
	public List<InverterToMeterVO> getMeters(Integer id) {
		return inverterToMeterDaoImpl.getAmmeterInfo(id);
	}
	 
}
