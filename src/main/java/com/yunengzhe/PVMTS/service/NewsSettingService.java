package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.NewsSettingDao;
import com.yunengzhe.PVMTS.domain.dto.UserTokenDTO;
import com.yunengzhe.PVMTS.domain.po.NewsSettingPO;
import com.yunengzhe.PVMTS.domain.po.PowerstationUserPO;


@Service("newsSettingService")
public class NewsSettingService {

	private static final Logger logger = LoggerFactory.getLogger(NewsSettingService.class);
	
	@Autowired
	private NewsSettingDao newsSettingDaoImpl;
	/**
	 * 获取指定用户的消息设置
	 * @param userId
	 * @return
	 */
	public NewsSettingPO getNewsSetting(Integer userId){
		List<NewsSettingPO> list = newsSettingDaoImpl.findBy("userId", userId);
		if(list.size()==0){
			Date date = new Date();
			NewsSettingPO np = new NewsSettingPO();
			np.setCreateTime(date);
			np.setPush(0);
			np.setSound(0);
			np.setVibrate(0);
			np.setUpdateTime(date);
			np.setUserId(userId);
			newsSettingDaoImpl.insert(np);
			list.add(np);
		}
		return list.get(0);
	}
	
	public List<UserTokenDTO> getNesSettings(String userIds){
		List<UserTokenDTO> list = newsSettingDaoImpl.getUserTokens(userIds);
		return list;
	}
	/**
	 * 更新消息配置
	 * @param newsSettingPO
	 */
	public void updateNewsSetting(NewsSettingPO newsSettingPO){
		newsSettingDaoImpl.update4Selective(newsSettingPO);
	}
	
	
	public List<PowerstationUserPO> getPowerstationUser(){
		return newsSettingDaoImpl.getPowerstationUser();
	}
}
