package com.yunengzhe.PVMTS.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.ScanInfoDao;
import com.yunengzhe.PVMTS.domain.dto.ScanInfoDTO;
import com.yunengzhe.PVMTS.domain.po.ScanInfoPO;


@Service("scanInfoService")
public class ScanInfoService {

	private static final Logger logger = LoggerFactory.getLogger(ScanInfoService.class);
	
	@Autowired
	private ScanInfoDao scanInfoDaoImpl;
	
	
	
	/**
	 * @Title:uploadInfo
	 * @Description:TODO(上传扫码信息) 
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int uploadInfo(ScanInfoDTO param){
		ScanInfoPO info = new ScanInfoPO();
		info.setEquipmentId(param.getEquipmentId());
		info.setEquipmentType(param.getEquipmentType());
		info.setEquipmentInfo(param.getEquipmentInfo());
		info.setCreateTime(new Date());
		info.setUpdateTime(new Date());
		int flag = 0;
		try {
			flag = scanInfoDaoImpl.insert(info);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return flag;
	}
	
	
	/**
	 * @Title:downLoadInfo
	 * @Description:TODO(下载设备信息) 
	 * @param @return
	 * @return ScanInfoPO
	 * @throws
	 */
	public ScanInfoPO downLoadInfo(int equipmentId){
		ScanInfoPO info = scanInfoDaoImpl.findUniqueBy("equipmentId", equipmentId);
		return info;
	}
	
	 
}
