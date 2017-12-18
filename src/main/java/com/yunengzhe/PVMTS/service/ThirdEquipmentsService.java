package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.ThirdEquipmentsDao;
import com.yunengzhe.PVMTS.domain.po.ThirdEquipmentsPO;


@Service("thirdEquipmentsService")
public class ThirdEquipmentsService {

	private static final Logger logger = LoggerFactory.getLogger(ThirdEquipmentsService.class);
	
	@Autowired
	private ThirdEquipmentsDao thirdEquipmentsDaoImpl;

	public List<ThirdEquipmentsPO> findThirdEquipments() {
		return thirdEquipmentsDaoImpl.findAll();
	}
	 
}
