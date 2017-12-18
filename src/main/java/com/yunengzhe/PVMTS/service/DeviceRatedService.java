package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.DeviceRatedDao;
import com.yunengzhe.PVMTS.domain.po.DeviceRatedPO;


@Service("deviceRatedService")
public class DeviceRatedService {

	private static final Logger logger = LoggerFactory.getLogger(DeviceRatedService.class);
	
	@Autowired
	private DeviceRatedDao deviceRatedDaoImpl;
	
	public DeviceRatedPO findByequepment(Integer id,Integer type){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("equipmentId", id);
		map.put("equipmentType", type);
		return deviceRatedDaoImpl.findUniqueByMap(map);
	}

	/**
	 * <p>List<DeviceRatedPO></p>
	 * <p>Description:获取额定功率列表</p>
	 * @author ynz
	 */
	public List<DeviceRatedPO> getAllRateList(){
		List<DeviceRatedPO> result = deviceRatedDaoImpl.findAll();
		return result;
	}

	
}
