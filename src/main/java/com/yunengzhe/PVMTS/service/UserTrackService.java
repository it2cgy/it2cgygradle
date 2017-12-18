package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.UserTrackDao;
import com.yunengzhe.PVMTS.domain.dto.UserTrackDTO;
import com.yunengzhe.PVMTS.domain.po.UserTrackPO;


@Service("userTrackService")
public class UserTrackService {

	private static final Logger logger = LoggerFactory.getLogger(UserTrackService.class);
	
	@Autowired
	private UserTrackDao userTrackDaoImpl;
	
	/**
	 * @Title:updateUserTrack
	 * @Description:TODO(更新用户轨迹) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void updateUserTrack(UserTrackDTO userTrack){
		UserTrackPO userTrackPO = new UserTrackPO();
		UserTrackPO result = userTrackDaoImpl.findUniqueBy("userId",userTrack.getUserId());
		userTrackPO.setUserId(userTrack.getUserId());
		userTrackPO.setLat(userTrack.getLat());
		userTrackPO.setLng(userTrack.getLng());
		userTrackPO.setTime(new Date());
		try {
			if(result!=null){//更新一条用户信息
				userTrackPO.setUpdateTime(new Date());
				userTrackPO.setId(result.getId());
				userTrackDaoImpl.update4Selective(userTrackPO);
			}else{//新增一条用户轨迹信息
				userTrackPO.setCreateTime(new Date());
				userTrackDaoImpl.insert(userTrackPO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}