package com.yunengzhe.PVMTS.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.LogsInfoPO;
import com.yunengzhe.PVMTS.domain.po.ThirduserPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.LogsInfoVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.EquipPoints;
import com.yunengzhe.PVMTS.service.LogsInfoService;
import com.yunengzhe.PVMTS.service.PointCacheService;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.ThirduserService;
import com.yunengzhe.PVMTS.util.LogCreateWord;
import com.yunengzhe.PVMTS.util.PointUtil;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.util.CacheManUtil;
import com.yunengzhe.commons.util.TimeUtil;
/**
 * 
 * @author liuchaoshuai
 *
 */
@Controller
@RequestMapping("/logsInfo")
public class LogsInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogsInfoController.class);
	
	@Autowired
	private LogsInfoService logsInfoService;
	@Autowired
	private PointInfoService pointInfoService;
	@Autowired
	private ThirduserService thirduserService;
	/**
	 * 新增日志
	 * @param request
	 * @param response
	 * @param updateLogsInfoDTO
	 * @return
	 */
	@RequestMapping(value="/addLogs",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO addLogs(HttpServletRequest request,HttpServletResponse response,@RequestBody LogsInfoPO logsBaseInfoVO){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			logsBaseInfoVO.setUserId(user.getUserid());
			logsBaseInfoVO.setId(null);
			logsBaseInfoVO.setLogStatus(0);
			logsBaseInfoVO.setCompanyId(1);
			logsInfoService.addLogs(logsBaseInfoVO);
			return RestResponse.responseOk();
		}catch(Exception e){ 
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常");
		}
	}
	/**
	 * 删除日志
	 * @param request
	 * @param response
	 * @param updateLogsInfoDTO
	 * @return
	 */
	@RequestMapping(value="/deleteLogs/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO deleteLogs(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer id){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			return RestResponse.responseOk(logsInfoService.deleteLogs(id));
		}catch(Exception e){ 
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常");
		}
	}
	/**
	 * 查看日志详情
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/readLogs/{id}")
	@ResponseBody
	public RestResponseVO readLogs(HttpServletRequest request,HttpServletResponse response,@PathVariable String id){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			LogsInfoPO logsInfoPO = logsInfoService.readLogs(id);
			LogsInfoVO logsInfoVO = new LogsInfoVO();
			logsInfoVO.setEquipmentType(logsInfoPO.getEquipmentType());
			logsInfoVO.setCompanyName(logsInfoPO.getCompanyName());
			logsInfoVO.setCreateTime(TimeUtil.string2Date(TimeUtil.date2String(logsInfoPO.getCreateTime(),"yyyy-MM-dd")));
			logsInfoVO.setDescription(logsInfoPO.getDescription());
			logsInfoVO.setId(logsInfoPO.getId());
			logsInfoVO.setCompanyId(logsInfoPO.getCompanyId());
			logsInfoVO.setPowerStationId(logsInfoPO.getPowerStationId());
			logsInfoVO.setEquipmentId(logsInfoPO.getEquipmentId());
			logsInfoVO.setPowerStationName(logsInfoPO.getPowerStationName());
			logsInfoVO.setTopic(logsInfoPO.getTopic());
			logsInfoVO.setUserName(logsInfoPO.getUserName()); 
			logsInfoVO.setCategoryId(logsInfoPO.getCategoryId());
			logsInfoVO.setCategoryName(logsInfoPO.getCategoryName());
			logsInfoVO.setHandling(logsInfoPO.getHandling());
			logsInfoVO.setRemark(logsInfoPO.getRemark());
			logsInfoVO.setCorporation(logsInfoPO.getCorporation());
			logsInfoVO.setResponsible(logsInfoPO.getResponsible());
			logsInfoVO.setLogStatus(logsInfoPO.getLogStatus());
			logsInfoVO.setKaleidoscope(logsInfoPO.getKaleidoscope());
			logsInfoVO.setHandler(logsInfoPO.getHandler());
			return RestResponse.responseOk(logsInfoVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常");
		}
	}
	/**
	 * 修改日志
	 * @param request
	 * @param response
	 * @param updateLogsInfoDTO
	 * @return
	 */
	@RequestMapping(value="/updateLogs",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO updateLogs(HttpServletRequest request,HttpServletResponse response,@RequestBody LogsInfoPO logsBaseInfoVO){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			logsBaseInfoVO.setUserId(user.getUserid());
			logsBaseInfoVO.setCompanyId(1);
			if(logsBaseInfoVO.getCompanyName()==""){
				logsBaseInfoVO.setCompanyName(null);
			}
			if(logsBaseInfoVO.getLogStatus()!=null){
				if(logsBaseInfoVO.getLogStatus()==1){
					logsBaseInfoVO.setHandler(user.getUserid()+"");
				}
			}
			logsInfoService.updateLogs(logsBaseInfoVO);
			return RestResponse.responseOk();
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常");
		}
	}
	/**
	 * 获取日志列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectLogs",method=RequestMethod.POST)
	@ResponseBody
	public Object selectLogs(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String>map){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			String searchstr = map.get("searchstr");
			Integer page = Integer.valueOf(map.get("page")+"");
			Integer pagesize = Integer.valueOf(map.get("pagesize")+"");
			String isThird = map.get("isThird");
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize =Paginator.ALARM_MAX_PAGESIZE;
			}
			if(StringUtil.empty(searchstr)){
				searchstr=null;
			}
			ResultListVO<LogsInfoPO> resultListVO = logsInfoService.selectLogs(page,pagesize,searchstr);
			List<LogsInfoPO> resultList = resultListVO.getResultList();
			List<LogsInfoVO> logsinfolist = new ArrayList<LogsInfoVO>();
			String local = (String)HttpSessionUtil.getAttribute("local");
			if("1".equals(isThird)){
				for (LogsInfoPO logsInfoPO : resultList) {
						List<ThirduserPO> measurementTypeId = thirduserService.getMeasurementTypeId(user.getUserid(), logsInfoPO.getEquipmentId().toString(), logsInfoPO.getEquipmentType().toString());
						for (ThirduserPO thirduserPO : measurementTypeId) {
							if(Integer.parseInt(thirduserPO.getMeasurementtypeId())==logsInfoPO.getId()){
								LogsInfoVO logsinfoVO = new LogsInfoVO();
								logsinfoVO.setCompanyId(logsInfoPO.getCompanyId());
								logsinfoVO.setCompanyName(logsInfoPO.getCompanyName());
								logsinfoVO.setCreateTime(logsInfoPO.getCreateTime());
								logsinfoVO.setDescription(logsInfoPO.getDescription());
								logsinfoVO.setEquipmentId(logsInfoPO.getEquipmentId());
								
								logsinfoVO.setEquipmentType(logsInfoPO.getEquipmentType());
								logsinfoVO.setId(logsInfoPO.getId());
								logsinfoVO.setPowerStationId(logsInfoPO.getPowerStationId());
								String key = PointUtil.getKey(logsInfoPO.getEquipmentType(),logsInfoPO.getEquipmentId());  
								EquipPoints equipPoints = (EquipPoints) CacheManUtil.getObjectFromJson(PointCacheService.CACHE_PREFIX_EQUIP, key,EquipPoints.class);
								if("en_US".equals(local)){
									logsinfoVO.setEquipmentName(equipPoints.getNameEn());
									logsinfoVO.setPowerStationName(logsInfoPO.getEnglishname());
								}else{
									logsinfoVO.setEquipmentName(equipPoints.getName());
									logsinfoVO.setPowerStationName(logsInfoPO.getPowerStationName());
								}
								logsinfoVO.setTopic(logsInfoPO.getTopic());
								logsinfoVO.setUserName(logsInfoPO.getUserName());
								logsinfoVO.setNickname(logsInfoPO.getNickname());
								logsinfoVO.setCategory(logsInfoPO.getCategory());
								logsinfoVO.setCategoryName(logsInfoPO.getCategoryName());
								logsinfoVO.setLogStatus(logsInfoPO.getLogStatus());
//								logsinfoVO.setHandling(logsInfoPO.getHandling());
//								logsinfoVO.setRemark(logsInfoPO.getRemark());
//								logsinfoVO.setCorporation(logsInfoPO.getCorporation());
//								logsinfoVO.setResponsible(logsInfoPO.getResponsible());
								logsinfolist.add(logsinfoVO);
							}
						}
				}
			}else{
				for (LogsInfoPO logsInfoPO : resultList) {
					LogsInfoVO logsinfoVO = new LogsInfoVO();
					logsinfoVO.setCompanyId(logsInfoPO.getCompanyId());
					logsinfoVO.setCompanyName(logsInfoPO.getCompanyName());
					logsinfoVO.setCreateTime(logsInfoPO.getCreateTime());
					logsinfoVO.setDescription(logsInfoPO.getDescription());
					logsinfoVO.setEquipmentId(logsInfoPO.getEquipmentId());
					logsinfoVO.setEquipmentName(logsInfoPO.getEquipmentName());
					logsinfoVO.setEquipmentType(logsInfoPO.getEquipmentType());
					logsinfoVO.setId(logsInfoPO.getId());
					logsinfoVO.setPowerStationId(logsInfoPO.getPowerStationId());
					String key = PointUtil.getKey(logsInfoPO.getEquipmentType(),logsInfoPO.getEquipmentId());  
					EquipPoints equipPoints = (EquipPoints) CacheManUtil.getObjectFromJson(PointCacheService.CACHE_PREFIX_EQUIP, key,EquipPoints.class);
					if("en_US".equals(local)){
						logsinfoVO.setEquipmentName(equipPoints.getNameEn());
						logsinfoVO.setPowerStationName(logsInfoPO.getEnglishname());
					}else{
						logsinfoVO.setEquipmentName(equipPoints.getName());
						logsinfoVO.setPowerStationName(logsInfoPO.getPowerStationName());
					}
					logsinfoVO.setTopic(logsInfoPO.getTopic());
					logsinfoVO.setUserName(logsInfoPO.getUserName());
					logsinfoVO.setNickname(logsInfoPO.getNickname());
					logsinfoVO.setCategory(logsInfoPO.getCategory());
					logsinfoVO.setCategoryName(logsInfoPO.getCategoryName());
					logsinfoVO.setLogStatus(logsInfoPO.getLogStatus());
					logsinfolist.add(logsinfoVO);
				}	
			} 
			return RestResponse.responseList(request, resultListVO.getCounts(),logsinfolist);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常");
		}
	}
	/**
	 * 获取第三方日志列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectThirdLogsSearch",method=RequestMethod.POST)
	@ResponseBody
	public Object selectThirdLogsSearch(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String>map){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			String topic = map.get("topic");
			String discription = map.get("discription");
			String equipment = map.get("equipment");
			String equipmentId = map.get("equipmentId");
			String powerStationId = map.get("powerStationId");
			String category = map.get("category");
			Integer page = Integer.valueOf(map.get("page")+"");
			Integer pagesize = Integer.valueOf(map.get("pagesize")+"");
			String isThird = map.get("isThird");
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize =Paginator.ALARM_MAX_PAGESIZE;
			}
			if(StringUtil.empty(topic)){
				topic=null;
			}
			if(StringUtil.empty(discription)){
				discription=null;
			}
			if(StringUtil.empty(equipment)){
				equipment=null;
			}
			if(StringUtil.empty(equipmentId)){
				equipmentId=null;
			}
			if(StringUtil.empty(powerStationId)){
				powerStationId=null;
			}
			if(StringUtil.empty(category)){
				category=null;
			}
			ResultListVO<LogsInfoPO> resultListVO = logsInfoService.selectThirdLogsSearch(page,pagesize,topic,discription,equipment,equipmentId,powerStationId,category);
			List<LogsInfoPO> resultList = resultListVO.getResultList();
			List<LogsInfoVO> logsinfolist = new ArrayList<LogsInfoVO>();
			for (LogsInfoPO logsInfoPO : resultList) {
				LogsInfoVO logsinfoVO = new LogsInfoVO();
				logsinfoVO.setCompanyId(logsInfoPO.getCompanyId());
				logsinfoVO.setCompanyName(logsInfoPO.getCompanyName());
				logsinfoVO.setCreateTime(logsInfoPO.getCreateTime());
				logsinfoVO.setDescription(logsInfoPO.getDescription());
				logsinfoVO.setEquipmentId(logsInfoPO.getEquipmentId());
				logsinfoVO.setEquipmentName(logsInfoPO.getEquipmentName());
				logsinfoVO.setEquipmentType(logsInfoPO.getEquipmentType());
				logsinfoVO.setId(logsInfoPO.getId());
				logsinfoVO.setPowerStationId(logsInfoPO.getPowerStationId());
				logsinfoVO.setPowerStationName(logsInfoPO.getPowerStationName());
				logsinfoVO.setTopic(logsInfoPO.getTopic());
				logsinfoVO.setUserName(logsInfoPO.getUserName());
				logsinfoVO.setNickname(logsInfoPO.getNickname());
				logsinfoVO.setCategory(logsInfoPO.getCategory());
				logsinfoVO.setCategoryName(logsInfoPO.getCategoryName());
				logsinfoVO.setLogStatus(logsInfoPO.getLogStatus());
				logsinfolist.add(logsinfoVO);
			}	
			return RestResponse.responseList(request, resultListVO.getCounts(),logsinfolist);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常");
		}
	}
	/**
	 * 获取日志列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectLogsSearch",method=RequestMethod.POST)
	@ResponseBody
	public Object selectLogsSearch(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String>map){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			String topic = map.get("topic");
			String discription = map.get("discription");
			String equipment = map.get("equipment");
			String equipmentId = map.get("equipmentId");
			String powerStationId = map.get("powerStationId");
			String category = map.get("category");
			Integer page = Integer.valueOf(map.get("page")+"");
			Integer pagesize = Integer.valueOf(map.get("pagesize")+"");
			String isThird = map.get("isThird");
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize =Paginator.ALARM_MAX_PAGESIZE;
			}
			if(StringUtil.empty(topic)){
				topic=null;
			}
			if(StringUtil.empty(discription)){
				discription=null;
			}
			if(StringUtil.empty(equipment)){
				equipment=null;
			}
			if(StringUtil.empty(equipmentId)){
				equipmentId=null;
			}
			if(StringUtil.empty(powerStationId)){
				powerStationId=null;
			}
			if(StringUtil.empty(category)){
				category=null;
			}
			ResultListVO<LogsInfoPO> resultListVO = logsInfoService.selectLogsSearch(page,pagesize,topic,discription,equipment,equipmentId,powerStationId,category);
			List<LogsInfoPO> resultList = resultListVO.getResultList();
			List<LogsInfoVO> logsinfolist = new ArrayList<LogsInfoVO>();
			if("1".equals(isThird)){
				for (LogsInfoPO logsInfoPO : resultList) {
					List<ThirduserPO> measurementTypeId = thirduserService.getMeasurementTypeId(user.getUserid(), logsInfoPO.getEquipmentId().toString(), logsInfoPO.getEquipmentType().toString());
					for (ThirduserPO thirduserPO : measurementTypeId) {
						if(Integer.parseInt(thirduserPO.getMeasurementtypeId())==logsInfoPO.getId()){
							LogsInfoVO logsinfoVO = new LogsInfoVO();
							logsinfoVO.setCompanyId(logsInfoPO.getCompanyId());
							logsinfoVO.setCompanyName(logsInfoPO.getCompanyName());
							logsinfoVO.setCreateTime(logsInfoPO.getCreateTime());
							logsinfoVO.setDescription(logsInfoPO.getDescription());
							logsinfoVO.setEquipmentId(logsInfoPO.getEquipmentId());
							logsinfoVO.setEquipmentName(logsInfoPO.getEquipmentName());
							logsinfoVO.setEquipmentType(logsInfoPO.getEquipmentType());
							logsinfoVO.setId(logsInfoPO.getId());
							logsinfoVO.setPowerStationId(logsInfoPO.getPowerStationId());
							logsinfoVO.setPowerStationName(logsInfoPO.getPowerStationName());
							logsinfoVO.setTopic(logsInfoPO.getTopic());
							logsinfoVO.setUserName(logsInfoPO.getUserName());
							logsinfoVO.setNickname(logsInfoPO.getNickname());
							logsinfoVO.setCategory(logsInfoPO.getCategory());
							logsinfoVO.setCategoryName(logsInfoPO.getCategoryName());
							logsinfoVO.setLogStatus(logsInfoPO.getLogStatus());
							logsinfolist.add(logsinfoVO);
						}
					}
				}
			}else{
				for (LogsInfoPO logsInfoPO : resultList) {
					LogsInfoVO logsinfoVO = new LogsInfoVO();
					logsinfoVO.setCompanyId(logsInfoPO.getCompanyId());
					logsinfoVO.setCompanyName(logsInfoPO.getCompanyName());
					logsinfoVO.setCreateTime(logsInfoPO.getCreateTime());
					logsinfoVO.setDescription(logsInfoPO.getDescription());
					logsinfoVO.setEquipmentId(logsInfoPO.getEquipmentId());
					logsinfoVO.setEquipmentName(logsInfoPO.getEquipmentName());
					logsinfoVO.setEquipmentType(logsInfoPO.getEquipmentType());
					logsinfoVO.setId(logsInfoPO.getId());
					logsinfoVO.setPowerStationId(logsInfoPO.getPowerStationId());
					logsinfoVO.setPowerStationName(logsInfoPO.getPowerStationName());
					logsinfoVO.setTopic(logsInfoPO.getTopic());
					logsinfoVO.setUserName(logsInfoPO.getUserName());
					logsinfoVO.setNickname(logsInfoPO.getNickname());
					logsinfoVO.setCategory(logsInfoPO.getCategory());
					logsinfoVO.setCategoryName(logsInfoPO.getCategoryName());
					logsinfoVO.setLogStatus(logsInfoPO.getLogStatus());
					logsinfolist.add(logsinfoVO);
				}	
			} 
			return RestResponse.responseList(request, resultListVO.getCounts(),logsinfolist);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常");
		}
	}
	/**
	 * @throws IOException 
	 * @Title:getReportDetails
	 * @Description:TODO下载
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/downloadlog")
	public String  downloadlog(String id,String powername,HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return null;
		}
			Map<String,Object> map = new HashMap<String,Object>();
			LogsInfoPO readLogs = logsInfoService.readLogs(id);
			map.put("id", id);//日志序号
			map.put("topic", readLogs.getTopic());//日志名称
			map.put("category", readLogs.getCategoryName());//日志类别
			map.put("powername", powername);//电站名称
			map.put("equipmentName", readLogs.getEquipmentName());//涉及设备
			map.put("description", readLogs.getDescription());//描述
			map.put("username", readLogs.getUserName());//创建人员名称
			map.put("createTime", readLogs.getCreateTime());//创建时间
			map.put("updataTime", readLogs.getUpdateTime());//更新时间
			
			map.put("kaleidoscope", readLogs.getKaleidoscope());
			map.put("handling", readLogs.getHandling());
			map.put("handler", readLogs.getHandler());
			map.put("responsible", readLogs.getResponsible());
			map.put("corporation", readLogs.getCorporation());
			map.put("remark", readLogs.getRemark());
			File file = null;  
	        InputStream fin = null;  
	        Writer writer = null;
	        OutputStream out = response.getOutputStream();  
	        try {
            // 调用工具类WordGenerator的createDoc方法生成Word文档  
        	file = LogCreateWord.createDoc(map,"log", "longieb",null);
            // 以流的形式下载文件。
			 fin = new FileInputStream(file);
	           response.setCharacterEncoding("utf-8");  
	           response.setContentType("application/wsword");  
	            //设置浏览器以下载的方式处理该文件默认名为resume.doc  
	           response.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("日志处理报告-"+id, "UTF-8")+".doc");  
	           out = response.getOutputStream();
	           byte[] buffer = new byte[512];  // 缓冲区  
	           int bytesToRead = -1;
	           while((bytesToRead = fin.read(buffer)) != -1) {  
	           	out.write(buffer, 0, bytesToRead);  
	           }
	        } finally {  
	            if(fin != null) fin.close();  
	            if(out != null) out.flush();  
	            //if(file != null) file.delete(); // 删除临时doc文件  
	            //if(filepdf != null) filepdf.delete(); // 删除临时pdf文件  
	        }  
		 return null;
	}
}
