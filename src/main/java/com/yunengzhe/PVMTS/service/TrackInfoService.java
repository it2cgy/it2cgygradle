package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.TrackInfoDao;
import com.yunengzhe.PVMTS.domain.po.TrackInfoPO;


@Service("trackInfoService")
public class TrackInfoService {

	private static final Logger logger = LoggerFactory.getLogger(TrackInfoService.class);
	
	@Autowired
	private TrackInfoDao trackInfoDaoImpl;

	public List<TrackInfoPO> getTrackInfoList() {
		// TODO Auto-generated method stub
		return trackInfoDaoImpl.findAll();
	}
	 
}
