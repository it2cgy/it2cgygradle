package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.yunengzhe.PVMTS.dao.UmengPushDao;
import com.yunengzhe.PVMTS.domain.po.UmengPushPO;


@Service("umengPushService")
public class UmengPushService {

	private static final Logger logger = LoggerFactory.getLogger(UmengPushService.class);
	
	@Autowired
	private UmengPushDao umengPushDaoImpl;
	
	/**
	 * 用户id与设备绑定
	 */
	public void bindingToken(Integer userId,String deviceToken,String deviceType){
		UmengPushPO p = new UmengPushPO();
		p.setUserId(userId);
		p.setDeviceToken(deviceToken);
		p.setCreateTime(new Date());
		p.setUpdateTime(new Date());
		p.setDeviceType(Integer.valueOf(deviceType));
		List<UmengPushPO> list = umengPushDaoImpl.findBy("userId",userId);
		if(list.size()>0){
			p.setId(list.get(0).getId());
			umengPushDaoImpl.update4Selective(p);
		}else{
			umengPushDaoImpl.insert(p);
		}
	}
	
	public List<UmengPushPO> getUserTokens(){
		List<UmengPushPO> list = umengPushDaoImpl.findAll();
		return list;
	}
	 
}
