package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.VersionInfoDao;
import com.yunengzhe.PVMTS.domain.po.VersionInfoPO;


@Service("versionInfoService")
public class VersionInfoService {

	private static final Logger logger = LoggerFactory.getLogger(VersionInfoService.class);
	
	@Autowired
	private VersionInfoDao versionInfoDaoImpl;

	public Integer checkVersion(VersionInfoPO versionInfo) {
		VersionInfoPO findUniqueByMap = this.findUniqueByMap(versionInfo.getVersionType());
		if(findUniqueByMap != null){
			if(findUniqueByMap.getVersionNumber().equals(versionInfo.getVersionNumber())){
				return 0;
			}
			return 1;
		}
		return null;
	}
	public VersionInfoPO findUniqueByMap(String type) {
		Map<String,Object> map = new HashMap<String,Object>();
		//获取检查版本类型的最新版本
		map.put("versionType", type);
		map.put("versionStatus", "0");
		VersionInfoPO findUniqueByMap = versionInfoDaoImpl.findUniqueByMap(map);
		return findUniqueByMap;
	}
}
