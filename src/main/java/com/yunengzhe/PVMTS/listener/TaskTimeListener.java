package com.yunengzhe.PVMTS.listener;

import java.text.ParseException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunengzhe.PVMTS.ReportDailyThread;
import com.yunengzhe.PVMTS.domain.po.TaskConfigPO;
import com.yunengzhe.PVMTS.service.TaskConfigService;
import com.yunengzhe.commons.util.SpringContextUtil;

public class TaskTimeListener  implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(TaskTimeListener.class);
	private static ReportDailyThread reportDailyThread = new ReportDailyThread();
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("开始加载每日报表生成时间");
		Thread t=new Thread(new Runnable(){
			public void run(){
				String cron = "0 0 23 * * ?";	
				try {  
					CronTriggerImpl  cronTrigger = (CronTriggerImpl) SpringContextUtil.getBean("cronTrigger");  
					StdScheduler scheduler= (StdScheduler) SpringContextUtil.getBean("schedulerTrigger");  
					JobDetailImpl jobDetail = (JobDetailImpl) SpringContextUtil.getBean("jobDetail");  
						TaskConfigService taskConfigService = (TaskConfigService) SpringContextUtil.getBean("taskConfigService"); 
						logger.info("taskConfigService is null?"+(taskConfigService==null));
						TaskConfigPO p = taskConfigService.getConfig();
						logger.info("TaskConfigPO is null?"+(p==null));
						if(p==null){
							logger.info("数据库无配置");
							cron="0 0 23 * * ?";	
						}else{
							logger.info("重新设置每日报表生成时间");
							if(p.getType()==1){
								String date = p.getConfigTime();
								String[] str = date.split(":");
								logger.info("配置的小时数----"+str[0]);
								logger.info("配置的分钟数"+str[1]);
								cron = "0 "+str[1]+" "+str[0]+" * * ?";
							}else{//默认时间
								cron="0 0 23 * * ?";	
							} 
						}
						CronTriggerImpl trigger = new CronTriggerImpl();  
						trigger.setCronExpression(cron);
			            TriggerKey triggerKey = cronTrigger.getKey();  
			            trigger.setJobName(jobDetail.getKey().getName());  
			            trigger.setKey(triggerKey);  
		                scheduler.addJob(jobDetail, true);  
		                if (scheduler.checkExists(triggerKey)) {  
		                	logger.info("reshceduleJob--->"+triggerKey);
		                    scheduler.rescheduleJob(triggerKey, trigger);  
		                } 
		            } catch (SchedulerException e) {  
		                throw new IllegalArgumentException(e);  
		            } catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			}
		});
		t.start();
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	} 
	
	
}
