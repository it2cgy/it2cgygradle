package com.yunengzhe.PVMTS.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunengzhe.PVMTS.company.service.IOldCompanyService;
import com.yunengzhe.PVMTS.util.SpringContextUtil; 
import com.yunengzhe.PVMTS.util.Json.ReadJson;
  


public class OperateTask {
	/**
	 * 控制时
	 */
	public static final Integer TIMEHOUR0 = 1;
	/**
	 * 控制分
	 */
	public static final Integer TIMEMINUTE0 = 0;
	/**
	 * 控制秒
	 */
	public static final Integer TIMESECOND0 = 0;
	/**
	 * 控制时
	 */
	public static final Integer TIMEHOUR1 = 10;
	/**
	 * 控制分
	 */
	public static final Integer TIMEMINUTE1 = 0;
	/**
	 * 控制秒
	 */
	public static final Integer TIMESECOND1 = 0;

	private static final Logger logger = LoggerFactory.getLogger(OperateTask.class);

	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);   
	
	public void executeTranslateTimer1() {
		Runnable task = new Runnable() {
			public void run() {
				Date startDate = new Date();
				logger.info("OperateTask start*******************************************");
				IOldCompanyService companyServiceImpl = (IOldCompanyService) SpringContextUtil.getBean("companyServiceImpl"); 
				companyServiceImpl.updatestatus();
				Date endDate = new Date();
				logger.info("execution time:{} milliseconds",endDate.getTime()-startDate.getTime());
				logger.info("OperateTask end*********************************************");
				
//				scheduler.shutdown();
			}  
		};

		long delay = 0;

		// 当前日历类并设置当前时间
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(new Date());
		// 获取当前时间的毫秒值
		long now = calendarNow.getTimeInMillis();

		// 当前日历类，并设置定时时间
		Calendar calenderSchedule = Calendar.getInstance();
		calenderSchedule.setTime(new Date());
		// 控制时（凌晨一点）
		calenderSchedule.set(Calendar.HOUR_OF_DAY,TIMEHOUR1);
		// 控制分
		calenderSchedule.set(Calendar.MINUTE,TIMEMINUTE1);
		// 控制秒
		calenderSchedule.set(Calendar.SECOND, TIMESECOND1);
		// 得出执行任务的时间,此处为今天的01：30：00
		long shedule = calenderSchedule.getTimeInMillis();
		// 间隔（多少毫秒后第一次执行）
		final int period = 24 * 60 * 60 * 1000;
		delay = shedule - now;
		// 如果延时为负（说明已经超过了执行的时间 加一天后再执行）
		if (delay < 0) {
			calenderSchedule.set(Calendar.DAY_OF_MONTH,
					calenderSchedule.get(Calendar.DAY_OF_MONTH) + 1);
			shedule = calenderSchedule.getTimeInMillis();
			delay = shedule - now;
		}
				
		// 这里设定将延时每天固定执行 
		if (scheduler.isShutdown()) {  
			scheduler = Executors.newScheduledThreadPool(1);
			scheduler.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);  
		} else {  
			scheduler.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
		}  
		logger.info("启动定时器  {}分钟后第一次执行***********************",delay/1000/60);
		logger.info("定时器  执行周期是{}分钟**********************",period/1000/60);
	}  
	/**
	 * 描述：		
	 * @param TYPE 判断是否是本地化0：本地化；1：网络版
	 * @param TIMEHOUR 控制时
	 * @param TIMEMINUTE 控制分
	 * @param TIMESECOND 控制秒
	 */
	public void executeTranslateTimer0() {
		Runnable task = new Runnable() {
			public void run() {
				Date startDate = new Date();
				logger.info("OperateTask start*******************************************");
				System.out.println("本地化进行时。。。。。。。。。。。。。");
				//CheckedOnLine.checkeJson();
				Date endDate = new Date();
				logger.info("execution time:{} milliseconds",endDate.getTime()-startDate.getTime());
				logger.info("OperateTask end*********************************************");
				
//				scheduler.shutdown();
			}  
		};

		long delay = 0;

		// 当前日历类并设置当前时间
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(new Date());
		// 获取当前时间的毫秒值
		long now = calendarNow.getTimeInMillis();

		// 当前日历类，并设置定时时间
		Calendar calenderSchedule = Calendar.getInstance();
		calenderSchedule.setTime(new Date());
		// 控制时（凌晨一点）
		calenderSchedule.set(Calendar.HOUR_OF_DAY,TIMEHOUR0);
		// 控制分
		calenderSchedule.set(Calendar.MINUTE,TIMEMINUTE0);
		// 控制秒
		calenderSchedule.set(Calendar.SECOND, TIMESECOND0);
		// 得出执行任务的时间,此处为今天的01：30：00
		long shedule = calenderSchedule.getTimeInMillis();
		// 间隔（多少毫秒后第一次执行）
//		final int period = 30 * 1000; 
		final int period = 24 * 60 * 60 * 1000; 
		delay = shedule - now;
		// 如果延时为负（说明已经超过了执行的时间 加一天后再执行）
		if (delay < 0) {
			calenderSchedule.set(Calendar.DAY_OF_MONTH,
					calenderSchedule.get(Calendar.DAY_OF_MONTH) + 1);
			shedule = calenderSchedule.getTimeInMillis();
//			delay = shedule - now;
			delay = 10 * 1000;
		}
				
		// 这里设定将延时每天固定执行 
		if (scheduler.isShutdown()) {  
			scheduler = Executors.newScheduledThreadPool(1);
			scheduler.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);  
		} else {  
			scheduler.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
		}  
		logger.info("启动定时器  {}分钟后第一次执行***********************",delay/1000/60);
		logger.info("定时器  执行周期是{}分钟**********************",period/1000/60);
	}  

	//停止任务，不再提交新任务，已提交任务会继续执行以致完成  
	public void stop() {  
		scheduler.shutdown();  
	}
}
