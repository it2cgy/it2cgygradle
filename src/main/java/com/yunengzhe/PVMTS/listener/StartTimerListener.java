package com.yunengzhe.PVMTS.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunengzhe.PVMTS.company.entity.LocalConfig;
import com.yunengzhe.PVMTS.i18n.ResourceDataManager;
import com.yunengzhe.PVMTS.util.MacUtil;
import com.yunengzhe.PVMTS.util.Json.ReadJson;

public class StartTimerListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(StartTimerListener.class);
	OperateTask task = null;
	public void contextInitialized(ServletContextEvent sce) { 
		//LocalConfig mpg = new LocalConfig();
		//mpg = ReadJson.getMpg(); 
		ResourceDataManager.initDataUS();//初始化国际化
	//	Integer productType = Integer.valueOf(mpg.getProducttype());
		Boolean flag = true;
		logger.info("TimerListener start******************");
		task = new OperateTask();
//		if(productType==0){
//			logger.info("***************进入本地化************");
//			logger.info("***************进入本地化************");
//			logger.info("***************进入本地化************");
//			if(mac.equals(macstr)){
//				flag = true;
//			}else{
//				flag = false;
//			}
//			if(!flag){
//				System.exit(0);
//			}
//			task.executeTranslateTimer0();
//		}else{
//			task.executeTranslateTimer1();
//		}	
		
		task.executeTranslateTimer1();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		 
	}

}
