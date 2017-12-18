package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.ThirduserDao;
import com.yunengzhe.PVMTS.domain.po.ThirduserPO;


@Service("thirduserService")
public class ThirduserService {

	private static final Logger logger = LoggerFactory.getLogger(ThirduserService.class);
	
	@Autowired
	private ThirduserDao thirduserDaoImpl;

	public List<ThirduserPO> getMeasurementTypeId(Integer userid,
			String equipmentcontainerId,String equipmentcontainerTableid) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userid);
		map.put("equipmentId", equipmentcontainerId);
		map.put("equipmentType", equipmentcontainerTableid);
		return thirduserDaoImpl.findByMap(map);
	}
	 
}
