package com.yunengzhe.PVMTS.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunengzhe.PVMTS.service.PointCacheService;
import com.yunengzhe.PVMTS.service.UserService;
import com.yunengzhe.commons.util.SpringContextUtil;
 

public class UserCacheListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(UserCacheListener.class); 
	 
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("TimerListener start******************");
		Thread t=new Thread(new Runnable(){

			public void run(){
				try{  
					UserService userService = (UserService) SpringContextUtil.getBean("userService");  
					userService.cacheAllUser();
					PointCacheService pointCacheService = (PointCacheService) SpringContextUtil.getBean("pointCacheService");  
					pointCacheService.cacheAllPoint();
					pointCacheService.cacheAllPointByEquip();
				}catch (Exception e) {
					logger.error("",e);
					e.printStackTrace(); 
				} 
			}
		}
				);
		t.start();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		 
	}

}
