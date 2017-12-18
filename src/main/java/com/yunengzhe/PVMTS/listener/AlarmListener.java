package com.yunengzhe.PVMTS.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunengzhe.PVMTS.AlarmThread;
import com.yunengzhe.PVMTS.domain.po.MailConfigPO;
import com.yunengzhe.PVMTS.service.MailConfigService;
import com.yunengzhe.commons.util.PropertiesUtil;
import com.yunengzhe.commons.util.SpringContextUtil;


/**
 * @ClassName:AlarmListener
 * @Description:TODO(报警定时发送邮件处理)
 * @author chenguiyang
 * @date 2017年9月12日下午5:00:56
 */
public class AlarmListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(AlarmListener.class);
	private static AlarmThread alarmThread = new AlarmThread();
	private MailConfigService mailConfigService;
	private static int alarmPush = PropertiesUtil.getInt("alarmPush", 0);//密码
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("AlarmListener start alarmPush "+ alarmPush +"******************");
		Thread t=new Thread(new Runnable(){
			public void run(){
				
				try{  
					if(mailConfigService==null){
						mailConfigService = (MailConfigService) SpringContextUtil.getBean("mailConfigService");
					}
					MailConfigPO config = mailConfigService.getConfigInfo();
					if(alarmPush==1 && config!=null){
						alarmThread.start();
					}
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
		// TODO Auto-generated method stub
		
	}

}
