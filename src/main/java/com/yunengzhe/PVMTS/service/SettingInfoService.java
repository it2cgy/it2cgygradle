package com.yunengzhe.PVMTS.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.SettingInfoDao;
import com.yunengzhe.PVMTS.domain.po.SettingInfoPO;


@Service("settingInfoService")
public class SettingInfoService {

	private static final Logger logger = LoggerFactory.getLogger(SettingInfoService.class);
	
	@Autowired
	private SettingInfoDao settingInfoDaoImpl;

	public SettingInfoPO getSetting(int userId) {
		SettingInfoPO findUniqueBy = settingInfoDaoImpl.findUniqueBy("userId", userId);
		if(findUniqueBy==null){
			findUniqueBy = new SettingInfoPO(0, 1, 1, 1, 1, 0);
		}
		logger.debug(findUniqueBy.toString());
		return findUniqueBy;
	}

	public boolean insert(SettingInfoPO setting) {
		// TODO Auto-generated method stub
		return settingInfoDaoImpl.insert(setting)==1;
	}

	public boolean updateSetting(SettingInfoPO setting) {
		// TODO Auto-generated method stub
		return settingInfoDaoImpl.updateSetting(setting)==1;
	}
	 
}
