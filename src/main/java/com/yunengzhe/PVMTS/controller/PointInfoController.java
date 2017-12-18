package com.yunengzhe.PVMTS.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.AllMeasurePointDataDTO;
import com.yunengzhe.PVMTS.domain.dto.monitor.PointReqDTO;
import com.yunengzhe.PVMTS.domain.dto.monitor.PointsReqDTO;
import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.EquipmentInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PointBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryData;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryPointData;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.util.TimeUtil;

@Controller
@RequestMapping("/pointInfo")
public class PointInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PointInfoController.class);
	
	@Autowired
	private PointInfoService pointInfoService;
	@Autowired
	public StationMonitorService stationMonitorService; 
	/**
	 * 获取指定电站的所有测点列表
	 * (不分页)
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getPowerStationAllPointNOPage",method=RequestMethod.POST)
	@ResponseBody
	public Object getPowerStationAllPointNOPage(@RequestBody Map<String,String>map,
			HttpServletRequest request,
			HttpServletResponse response) {
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			String powerStationId = map.get("powerStationId");
			List<PointBaseInfoVO> list = pointInfoService.getPowerStationAllPointNOpage(powerStationId);
			return RestResponse.responseOk(list);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取指定电站的所有测点列表
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getPowerStationAllPoint",method=RequestMethod.POST)
	@ResponseBody
	public Object getPowerStationAllPoint(@RequestBody AllMeasurePointDataDTO allMeasurePointDataDTO,
			HttpServletRequest request,
			HttpServletResponse response) {
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			String powerStationId = allMeasurePointDataDTO.getPowerStationId();
			String measurePoint = allMeasurePointDataDTO.getMeasurePoint();
			String checkedPoint = allMeasurePointDataDTO.getCheckedPoint();
			Integer page = allMeasurePointDataDTO.getPage();
			Integer pagesize = allMeasurePointDataDTO.getPagesize();
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			ResultListVO resultListVO = pointInfoService.getPowerStationAllPoint(powerStationId,measurePoint,checkedPoint,page,pagesize);
			return RestResponse.responseList(request, resultListVO.getCounts(), resultListVO.getResultList());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取指定电站的所有设备
	 * @param request
	 * @param response
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getPowerStationEquipments/{powerStationId}")
	@ResponseBody
	public RestResponseVO getPowerStationEquipments(HttpServletRequest request,HttpServletResponse response,
			@PathVariable String powerStationId){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<EquipmentInfoVO> list = pointInfoService.getPowerStationEquioments(Integer.valueOf(powerStationId));
			return RestResponse.responseOk(list);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取指定电站指定设备类型的所有设备
	 * @param request
	 * @param response
	 * @param powerStationId
	 * @param equipmentType
	 * @return
	 */
	@RequestMapping(value="/getEquipments",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO getEquipments(HttpServletRequest request,HttpServletResponse response,
			String powerStationId,String equipmentType){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			if(StringUtil.empty(powerStationId)){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请求电站id为空");
			}
			if(StringUtil.empty(equipmentType)){
				equipmentType = null;
			}
			List<EquipmentInfoVO> list = pointInfoService.getEquioments(powerStationId,equipmentType);
			return RestResponse.responseOk(list);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 获取指定设备的所有测点列表
	 * (不分页)
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getPointsNOPage",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointsNOPage(@RequestBody Map<String,String>map,
			HttpServletRequest request,
			HttpServletResponse response) {
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			String powerStationId = map.get("powerStationId");
			if(StringUtil.empty(powerStationId)){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请求电站id为空");
			}
			String equipmentcontainerTableid = map.get("equipmentcontainerTableid");
			if(StringUtil.empty(equipmentcontainerTableid) || Integer.valueOf(equipmentcontainerTableid)==-1){
				equipmentcontainerTableid = null;
			}
			String equipmentcontainerId = map.get("equipmentcontainerId");
			if(StringUtil.empty(equipmentcontainerId) || (equipmentcontainerId.indexOf(",")==-1&&Integer.valueOf(equipmentcontainerId)==-1) ){
				equipmentcontainerId = null;
			}
			String measurementtypeName = map.get("measurementtypeName");
			if(StringUtil.empty(measurementtypeName)){
				measurementtypeName = null;
			}
			String checkedPoints = map.get("checkedPoints");
			String local = (String)HttpSessionUtil.getAttribute("local");
			List<PointInfoPO> list = pointInfoService.getPointsNOpage(powerStationId,equipmentcontainerTableid,equipmentcontainerId,measurementtypeName);
			for(int i=list.size()-1;i>=0;i--){
				if("en_US".equals(local)){
					list.get(i).setMeasurementtypeDescription(list.get(i).getPointEnglishName());
					list.get(i).setEquipmentcontainerName(list.get(i).getEquipmentcontainerNameEn());
				}
			}
			if(!StringUtil.empty(checkedPoints)){
				for(int i=list.size()-1;i>=0;i--){
					if(checkedPoints.indexOf(list.get(i).getId()+"")!=-1){
						list.remove(i);
					}
				}
			}
			return RestResponse.responseOk(list);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 月累计
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getPointMonthgeneration",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointMonthgeneration(@RequestBody PointReqDTO reqParam,HttpServletRequest request,
			HttpServletResponse response) {
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Date startTime = reqParam.getStartTime();
			Date endTime = reqParam.getEndTime(); 
			long minutes = reqParam.getMinutesSpan();
			if(reqParam.getAnaloginputId()<=0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "测点数据不正确");
			} 
			MonitorHistoryPointData pointData = stationMonitorService.getMonitorHistoryData(reqParam.getAnaloginputId(), startTime, endTime,60*minutes,reqParam.getHaveReal()==1);
			List<MonitorHistoryData> list = pointData.getHistoryDatas();
			List<MonitorHistoryData> result = new ArrayList<MonitorHistoryData>();
//			String kk="";
//			Date date11 = new Date();
			for(int i=0;i<list.size();i++){
				MonitorHistoryData monitorHistoryData = list.get(i);
				Date date = new Date(monitorHistoryData.getTime());
				String key = TimeUtil.date2String(date, "yyyy-MM-dd");
//				kk = key;
//				date11 = date;
				long time = TimeUtil.string2Date(key).getTime();
				if(result.size()==0){
					MonitorHistoryData monutor = new MonitorHistoryData();
					monutor.setTime(time);
					monutor.setValue(monitorHistoryData.getValue());
					result.add(monutor);
				}else{
					Boolean flag = false;
					for(int j=0;j<result.size();j++){
						if(result.get(j).getTime()==time){
							result.get(j).setValue(monitorHistoryData.getValue());
							flag = true;
						}
					}
					if(!flag){
						MonitorHistoryData monutor = new MonitorHistoryData();
						monutor.setTime(time);
						monutor.setValue(monitorHistoryData.getValue());
						result.add(monutor);
					}
				}
			}
			return RestResponse.responseOk(result); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 年累计
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getPointYearGeneration",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointYearGeneration(@RequestBody PointReqDTO reqParam,HttpServletRequest request,
			HttpServletResponse response) {
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Date startTime = reqParam.getStartTime();
			Date endTime = reqParam.getEndTime(); 
			long minutes = reqParam.getMinutesSpan();
			if(reqParam.getAnaloginputId()<=0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "测点数据不正确");
			} 
			MonitorHistoryPointData pointData = stationMonitorService.getMonitorHistoryData(reqParam.getAnaloginputId(), startTime, endTime,60*minutes,reqParam.getHaveReal()==1);
			List<MonitorHistoryData> list = pointData.getHistoryDatas();
			List<MonitorHistoryData> result = new ArrayList<MonitorHistoryData>();
			for(int i=0;i<list.size();i++){
				MonitorHistoryData monitorHistoryData = list.get(i);
				Date date = new Date(monitorHistoryData.getTime());
				String key = TimeUtil.date2String(date, "yyyy-MM");
				key+="-01";
				long time = TimeUtil.string2Date(key).getTime();
				if(result.size()==0){
					MonitorHistoryData monutor = new MonitorHistoryData();
					monutor.setTime(time);
					monutor.setValue(monitorHistoryData.getValue());
					result.add(monutor);
				}else{
					Boolean flag = false;
					for(int j=0;j<result.size();j++){
						if(result.get(j).getTime()==time){
							result.get(j).setValue(monitorHistoryData.getValue());
							flag = true;
						}
					}
					if(!flag){
						MonitorHistoryData monutor = new MonitorHistoryData();
						monutor.setTime(time);
						monutor.setValue(monitorHistoryData.getValue());
						result.add(monutor);
					}
				}
			}
			return RestResponse.responseOk(result); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 小时累计
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getPointHoursGeneration",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointHoursGeneration(@RequestBody PointReqDTO reqParam,HttpServletRequest request,
			HttpServletResponse response) {
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			 
			
			return RestResponse.responseOk(getPointHoursGeneration(reqParam)); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 小时累计
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getPointHoursGenerations",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointHoursGenerations(@RequestBody PointsReqDTO reqParam,HttpServletRequest request,
			HttpServletResponse response) {
		try{ 
			String ids = reqParam.getAnaloginputIds();
			if(StringUtils.isBlank(ids)){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "测点数据不正确");
			} 
			String[] idList = ids.split(",");
			long queryStart = System.currentTimeMillis();
			List<MonitorHistoryData> results = new ArrayList();
			for(int i=0; i<idList.length; i++){
				PointReqDTO reqParamOne = new PointReqDTO();
				reqParamOne.setAnaloginputId(Integer.valueOf(idList[i]));
				reqParamOne.setEndTime(reqParam.getEndTime());
				reqParamOne.setStartTime(reqParam.getStartTime());
				reqParamOne.setHaveReal(reqParam.getHaveReal());
				reqParamOne.setMinutesSpan(reqParam.getMinutesSpan());
				List<MonitorHistoryData> onResult = getPointHoursGeneration(reqParamOne);
				if(onResult.size()!=0&&onResult.get(0).getTime()!=0){
					if(results.size()<onResult.size()){
						results.clear();
						for(MonitorHistoryData res:onResult){
							results.add(res);
						}
					}else{
						for(int j=0;j<onResult.size();j++){
							results.get(j).setValue(results.get(j).getValue()+onResult.get(j).getValue());
						}
					}
				}
			}
			
			return RestResponse.responseOk(results); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
	public List<MonitorHistoryData> getPointHoursGeneration(PointReqDTO reqParam ) {
	 		 
			long startTimeLong = reqParam.getStartTime().getTime()/1000/60/60*1000*60*60-1000*60*60;
			long endTimeLong = reqParam.getEndTime().getTime();
			 
			Date startTime = new Date(startTimeLong);
			Date endTime = reqParam.getEndTime(); 
			long minutes = reqParam.getMinutesSpan();
			if(reqParam.getAnaloginputId()<=0){
				return null;
			} 
			
			List<MonitorHistoryData> results = new ArrayList<MonitorHistoryData>();
			Map<String,MonitorHistoryData> resultsMap = new HashMap<String,MonitorHistoryData>();
			for(long start=startTime.getTime();start<endTimeLong;start=start+60*60*1000){

				Date date = new Date(start);
				String key = TimeUtil.date2String(date, "yyyy-MM-dd HH");
				
				MonitorHistoryData result = new MonitorHistoryData();
				result.setTime(start);
				result.setValue(0);
				results.add(result);
				resultsMap.put(key, result);
			}
			
			MonitorHistoryPointData pointData = stationMonitorService.getMonitorHistoryData(reqParam.getAnaloginputId(), startTime, endTime,60*minutes,reqParam.getHaveReal()==1);
			List<MonitorHistoryData> list = pointData.getHistoryDatas();
			String keyflag="";
			double minValue=0;
			double maxValue=0;
			for(int i=0;i<list.size();i++){
				MonitorHistoryData monitorHistoryData = list.get(i);
				Date date = new Date(monitorHistoryData.getTime());
				String key = TimeUtil.date2String(date, "yyyy-MM-dd HH");
				MonitorHistoryData result = resultsMap.get(key);
				if(result!=null){
					if("".equals(keyflag)){
						keyflag=key;//list中第一个key
						if(monitorHistoryData.getValue()!=0){//如果value不等于0
							minValue=monitorHistoryData.getValue();//初始化一小时内最小值
							maxValue=monitorHistoryData.getValue();//初始化一小时内最大值
						}
					}else if((!"".equals(keyflag))&&(keyflag.equals(key))){//在一个小时内
						if(monitorHistoryData.getValue()!=0){//如果value不为0
							if(minValue==0){//如果最小值还是0
								minValue=monitorHistoryData.getValue();//初始化一小时内最小值
							}
							maxValue=monitorHistoryData.getValue();//更新最大值	
							/*判断上一个小时的值
							 * 如果是0，并且当前小时的最小值不是0
							 * 上一个小时的值为当前小时的最小值
							 * */
							int leftnum = Integer.valueOf(key.split(" ")[1]);
							int num = leftnum-1;
							String numstr = num+"";
							if(numstr.length()==1){
								numstr=0+numstr;
							}
							String leftkey = key.split(" ")[0]+" "+numstr;
							MonitorHistoryData leftresult = resultsMap.get(leftkey);
//							if(leftresult==null&&minValue!=0){
//								if(maxValue!=minValue){
//									Date tdate = TimeUtil.string2Date(leftkey);
//									leftresult = new MonitorHistoryData();
//									leftresult.setTime(tdate.getTime());
//									leftresult.setValue(minValue);
//								}
//							}
//							if(leftresult!=null&&leftresult.getValue()==0&&minValue!=0){
//								if(maxValue!=minValue){
//									leftresult.setValue(minValue);
//								}
//							}
						}
						keyflag=key;//更新key值
					}else if((!"".equals(keyflag))&&(!keyflag.equals(key))){//变更小时
						minValue=0;//更新该小时的最小值
						if(monitorHistoryData.getValue()!=0){//如果当前值不是0
							minValue=monitorHistoryData.getValue();//初始化一小时内最小值
							maxValue=monitorHistoryData.getValue();//更新最大值
						}
						keyflag=key;//更新key值
					}
					result.setValue(maxValue);//对当前key赋值
				} 
			}
			
			List<MonitorHistoryData> resultsData = new ArrayList<MonitorHistoryData>();
			double maxV = 0;
//			if(results.size()>0){
//				maxV = results.get(0).getValue();
//			}
			for(int i=1;i<results.size();i++){
				MonitorHistoryData monitorHistoryData1 = results.get(i);
				MonitorHistoryData monitorHistoryData2 = results.get(i-1);
				double value = monitorHistoryData1.getValue()-monitorHistoryData2.getValue();
				if(value<=0){
					value = 0; 
				}
				if(monitorHistoryData2.getValue()<0){
					value=0;
				}
				if(monitorHistoryData2.getValue()==0 && maxV>0){
					value=0;//用于判断是否是中间点为0，第一个有值的点之后的所有 数据为0的点都是非法的
				}

				if(monitorHistoryData1.getValue()>0){//用于判断是否是中间点为0
					maxV = monitorHistoryData1.getValue();
				}
				MonitorHistoryData result = new MonitorHistoryData();
				result.setTime(monitorHistoryData1.getTime());
				result.setValue(value);
				resultsData.add(result); 
			}
			
			return resultsData;  
	}
	
	
	/**
	 * 历年累计
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getPointGrossGeneration",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointGrossGeneration(@RequestBody PointReqDTO reqParam,HttpServletRequest request,
			HttpServletResponse response) {
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Date startTime = reqParam.getStartTime();
			Date endTime = reqParam.getEndTime(); 
			long minutes = reqParam.getMinutesSpan();
			if(reqParam.getAnaloginputId()<=0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "测点数据不正确");
			} 
			MonitorHistoryPointData pointData = stationMonitorService.getMonitorHistoryData(reqParam.getAnaloginputId(), startTime, endTime,60*minutes,reqParam.getHaveReal()==1);
			List<MonitorHistoryData> list = pointData.getHistoryDatas();
			List<MonitorHistoryData> result = new ArrayList<MonitorHistoryData>();
			for(int i=0;i<list.size();i++){
				MonitorHistoryData monitorHistoryData = list.get(i);
				Date date = new Date(monitorHistoryData.getTime());
				String key = TimeUtil.date2String(date, "yyyy").split(":")[0];
				long time = date.getTime();
				if(result.size()==0){
					MonitorHistoryData monutor = new MonitorHistoryData();
					monutor.setTime(time);
					monutor.setValue(monitorHistoryData.getValue());
					result.add(monutor);
				}else{
					Boolean flag = false;
					for(int j=0;j<result.size();j++){
						String timestr = TimeUtil.date2String(new Date(result.get(j).getTime()),"yyyy");
						if(timestr.equals(key)){
							result.get(j).setValue(monitorHistoryData.getValue());
							flag = true;
						}
					}
					if(!flag){
						MonitorHistoryData monutor = new MonitorHistoryData();
						monutor.setTime(time);
						monutor.setValue(monitorHistoryData.getValue());
						result.add(monutor);
					}
				}
			}
			return RestResponse.responseOk(result); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
