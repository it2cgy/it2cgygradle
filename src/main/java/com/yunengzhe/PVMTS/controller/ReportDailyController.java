package com.yunengzhe.PVMTS.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.ReportDailyThread;
import com.yunengzhe.PVMTS.domain.po.PowerStationPO;
import com.yunengzhe.PVMTS.domain.po.ReportDailyPO;
import com.yunengzhe.PVMTS.domain.po.ReportDailyPowerPO;
import com.yunengzhe.PVMTS.domain.po.TaskConfigPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.AmmeterInfoVO;
import com.yunengzhe.PVMTS.domain.vo.InverterInfoVO;
import com.yunengzhe.PVMTS.domain.vo.IvEquipmentVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.SaveReportDailyVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.WeatherInfoVO;
import com.yunengzhe.PVMTS.domain.vo.weather.WeatherVO;
import com.yunengzhe.PVMTS.service.DeviceRatedService;
import com.yunengzhe.PVMTS.service.IvEquipmentService;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.service.ReportDailyPowerService;
import com.yunengzhe.PVMTS.service.ReportDailyService;
import com.yunengzhe.PVMTS.service.TaskConfigService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.ApiClient;
import com.yunengzhe.PVMTS.util.JsonUtil;
import com.yunengzhe.PVMTS.util.http.ResponseData;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.util.BigDecimalUtils;
@Component
@Controller
@RequestMapping("/reportDaily")
public class ReportDailyController {

	private static final Logger logger = LoggerFactory.getLogger(ReportDailyController.class);
	@Autowired
	private TaskConfigService taskConfigService;
	@Autowired
	private ReportDailyService reportDailyService;

	@Autowired
	private ReportDailyPowerService reportDailyPowerService;

	@Autowired
	private PowerStationService powerStationService;

	@Autowired
	private PowerStationController powerStationController;

	@Autowired
	private PointInfoService pointInfoService;

	@Autowired
	private StationMonitorService stationMonitorService;

	@Autowired
	private InverterController inverterController;

	@Autowired
	private IvEquipmentService ivEquipmentService;

	@Autowired
	private DeviceRatedService deviceRatedService;

	@Autowired
	private SchedulerFactoryBean schedulerTrigger;

	@Autowired
	private CronTriggerFactoryBean cronTrigger;

	@Autowired
	private MethodInvokingJobDetailFactoryBean jobDetail;
	/**
	 * 获取日报信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getReportDailyInfo",method=RequestMethod.POST)
	@ResponseBody
	public Object getReportDailyInfo(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String>map){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Integer page = Integer.parseInt(map.get("page"));
			Integer pagesize = Integer.parseInt(map.get("pagesize"));
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			} 
			ResultListVO<ReportDailyPO> result =reportDailyService.getReportDailyInfo(page,pagesize);
			return RestResponse.responseList(request, result.getCounts(), result.getResultList()); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取日报信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getReportById/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Object getReportById(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer id){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			logger.debug("查询下载报表id----------->"+id);
			ReportDailyPO reportDailyPO = reportDailyService.get(id);
			logger.debug("查询下载报表id结果----------->"+reportDailyPO);
			return RestResponse.responseOk(reportDailyPO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}

	/**
	 * 删除日报信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/deleteReportDaily/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO deleteReportDaily(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer id){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			return RestResponse.responseOk(reportDailyService.deleteReportDaily(id));
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}




	@RequestMapping(value="/saveReportDaily",method=RequestMethod.GET)
	@ResponseBody
	public void saveReportDaily() throws Exception{
		ReportDailyThread r = new ReportDailyThread();
		r.start();
	}

	/**
	 * @Title:getReportDetails
	 * @Description:TODO下载
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/downLoadword")
	public Object  downLoadword(Integer id,Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			logger.debug("下载报表id是------>"+id);
			ReportDailyPO reportDailyPO = reportDailyService.get(id);
			String path = reportDailyPO.getUrl();
			if(path==null||path==""){
				return null;
			}
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.setCharacterEncoding("utf-8");  
			response.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(reportDailyPO.getReportname(), "UTF-8")+".doc");  
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/wsword");
			logger.debug("下载文件成功");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			logger.error("下载文件异常,下载文件路径不存在");
			ex.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/configTime")
	@ResponseBody
	public Object configTime(HttpServletRequest request,HttpServletResponse response,String datetime,Integer configId,Integer configtype){
		try {
			logger.info("开始配置时间"+datetime);
			String[] str = datetime.split(":");
			String cron = "0 "+str[1]+" "+str[0]+" * * ?";
			cronTrigger.setCronExpression(cron);
			CronTrigger c = cronTrigger.getObject();
			Scheduler scheduler = schedulerTrigger.getScheduler();
			CronTriggerImpl trigger = new CronTriggerImpl();  
			trigger.setCronExpression(cron);  
			TriggerKey triggerKey = c.getKey();  
			trigger.setJobName(jobDetail.getObject().getKey().getName());  
			trigger.setKey(triggerKey);  
			try {  
				scheduler.addJob(jobDetail.getObject(), true);  
				if (scheduler.checkExists(triggerKey)) {  
					scheduler.rescheduleJob(triggerKey, trigger);  
				} 
			} catch (SchedulerException e) {  
				throw new IllegalArgumentException(e);  
			}  

			TaskConfigPO p = new TaskConfigPO();
			p.setConfigTime(datetime);
			p.setUpdateTime(new Date());
			p.setType(configtype);
			p.setDefaultTime("23:00");
			if(configId==null){
				p.setCreateTime(new Date());
				taskConfigService.insertConfig(p);
			}else{
				p.setId(configId);
				taskConfigService.updateConfig(p);
			}
			return RestResponse.responseOk();
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}

}
