package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.dao.UserGetuiDao;
import com.yunengzhe.PVMTS.domain.po.UserGetuiPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;

/**
 * @ClassName:UserGetuiService
 * @Description:TODO(描述)
 * @author chenguiyang
 * @date 2017年7月3日下午2:10:16
 */
@Service("userGetuiService")
public class UserGetuiService {

	private static final Logger logger = LoggerFactory.getLogger(UserGetuiService.class);
	
	@Autowired
	private UserGetuiDao userGetuiDaoImpl;
	
	/**
	 * @Title:setClientID
	 * @Description:TODO(绑定用户id与设备id) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void setClientID(int userId,String cid,String clientType){
		UserGetuiPO userGetuiPO = new UserGetuiPO();
		userGetuiPO.setUserId(userId);
		userGetuiPO.setClientId(cid);
		userGetuiPO.setUpdateTime(new Date());
		userGetuiPO.setClientType(clientType);
		List<UserGetuiPO> listData = userGetuiDaoImpl.findBy("userId",userId);
		
		if(listData.size()>0){
			userGetuiPO.setId(listData.get(0).getId());
			userGetuiDaoImpl.update4Selective(userGetuiPO);
		}else{
			userGetuiDaoImpl.insert(userGetuiPO);
		}
	}
	
	/**
	 * @Title:getClientID
	 * @Description:TODO(根据用户id查询设备id) 
	 * @param @param userId
	 * @return void
	 * @throws
	 */
	public String getClientID(int userId){
		UserGetuiPO result = userGetuiDaoImpl.findUniqueBy("userId",userId);
		if(result!=null){
			return result.getClientId();
		}
		return "";
	}
	 
}
