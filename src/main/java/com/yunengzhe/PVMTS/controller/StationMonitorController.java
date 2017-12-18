package com.yunengzhe.PVMTS.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.yunengzhe.PVMTS.domain.dto.monitor.EquipmentReqDTO;
import com.yunengzhe.PVMTS.domain.dto.monitor.EquipmentsReqDTO;
import com.yunengzhe.PVMTS.domain.dto.monitor.PointReqDTO;
import com.yunengzhe.PVMTS.domain.dto.monitor.PointsReqDTO;
import com.yunengzhe.PVMTS.domain.dto.monitor.SyncDTO;
import com.yunengzhe.PVMTS.domain.po.MeasurementtypePO;
import com.yunengzhe.PVMTS.domain.po.ThirduserPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureData;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureDatas;
import com.yunengzhe.PVMTS.domain.vo.monitor.EquipmentDataVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryPointData;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorRealData;
import com.yunengzhe.PVMTS.service.MeasurementtypeService;
import com.yunengzhe.PVMTS.service.ThirduserService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.util.PropertiesUtil;

@Controller
@RequestMapping("/stationmonitor")
public class StationMonitorController {


	private static final Logger logger = LoggerFactory.getLogger(StationMonitorController.class);

	@Autowired
	public StationMonitorService stationMonitorService; 
	@Autowired
	private ThirduserService thirduserService;
	@Autowired
	private MeasurementtypeService measurementtypeService;
	/**
	 *加载多个点的历史数据
	 * @param pointIds
	 */
	@RequestMapping(value="/history/points",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointHistoryPoints(@RequestBody PointsReqDTO reqParam, HttpServletRequest request,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Date startTime = reqParam.getStartTime();
			Date endTime = reqParam.getEndTime();
			String ids = reqParam.getAnaloginputIds();
			//long minutes = reqParam.getMinutesSpan();
			if(StringUtils.isBlank(ids)){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "测点数据不正确");
			}
			List<MonitorHistoryPointData> listResults = new ArrayList<MonitorHistoryPointData>();
			String[] idList = ids.split(",");
			long queryStart = System.currentTimeMillis();
			for(int i=0; i<idList.length; i++){
				if(StringUtils.isNumeric(idList[i])){
					int pointid = Integer.valueOf(idList[i]);
					MonitorHistoryPointData pointData = stationMonitorService.getMonitorHistoryData(pointid, startTime, endTime,reqParam.getHaveReal()==1);
					listResults.add(pointData);
				}
			}
			long queryEnd = System.currentTimeMillis();
			logger.debug("query time long:" + (queryEnd-queryStart));
			return RestResponse.responseOk(listResults); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 *加载单个个点的历史数据
	 * @param pointIds
	 */
	@RequestMapping(value="/history/sync",method=RequestMethod.POST)
	@ResponseBody
	public Object syncHistory(@RequestBody SyncDTO reqParam, HttpServletRequest request,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			
			if(reqParam.getStartTime()==null || reqParam.getEndTime()==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请求参数不正确");
			}
			
			Date startTime = reqParam.getStartTime();
			Date endTime = reqParam.getEndTime();
			stationMonitorService.syncHistoryData(user.getUserid(),reqParam.getName(),startTime, endTime);
			return RestResponse.responseOk(); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}


	/**
	 *加载单个个点的历史数据
	 * @param pointIds
	 */
	@RequestMapping(value="/history",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointHistoryOnePoint(@RequestBody PointReqDTO reqParam, HttpServletRequest request,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Date startTime = reqParam.getStartTime();
			Date endTime = reqParam.getEndTime(); 
			//long minutes = reqParam.getMinutesSpan();
			if(reqParam.getAnaloginputId()<=0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "测点数据不正确");
			} 
			MonitorHistoryPointData pointData = stationMonitorService.getMonitorHistoryData(reqParam.getAnaloginputId(), startTime, endTime,reqParam.getHaveReal()==1); 
			return RestResponse.responseOk(pointData.getHistoryDatas()); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}

	/**
	 *加载单个点的历史数据，有时间间隔，补充空点
	 * @param pointIds
	 */
	@RequestMapping(value="/historyRedress",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointHistoryOnePointRedress(@RequestBody PointReqDTO reqParam, HttpServletRequest request,HttpServletResponse response){
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
			return RestResponse.responseOk(pointData.getHistoryDatas()); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}

 
	/**
	 *加载多个点的历史数据
	 * @param pointIds
	 */
	@RequestMapping(value="/historyRedress/points",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointHistoryPointsRedress(@RequestBody PointsReqDTO reqParam, HttpServletRequest request,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Date startTime = reqParam.getStartTime();
			Date endTime = reqParam.getEndTime();
			String ids = reqParam.getAnaloginputIds();
			long minutes = reqParam.getMinutesSpan();
			if(StringUtils.isBlank(ids)){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "测点数据不正确");
			}
			List<MonitorHistoryPointData> listResults = new ArrayList<MonitorHistoryPointData>();
			String[] idList = ids.split(",");
			long queryStart = System.currentTimeMillis();
			for(int i=0; i<idList.length; i++){
				if(StringUtils.isNumeric(idList[i])){
					int pointid = Integer.valueOf(idList[i]);
					MonitorHistoryPointData pointData = stationMonitorService.getMonitorHistoryData(pointid, startTime, endTime,minutes*60,reqParam.getHaveReal()==1);
					listResults.add(pointData);
				}
			}
			long queryEnd = System.currentTimeMillis();
			logger.debug("query time long:" + (queryEnd-queryStart));
			logger.debug("数据查询结果(可供报表下载)----------------------------------->>:" + listResults);
			return RestResponse.responseOk(listResults); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}

	/**
	 * 加载多个测点
	 * @param pointIds
	 */
	@RequestMapping(value="/real/points",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointPoints(@RequestBody Map<String,String> map, HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try{
			String analoginputIds = map.get("analoginputIds");
			List<MonitorRealData> listResults = new ArrayList<MonitorRealData>();
			String[] idList = analoginputIds.split(","); 
			for(int i=0; i<idList.length; i++){
				if(StringUtils.isNumeric(idList[i])){
					MonitorRealData realData = stationMonitorService.getMonitorRealData(Integer.valueOf(idList[i]));
					listResults.add(realData);
				}
			}
			 
			return RestResponse.responseOk(listResults);

		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 加载某个设备
	 * @param pointIds
	 */
	@RequestMapping(value="/real/equipment",method=RequestMethod.POST)
	@ResponseBody
	public Object getEquipment(@RequestBody EquipmentReqDTO map, HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try{ 
			String equipmentcontainerTableid = map.getEquipmentcontainerTableid();
			String equipmentcontainerId = map.getEquipmentcontainerId();
			if(StringUtils.isNumeric(equipmentcontainerTableid) && StringUtils.isNumeric(equipmentcontainerId)){
				AnalogStructureDatas data = stationMonitorService.getMonitorEquipRealData(Integer.valueOf(equipmentcontainerTableid),Integer.valueOf(equipmentcontainerId));
				if(map.getIsThirdUser() != null && map.getIsThirdUser().equals("1")){
					List<AnalogStructureData> data2 = data.getData();
					List<ThirduserPO> measurementTypeId = thirduserService.getMeasurementTypeId(user.getUserid(),equipmentcontainerId,equipmentcontainerTableid);
					List<AnalogStructureData> list = new ArrayList<AnalogStructureData>();
					for (int j = 0; j < measurementTypeId.size(); j++) {
						String aliasname = measurementtypeService.get(measurementTypeId.get(j).getMeasurementtypeId()).getAliasname();
						measurementTypeId.get(j).setAliasname(aliasname);
						for (int i = 0; i < data2.size(); i++) {
							if(data2.get(i).getMeasuerName().equals(measurementTypeId.get(j).getAliasname())){
								AnalogStructureData analog = new AnalogStructureData();
								analog.setAvaiabletag(data2.get(i).getAvaiabletag());
								analog.setMeasuerName(data2.get(i).getMeasuerName());
								analog.setMtime(data2.get(i).getMtime());
								analog.setQualitycode(data2.get(i).getQualitycode());
								analog.setTime(data2.get(i).getTime());
								analog.setValue(data2.get(i).getValue());
								list.add(analog);
							}
							
						}
					}
					return RestResponse.responseOk(list);
				}
				return RestResponse.responseOk(data.getData());
			}else{
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请求数据不正确");
			}

		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 加载 相同类型的多个设备
	 * @param pointIds
	 */
	@RequestMapping(value="/real/equipments",method=RequestMethod.POST)
	@ResponseBody
	public Object getEquipments(@RequestBody EquipmentsReqDTO equipments, HttpServletRequest request,HttpServletResponse response){
		try{ 
			 
			if(equipments.getEquipments().size()<=0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请求数据不正确");
			}
			List<EquipmentDataVO> resequipments = new ArrayList<EquipmentDataVO>();
			for(EquipmentReqDTO dto :equipments.getEquipments() ){
				String equipmentcontainerTableid = dto.getEquipmentcontainerTableid();
				String equipmentcontainerId = dto.getEquipmentcontainerId();
				
				if(StringUtils.isNumeric(equipmentcontainerTableid) && StringUtils.isNumeric(equipmentcontainerId)){
					AnalogStructureDatas data = stationMonitorService.getMonitorEquipRealData(Integer.valueOf(equipmentcontainerTableid),Integer.valueOf(equipmentcontainerId));
					
					EquipmentDataVO dataVO = new EquipmentDataVO();
					dataVO.setEquipmentcontainerId(equipmentcontainerId);
					dataVO.setEquipmentcontainerTableid(equipmentcontainerTableid); 
					dataVO.setData(data.getData()); 
					resequipments.add(dataVO);
				}else{
					return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请求数据不正确");
				}
			}  
			return RestResponse.responseOk(resequipments);


		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 加载单个点实时数据
	 * @param pointIds
	 */
	@RequestMapping(value="/real/point",method=RequestMethod.POST)
	@ResponseBody
	public Object getPointPoint(@RequestBody Map<String,String> map, HttpServletRequest request,HttpServletResponse response){
		try{
			MonitorRealData realData = null;
			String analoginputId = map.get("analoginputId");
			if(StringUtils.isNumeric(analoginputId)){
				realData = stationMonitorService.getMonitorRealData(Integer.valueOf(analoginputId));
				return RestResponse.responseOk(realData);
			}else{
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请求数据不正确");
			}

		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
