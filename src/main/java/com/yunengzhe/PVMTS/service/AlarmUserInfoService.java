package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.AlarmUserInfoDao;
import com.yunengzhe.PVMTS.domain.po.AlarmUserInfoPO;


@Service("alarmUserInfoService")
public class AlarmUserInfoService {

	private static final Logger logger = LoggerFactory.getLogger(AlarmUserInfoService.class);
	
	@Autowired
	private AlarmUserInfoDao alarmUserInfoDaoImpl;
	 
	public boolean insert(AlarmUserInfoPO alarmUserInfoPO){
		return alarmUserInfoDaoImpl.insert(alarmUserInfoPO)==1;
	}

	public AlarmUserInfoPO checkIsRead(Integer id, Integer id2) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("alarmId", id);
		map.put("userId", id2);
		return alarmUserInfoDaoImpl.findUniqueByMap(map);
	};
}
