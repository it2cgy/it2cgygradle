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

import com.yunengzhe.PVMTS.domain.dto.PowerstationDTO;
import com.yunengzhe.PVMTS.domain.dto.SearchMeansurePointDataDTO;
import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.po.RoleAndPowerPO;
import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;
import com.yunengzhe.PVMTS.domain.po.WeatherStationPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.AlarmCountByUipmentVO;
import com.yunengzhe.PVMTS.domain.vo.AmmeterBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.AmmeterInfoVO; 
import com.yunengzhe.PVMTS.domain.vo.Counts;
import com.yunengzhe.PVMTS.domain.vo.EquipmentTypeVO;
import com.yunengzhe.PVMTS.domain.vo.HistoryData;
import com.yunengzhe.PVMTS.domain.vo.HistoryDataResult;
import com.yunengzhe.PVMTS.domain.vo.InverterBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.InverterInfoVO;
import com.yunengzhe.PVMTS.domain.vo.InverterTypeCounts;
import com.yunengzhe.PVMTS.domain.vo.PointHistoryVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationListVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.WeatherInfoVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureData;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureDatas;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryData;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryPointData;
import com.yunengzhe.PVMTS.service.AlarmInfoService;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.service.RoleAndPowerService;
import com.yunengzhe.PVMTS.service.UserAndRoleService;
import com.yunengzhe.PVMTS.service.WeatherStationService;
import com.yunengzhe.PVMTS.service.WorkOrderInfoService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.util.TimeUtil;

@Controller
@RequestMapping("/powerStation")
public class PowerStationController {
	
	//系统Pr=当日发电量/(日辐射总量/3.6*光伏组件装机容量)*100
	private static final Logger logger = LoggerFactory.getLogger(PowerStationController.class);
	/**
	 * 电站编码，备用
	 */
	public final static int POWER_STATION  = 0;
	/**
	 * 设备类型-直流汇流箱编码
	 */
	public final static int COMBIRNER_BOX_DC  = 1;
	/**
	 * 设备类型-集中逆变器编码
	 */
	public final static int INVERTR_CENTRALIZED  = 2;
	/**
	 * 设备类型-组串逆变器设备编码
	 */
	public final static int INVERTR_SERIES  = 3;
	/**
	 * 设备类型-交流汇流柜设备编码
	 */
	public final static int COMBIRNER_BOX_AC  = 4;
	/**
	 * 设备类型-电表设备编码
	 */
	public final static int ELECTRIC_METER  = 5;
	/**
	 * 设备类型-并网柜设备编码
	 */
	public final static int GRID_CABINET  = 6;
	/**
	 * 设备类型-箱变设备编码
	 */
	public final static int BOX_TRANSFORMER  = 7;
	/**
	 * 设备类型-环境监测仪设备编码
	 */
	public final static int ENVIRONMENT_MONITOR  = 8;
	/**
	 * 公司管理员
	 */
	public final static int COMPANY_ADMIN_ROLEID  = 2;
	
	@Autowired
	private PointInfoService pointInfoService;
	
	@Autowired
	private RoleAndPowerService roleAndPowerService;
	@Autowired
	public StationMonitorService stationMonitorService; 
	@Autowired
	private UserAndRoleService userAndRoleService;
	@Autowired
	private PowerStationService powerStationService; 
	@Autowired
	private WeatherStationService weatherStationService;
	@Autowired
	private AlarmInfoService alarmInfoService;
	@Autowired
	private WorkOrderInfoService workOrderInfoService;
	/**
	 * 获取电站基本信息电站列表（不包含实时信息）
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getPowerStationBaseInfo",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO getPowerStationBaseInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String,String>map){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			String searchstr = map.get("searchstr");
			String companyId = user.getCompanyId()+"";
			Integer roleid = 0;
			List<UserAndRolePO> rolleList = user.getRoleList();
			if(rolleList.size()>0){
				roleid = rolleList.get(0).getRoleId();
			}
			if(roleid==COMPANY_ADMIN_ROLEID){//系统管理员
				roleid=null;
			}
			List<PowerStationBaseInfoVO> list = powerStationService.getPowerStationBaseInfo("",companyId,searchstr,roleid==null?null:roleid.toString());
			return RestResponse.responseOk(list);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取电站基本信息电站列表（不包含实时信息）
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getPowerStationById/{powerStationId}",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO getPowerStationById(HttpServletRequest request,HttpServletResponse response,
			@PathVariable Integer powerStationId){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			PowerStationBaseInfoVO PowerStationBaseInfoVO = powerStationService.getPowerStationInfo(powerStationId+"");
			return RestResponse.responseOk(PowerStationBaseInfoVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取电站信息电站列表（包含实时信息）
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getPowerStationInfo",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO getPowerStationInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String,String>map){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			String searchstr = map.get("searchstr");
			String companyId = user.getCompanyId()+"";
			List<Integer> powerstationList = user.getPowerStationList();
			List<PowerStationInfoVO> result = new ArrayList<PowerStationInfoVO>();
			//获取基本信息
//			List<PowerStationBaseInfoVO> list = powerStationService.getPowerStationBaseInfo(companyId,searchstr);
			String ids="";
			if(powerstationList!=null&&powerstationList.size()>0){
				for(int s : powerstationList){
					ids+=s+",";
				}
				ids=ids.substring(0,ids.length()-1);
			}
			Integer roleid = 0;
			List<UserAndRolePO> rolleList = user.getRoleList();
			if(rolleList.size()>0){
				roleid = rolleList.get(0).getRoleId();
			}
			if(roleid==COMPANY_ADMIN_ROLEID){//公司管理员
				roleid=null;
			}
		 
			List<PowerStationBaseInfoVO> list = powerStationService.getPowerStationBaseInfo(ids,companyId,searchstr,roleid==null?null:roleid.toString());
			logger.debug(list.toString());
			for(int i=0;i<list.size();i++){
				PowerStationBaseInfoVO baseInfo = list.get(i);
				PowerStationInfoVO PowerStationInfoVO = new PowerStationInfoVO();
				PowerStationInfoVO.setAddress(baseInfo.getAddress());
				PowerStationInfoVO.setId(baseInfo.getId());
				PowerStationInfoVO.setInstallCapacity(baseInfo.getInstallCapacity());
				PowerStationInfoVO.setName(baseInfo.getName());
				PowerStationInfoVO.setCode(baseInfo.getCode());
				PowerStationInfoVO.setLat(baseInfo.getLat());
				PowerStationInfoVO.setLng(baseInfo.getLng());
				PowerStationInfoVO.setManager(baseInfo.getManager());
				//投资方单位
				PowerStationInfoVO.setInvestFirmName(baseInfo.getInvestFirmName());
				RestResponseVO WeatherInfoData = getWeatherInfo(request,baseInfo.getId()+"",response);
				WeatherInfoVO WeatherInfoVO = (WeatherInfoVO) WeatherInfoData.getData();
				//辐照度
				PowerStationInfoVO.setIrradiance(WeatherInfoVO.getIrradiance());
				//温度
				PowerStationInfoVO.setTemperature(WeatherInfoVO.getTemperature());
				PowerStationInfoVO.setRadiantExposure(WeatherInfoVO.getRadiantExposure());
				PowerStationInfoVO.setImgUrl(baseInfo.getImgUrl());
				//总功率，累计发电量，日发电量，日发电量集合
				AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(POWER_STATION,Integer.valueOf(baseInfo.getId()+""));  
				List<AnalogStructureData> PointResult = datas.getData();
				for(int k=0;k<PointResult.size();k++){
					AnalogStructureData data = PointResult.get(k);
					if(data.getMeasuerName().equals("GeneratedActivePower")){
						PowerStationInfoVO.setGeneratedActivePower(data.getValue());
					}else if(data.getMeasuerName().equals("GenerationDaily")){
						PowerStationInfoVO.setGenerationDaily(data.getValue());
					}else if(data.getMeasuerName().equals("GenerationGross")){
						PowerStationInfoVO.setGenerationGross(data.getValue());
					}
				}
				//日发电量id
				PointInfoPO pointInfo = pointInfoService.getPointInfo("GenerationDaily", 0, Integer.valueOf(baseInfo.getId()+""));
				//总功率id
				PointInfoPO pointInfo2 = pointInfoService.getPointInfo("GeneratedActivePower", 0, Integer.valueOf(baseInfo.getId()+""));
				PointInfoPO pointInfo3 = pointInfoService.getPointInfo("GenerationGross", 0, Integer.valueOf(baseInfo.getId()+""));
				if(pointInfo!=null){
					PowerStationInfoVO.setGenerationDailyId(pointInfo.getId());
				}else{
					PowerStationInfoVO.setGenerationDailyId(-1);
				}
				if(pointInfo!=null){
					PowerStationInfoVO.setGeneratedActivePowerId(pointInfo2.getId());
				}else{
					PowerStationInfoVO.setGeneratedActivePowerId(-1);
				}
				if(pointInfo!=null){
					PowerStationInfoVO.setGenerationGrossId(pointInfo3.getId());
				}else{
					PowerStationInfoVO.setGenerationGrossId(-1);
				}
				//PR值
				RestResponseVO pr = getPRValue(request,baseInfo.getId()+"",response);
				PowerStationInfoVO.setPr(Double.valueOf(pr.getData()+""));
				//报警
				PowerStationInfoVO.setAlarms(alarmInfoService.getAlarmCount(baseInfo.getId()+"").get(0).getPowerAlarmCount());
				//获取逆变器实时数据停机数、通讯异常
				InverterTypeCounts InverterTypeCounts = (InverterTypeCounts) getStopCommunicationAnomalyCounts(baseInfo.getId()+"").getData();
				PowerStationInfoVO.setCommunicationAnomaly(InverterTypeCounts.getException());
				PowerStationInfoVO.setStop(InverterTypeCounts.getAbNormalstop()+InverterTypeCounts.getNormalstop());
				//最后一次体检时间
				Date lastDate = workOrderInfoService.physicalTime(baseInfo.getId());
				if(lastDate==null){
					PowerStationInfoVO.setLastPhysicalExaminationTime(TimeUtil.string2Date("1970-01-01"));
				}else{
					PowerStationInfoVO.setLastPhysicalExaminationTime(lastDate);
				}
				result.add(PowerStationInfoVO);
			}
			return RestResponse.responseOk(result);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 获取电表基本信息列表（不包括实时数据）
	 * @param powerStationId
	 * @return 
	 */
	@RequestMapping(value="/getAmmeterBaseInfo/{powerStationId}")
	@ResponseBody
	public RestResponseVO getAmmeterBaseInfo(HttpServletRequest request,@PathVariable String powerStationId,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<AmmeterBaseInfoVO> result = powerStationService.getAmmeterBaseInfo(powerStationId);
			return RestResponse.responseOk(result);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 获取电表信息列表（包括实时数据）
	 * @param powerStationId
	 * @return 
	 */
	@RequestMapping(value="/getAmmeterInfo/{powerStationId}")
	@ResponseBody
	public RestResponseVO getAmmeterInfo(HttpServletRequest request,@PathVariable String powerStationId,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<AmmeterInfoVO> result = new ArrayList<AmmeterInfoVO>();
			//获取点表基本信息
			List<AmmeterBaseInfoVO> list = powerStationService.getAmmeterBaseInfo(powerStationId);
			Date date = new Date();
			for(int i=0;i<list.size();i++){
				AmmeterBaseInfoVO baseInfo = list.get(i);
				AmmeterInfoVO AmmeterInfoVO = new AmmeterInfoVO();
				AmmeterInfoVO.setId(baseInfo.getId());
				AmmeterInfoVO.setModel(baseInfo.getModel());
				AmmeterInfoVO.setName(baseInfo.getName());
				if(StringUtil.empty(baseInfo.getSerialNumber())){
					AmmeterInfoVO.setSerialNumber(baseInfo.getName());
				}else{
					AmmeterInfoVO.setSerialNumber(baseInfo.getSerialNumber());
				}
				AmmeterInfoVO.setUpdateTime(date);
				//总功率，总电能（实时）
				//String measuerNames = "ThreePhaseActivePower,ReverseActiveP";
				AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(ELECTRIC_METER, baseInfo.getId());  
			 
				List<AnalogStructureData> rslist = datas.getData();
				for(int j=0;j<rslist.size();j++){
					if(rslist.get(j).getMeasuerName().equals("ThreePhaseActivePower")){
						AmmeterInfoVO.setThreePhaseActivePower(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("ReverseActiveP")){
						AmmeterInfoVO.setReverseActiveP(rslist.get(j).getValue());
					}
				}
				result.add(AmmeterInfoVO);
			}
			return RestResponse.responseOk(result);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取电表信息列表（包括实时数据）
	 * @param powerStationId
	 * @return 
	 */
	@RequestMapping(value="/getAmmeterDetail/{powerStationId}")
	@ResponseBody
	public RestResponseVO getAmmeterDetail(HttpServletRequest request,@PathVariable String powerStationId,Integer flag,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<AmmeterInfoVO> result = new ArrayList<AmmeterInfoVO>();
			//获取点表基本信息
			List<AmmeterBaseInfoVO> list = powerStationService.getAmmeterBaseInfo(powerStationId);
			Date date = new Date();
			for(int i=0;i<list.size();i++){
				AmmeterBaseInfoVO baseInfo = list.get(i);
				AmmeterInfoVO AmmeterInfoVO = new AmmeterInfoVO();
				AmmeterInfoVO.setId(baseInfo.getId());
				AmmeterInfoVO.setModel(baseInfo.getModel());
				AmmeterInfoVO.setName(baseInfo.getName());
				if(StringUtil.empty(baseInfo.getSerialNumber())){
					AmmeterInfoVO.setSerialNumber(baseInfo.getName());
				}else{
					AmmeterInfoVO.setSerialNumber(baseInfo.getSerialNumber());
				}
				AmmeterInfoVO.setUpdateTime(date);
				//总功率，总电能（实时）
				//String measuerNames = "ThreePhaseActivePower,ReverseActiveP";
				AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(ELECTRIC_METER, baseInfo.getId());  
			 
				List<AnalogStructureData> rslist = datas.getData();
				for(int j=0;j<rslist.size();j++){
					if(Integer.valueOf(baseInfo.getMeterType()+"")==flag){
						if(rslist.get(j).getMeasuerName().equals("GenerationDaily")){
							AmmeterInfoVO.setReverseActiveP(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("GenerationMonth")){
							AmmeterInfoVO.setGenerationMonth(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("GenerationYear")){
							AmmeterInfoVO.setGenerationYear(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("GenerationGross")){
							AmmeterInfoVO.setGenerationGross(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationDaily")){
							AmmeterInfoVO.setUnifiedPowerGenerationDaily(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationMonth")){
							AmmeterInfoVO.setUnifiedPowerGenerationMonth(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationYear")){
							AmmeterInfoVO.setUnifiedPowerGenerationYear(rslist.get(j).getValue());
						}
					}
				}
				result.add(AmmeterInfoVO);
			}
			return RestResponse.responseOk(result);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
	/**
	 * 获取电表信息列表（包括实时数据）
	 * @param powerStationId
	 * @return 
	 */
	public List<AmmeterInfoVO> getAmmeterDetailInfo(String powerStationId,Integer flag){
		try{
			List<AmmeterInfoVO> result = new ArrayList<AmmeterInfoVO>();
			//获取点表基本信息
			List<AmmeterBaseInfoVO> list = powerStationService.getAmmeterBaseInfo(powerStationId);
			Date date = new Date();
			for(int i=0;i<list.size();i++){
				AmmeterBaseInfoVO baseInfo = list.get(i);
				AmmeterInfoVO AmmeterInfoVO = new AmmeterInfoVO();
				AmmeterInfoVO.setId(baseInfo.getId());
				AmmeterInfoVO.setModel(baseInfo.getModel());
				AmmeterInfoVO.setName(baseInfo.getName());
				if(StringUtil.empty(baseInfo.getSerialNumber())){
					AmmeterInfoVO.setSerialNumber(baseInfo.getName());
				}else{
					AmmeterInfoVO.setSerialNumber(baseInfo.getSerialNumber());
				}
				AmmeterInfoVO.setUpdateTime(date);
				//总功率，总电能（实时）
				//String measuerNames = "ThreePhaseActivePower,ReverseActiveP";
				AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(ELECTRIC_METER, baseInfo.getId());  
			 
				List<AnalogStructureData> rslist = datas.getData();
				for(int j=0;j<rslist.size();j++){
//					if(Integer.valueOf(baseInfo.getMeterType()+"")==flag){
						if(rslist.get(j).getMeasuerName().equals("GenerationDaily")){
							AmmeterInfoVO.setGenerationDaily(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("GenerationMonth")){
							AmmeterInfoVO.setGenerationMonth(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("GenerationYear")){
							AmmeterInfoVO.setGenerationYear(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("PositiveActiveP")){
							AmmeterInfoVO.setGenerationGross(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationDaily")){
							AmmeterInfoVO.setUnifiedPowerGenerationDaily(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationMonth")){
							AmmeterInfoVO.setUnifiedPowerGenerationMonth(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationYear")){
							AmmeterInfoVO.setUnifiedPowerGenerationYear(rslist.get(j).getValue());
						}else if(rslist.get(j).getMeasuerName().equals("UnifiedPositiveActiveP")){
							AmmeterInfoVO.setUnifiedPositiveActiveP(rslist.get(j).getValue());
						}
//					}
				}
				result.add(AmmeterInfoVO);
			}
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/**
	 * 获取逆变器基本信息列表（不包含实时数据）
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getInverterBaseInfo/{powerStationId}")
	@ResponseBody
	public RestResponseVO getInverterBaseInfo(HttpServletRequest request,@PathVariable String powerStationId,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<InverterBaseInfoVO> list = powerStationService.getInverterBaseInfo(powerStationId);
			return RestResponse.responseOk(list);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 获取逆变器信息列表（包含实时数据）
	 * @param powerStationId 
	 * @return
	 */
	@RequestMapping(value="/getInverterInfo",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO getInverterInfo(HttpServletRequest request,@RequestBody Map<String,String>inverterstr,HttpServletResponse response){
		try{
			String powerStationId = inverterstr.get("powerStationId");
			String equipmentStatus = inverterstr.get("equipmentStatus");
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<InverterInfoVO> result = new ArrayList<InverterInfoVO>();
			//基本数据
			List<InverterBaseInfoVO> list = powerStationService.getInverterBaseInfo(powerStationId);
			int testCount = 0;
			for(int i=0;i<list.size();i++){
				InverterBaseInfoVO BaseInfo = list.get(i);
				InverterInfoVO InverterInfoVO = new InverterInfoVO();
				InverterInfoVO.setId(BaseInfo.getId());
				InverterInfoVO.setModel(BaseInfo.getModel());
				InverterInfoVO.setName(BaseInfo.getName());
				if(StringUtil.empty(BaseInfo.getSerialNumber())){
					InverterInfoVO.setSerialNumber(BaseInfo.getName());
				}else{
					InverterInfoVO.setSerialNumber(BaseInfo.getSerialNumber());
				}
				//月发电量、温度、交流功率、直流功率,状态,功率因数，日发电量
				AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(INVERTR_SERIES, BaseInfo.getId());  
				List<AnalogStructureData> rslist = datas.getData();
				for(int j=0;j<rslist.size();j++){
					if(rslist.get(j).getMeasuerName().equals("Temperature")){
						InverterInfoVO.setTemperature(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("GenerationMonth")){
						InverterInfoVO.setGenerationMonth(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("ThreePhaseActivePower")){
						InverterInfoVO.setThreePhaseActivePower(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("TotalInputPower")){
						InverterInfoVO.setTotalInputPower(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("EquipmentStatus")){
						InverterInfoVO.setEquipmentStatus(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("PowerFactor")){
						InverterInfoVO.setPowerFactor(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("GenerationDaily")){
						InverterInfoVO.setGenerationDaliy(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("GenerationYear")){
						InverterInfoVO.setGenerationYear(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("GenerationGross")){
						InverterInfoVO.setGenerationGross(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationDaily")){
						InverterInfoVO.setUnifiedPowerGenerationDaily(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationMonth")){
						InverterInfoVO.setUnifiedPowerGenerationMonth(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationYear")){
						InverterInfoVO.setUnifiedPowerGenerationYear(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("UnifiedGenerationGross")){
						InverterInfoVO.setUnifiedGenerationGross(rslist.get(j).getValue());
					}
				}
				if(StringUtil.empty(equipmentStatus)||Integer.valueOf(equipmentStatus)==0){//全部
					result.add(InverterInfoVO);
				}else if(Integer.valueOf(equipmentStatus)==1){//停机
					//TODO
					//if((int)(InverterInfoVO.getEquipmentStatus())==3||(int)(InverterInfoVO.getEquipmentStatus())==4){
					if(testCount<=5){
						result.add(InverterInfoVO);
					}else{
						break;
					}
					testCount++;
				}else if(Integer.valueOf(equipmentStatus)==2){//通讯异常
					if(testCount<=7){
						result.add(InverterInfoVO);
					}else{
						break;
					}
					testCount++;
				}
			}
			if(result.size()!=0){
				for(int h=0;h<result.size();h++){
					//报警
					InverterInfoVO itv = result.get(h);
					List<AlarmCountByUipmentVO>alarmsList = alarmInfoService.getAlarmCountByUipment(itv.getId(),3);
					int alarmCounts = 0;
					for(int m=0;m<alarmsList.size();m++){
						alarmCounts = alarmCounts+alarmsList.get(m).getCount();
					}
					itv.setAlarms(alarmCounts);
				}
			}
			return RestResponse.responseOk(result);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取逆变器信息列表（包含实时数据）
	 * @param powerStationId 
	 * @return
	 */
	public List<InverterInfoVO> getInverterDetail(String powerStationId){
		try{
			List<InverterInfoVO> result = new ArrayList<InverterInfoVO>();
			//基本数据
			List<InverterBaseInfoVO> list = powerStationService.getInverterBaseInfo(powerStationId);
			int testCount = 0;
			for(int i=0;i<list.size();i++){
				InverterBaseInfoVO BaseInfo = list.get(i);
				InverterInfoVO InverterInfoVO = new InverterInfoVO();
				InverterInfoVO.setId(BaseInfo.getId());
				InverterInfoVO.setModel(BaseInfo.getModel());
				InverterInfoVO.setName(BaseInfo.getName());
				if(StringUtil.empty(BaseInfo.getSerialNumber())){
					InverterInfoVO.setSerialNumber(BaseInfo.getName());
				}else{
					InverterInfoVO.setSerialNumber(BaseInfo.getSerialNumber());
				}
				//月发电量、温度、交流功率、直流功率,状态,功率因数，日发电量
				AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(INVERTR_SERIES, BaseInfo.getId());  
				List<AnalogStructureData> rslist = datas.getData();
				for(int j=0;j<rslist.size();j++){
					if(rslist.get(j).getMeasuerName().equals("Temperature")){
						InverterInfoVO.setTemperature(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("GenerationMonth")){
						InverterInfoVO.setGenerationMonth(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("ThreePhaseActivePower")){
						InverterInfoVO.setThreePhaseActivePower(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("TotalInputPower")){
						InverterInfoVO.setTotalInputPower(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("EquipmentStatus")){
						InverterInfoVO.setEquipmentStatus(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("PowerFactor")){
						InverterInfoVO.setPowerFactor(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("GenerationDaily")){
						InverterInfoVO.setGenerationDaliy(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("GenerationYear")){
						InverterInfoVO.setGenerationYear(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("GenerationGross")){
						InverterInfoVO.setGenerationGross(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationDaily")){
						InverterInfoVO.setUnifiedPowerGenerationDaily(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationMonth")){
						InverterInfoVO.setUnifiedPowerGenerationMonth(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("UnifiedPowerGenerationYear")){
						InverterInfoVO.setUnifiedPowerGenerationYear(rslist.get(j).getValue());
					}else if(rslist.get(j).getMeasuerName().equals("UnifiedGenerationGross")){
						InverterInfoVO.setUnifiedGenerationGross(rslist.get(j).getValue());
					}
				}
				result.add(InverterInfoVO);
			}
			if(result.size()!=0){
				for(int h=0;h<result.size();h++){
					//报警
					InverterInfoVO itv = result.get(h);
					List<AlarmCountByUipmentVO>alarmsList = alarmInfoService.getAlarmCountByUipment(itv.getId(),3);
					int alarmCounts = 0;
					for(int m=0;m<alarmsList.size();m++){
						alarmCounts = alarmCounts+alarmsList.get(m).getCount();
					}
					itv.setAlarms(alarmCounts);
				}
			}
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/**
	 * 获取电站详情信息
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/appPowerStationDetails/{powerStationId}")
	@ResponseBody
	public RestResponseVO AppPowerStationDetails(HttpServletRequest request,@PathVariable String powerStationId,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			PowerStationDetailsVO PowerStationDetailsVO = new PowerStationDetailsVO();
			PointHistoryVO[] arr = new PointHistoryVO[10];
			//电站发电功率,日发电量（实时）
			AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(POWER_STATION,Integer.valueOf(powerStationId));  
			List<AnalogStructureData> result = datas.getData();
			for(int i=0;i<result.size();i++){
				AnalogStructureData data = result.get(i);
				if(data.getMeasuerName().equals("GeneratedActivePower")){
					PowerStationDetailsVO.setGeneratedActivePower(data.getValue());
				}else if(data.getMeasuerName().equals("GenerationDaily")){
					PowerStationDetailsVO.setGenerationDaily(data.getValue());
					PointHistoryVO PointHistoryVO = new PointHistoryVO();
					PointHistoryVO.setTime(new Date());
					PointHistoryVO.setValue(data.getValue()); 
					arr[9]=PointHistoryVO;
				}else if(data.getMeasuerName().equals("GenerationGross")){
					PowerStationDetailsVO.setGenerationGross(data.getValue());
				}
			}
			//电厂名称,id,地址，经度，纬度，负责人
			PowerStationBaseInfoVO PowerStationBaseInfoVO = powerStationService.getPowerStationInfo(powerStationId);
			PowerStationDetailsVO.setName(PowerStationBaseInfoVO.getName());
			PowerStationDetailsVO.setId(PowerStationBaseInfoVO.getId());
			//装机容量
			PowerStationDetailsVO.setInstallCapacity(Double.valueOf(PowerStationBaseInfoVO.getInstallCapacity()+""));
			//pr
			RestResponseVO pr = getPRValue(request,powerStationId,response);
			PowerStationDetailsVO.setPr(Double.valueOf(pr.getData()+""));
			//温度，风速，组件温度，辐照度，风向，湿度 ，降雨量,累计辐照度
			RestResponseVO WeatherInfoData = getWeatherInfo(request,powerStationId,response);
			WeatherInfoVO WeatherInfoVO = (WeatherInfoVO) WeatherInfoData.getData();
			/**
			 * 各向辐射度
			 */
			PowerStationDetailsVO.setDirectIrradiance(WeatherInfoVO.getDirectIrradiance());
			PowerStationDetailsVO.setScateredIrradiance(WeatherInfoVO.getScateredIrradiance());
			PowerStationDetailsVO.setInclinedIrradiance(WeatherInfoVO.getInclinedIrradiance());
			PowerStationDetailsVO.setRadiantExposure(WeatherInfoVO.getRadiantExposure());
			PowerStationDetailsVO.setIrradiance(WeatherInfoVO.getIrradiance());
			//直辐射、散辐射、斜面辐射
			PowerStationDetailsVO.setDirectRadiation(WeatherInfoVO.getDirectRadiation());
			PowerStationDetailsVO.setScateredRadiation(WeatherInfoVO.getScateredRadiation());
			PowerStationDetailsVO.setInclinedRadiation(WeatherInfoVO.getInclinedIrradiance());
			//功率曲线（今天零点到当前时刻点的）
			PointInfoPO pointInfo2 = pointInfoService.getPointInfo("GeneratedActivePower", 0, Integer.valueOf(powerStationId));
			PointInfoPO pointInfo3 = pointInfoService.getPointInfo("GenerationDaily", 0, Integer.valueOf(powerStationId));
			if(pointInfo2!=null){
				PowerStationDetailsVO.setPowerId(pointInfo2.getId());
			}
			if(pointInfo3!=null){
				PowerStationDetailsVO.setGenerationDailyId(pointInfo3.getId());
			}
			return RestResponse.responseOk(PowerStationDetailsVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取电站详情信息
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/powerStationDetails/{powerStationId}")
	@ResponseBody
	public RestResponseVO PowerStationDetails(HttpServletRequest request,@PathVariable String powerStationId,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			PowerStationDetailsVO PowerStationDetailsVO = new PowerStationDetailsVO();
			PointHistoryVO[] arr = new PointHistoryVO[10];
			//电站发电功率,日发电量（实时）
			AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(POWER_STATION,Integer.valueOf(powerStationId));  
			List<AnalogStructureData> result = datas.getData();
			for(int i=0;i<result.size();i++){
				AnalogStructureData data = result.get(i);
				if(data.getMeasuerName().equals("GeneratedActivePower")){
					PowerStationDetailsVO.setGeneratedActivePower(data.getValue());
				}else if(data.getMeasuerName().equals("GenerationDaily")){
					PowerStationDetailsVO.setGenerationDaily(data.getValue());
					PointHistoryVO PointHistoryVO = new PointHistoryVO();
					PointHistoryVO.setTime(new Date());
					PointHistoryVO.setValue(data.getValue()); 
					arr[9]=PointHistoryVO;
				}else if(data.getMeasuerName().equals("GenerationMonth")){
					PowerStationDetailsVO.setGenerationMonth(data.getValue());
				}else if(data.getMeasuerName().equals("GenerationYear")){
					PowerStationDetailsVO.setGenerationYear(data.getValue());
				}else if(data.getMeasuerName().equals("GenerationGross")){
					PowerStationDetailsVO.setGenerationGross(data.getValue());
				}
			}
			//电厂名称,id,地址，经度，纬度，负责人
			PowerStationBaseInfoVO PowerStationBaseInfoVO = powerStationService.getPowerStationInfo(powerStationId);
			PowerStationDetailsVO.setName(PowerStationBaseInfoVO.getName());
			PowerStationDetailsVO.setId(PowerStationBaseInfoVO.getId());
			PowerStationDetailsVO.setAddress(PowerStationBaseInfoVO.getAddress());
			PowerStationDetailsVO.setLat(Double.valueOf((PowerStationBaseInfoVO.getLat()==null?0:PowerStationBaseInfoVO.getLat())+""));
			PowerStationDetailsVO.setLng(Double.valueOf((PowerStationBaseInfoVO.getLng()==null?0:PowerStationBaseInfoVO.getLng())+""));
			PowerStationDetailsVO.setManager(PowerStationBaseInfoVO.getManager());
			PowerStationDetailsVO.setManagerMobile(PowerStationBaseInfoVO.getManagerMobile());
			PowerStationDetailsVO.setMonitoring(PowerStationBaseInfoVO.getMonitoring());
			Date now = new Date();
			Date runtime = PowerStationBaseInfoVO.getStartPruduceTime();
			int days = 0;
			if(runtime!=null){
				days= (int) ((now.getTime()-runtime.getTime())/86400000);
			}
			PowerStationDetailsVO.setRunDays(days);
			PowerStationDetailsVO.setRunTime(runtime);
			//装机容量
			PowerStationDetailsVO.setInstallCapacity(Double.valueOf(PowerStationBaseInfoVO.getInstallCapacity()+""));
			//pr
			RestResponseVO pr = getPRValue(request,powerStationId,response);
			PowerStationDetailsVO.setPr(Double.valueOf(pr.getData()+""));
			//温度，风速，组件温度，辐照度，风向，湿度 ，降雨量,累计辐照度
			RestResponseVO WeatherInfoData = getWeatherInfo(request,powerStationId,response);
			WeatherInfoVO WeatherInfoVO = (WeatherInfoVO) WeatherInfoData.getData();
			PowerStationDetailsVO.setTemperature(WeatherInfoVO.getTemperature());
			PowerStationDetailsVO.setWindSpeed(WeatherInfoVO.getWindSpeed());
			PowerStationDetailsVO.setTemperatureofModules(WeatherInfoVO.getTemperatureofModules());
			PowerStationDetailsVO.setHumidity(WeatherInfoVO.getHumidity());
			PowerStationDetailsVO.setWindDirection(WeatherInfoVO.getWindDirection());
			PowerStationDetailsVO.setIrradiance(WeatherInfoVO.getIrradiance());
			/**
			 * 各向辐射度
			 */
			PowerStationDetailsVO.setDirectIrradiance(WeatherInfoVO.getDirectIrradiance());
			PowerStationDetailsVO.setScateredIrradiance(WeatherInfoVO.getScateredIrradiance());
			PowerStationDetailsVO.setInclinedIrradiance(WeatherInfoVO.getInclinedIrradiance());
			PowerStationDetailsVO.setRainFall(WeatherInfoVO.getRainFall());
			PowerStationDetailsVO.setRadiantExposure(WeatherInfoVO.getRadiantExposure());
			//直辐射、散辐射、斜面辐射
			PowerStationDetailsVO.setDirectRadiation(WeatherInfoVO.getDirectRadiation());
			PowerStationDetailsVO.setScateredRadiation(WeatherInfoVO.getScateredRadiation());
			PowerStationDetailsVO.setInclinedRadiation(WeatherInfoVO.getInclinedIrradiance());
			//通讯异常，停机，总数，故障运行
			InverterTypeCounts InverterTypeCounts = (InverterTypeCounts) getStopCommunicationAnomalyCounts(powerStationId).getData();
			PowerStationDetailsVO.setCommunicationAnomaly(InverterTypeCounts.getException());
			PowerStationDetailsVO.setStop(InverterTypeCounts.getAbNormalstop()+InverterTypeCounts.getNormalstop());
			PowerStationDetailsVO.setInverterCounts(InverterTypeCounts.getTotal());
			PowerStationDetailsVO.setFaultOperation(InverterTypeCounts.getFaultOperation());
			//总报警次数、实际故障次数故障排除比例、
			Counts Counts = alarmInfoService.getCounts(Integer.valueOf(powerStationId));
			PowerStationDetailsVO.setTotalAlarmsCounts(Counts.getAlarmSum());
			PowerStationDetailsVO.setFaultCounts(Counts.getFaultSum());
			PowerStationDetailsVO.setFaultSaved(Counts.getFaultProcess());
			//体检次数、
			PowerStationDetailsVO.setPhysicalExaminationCounts(
					workOrderInfoService.physicalTimes(Integer.valueOf(powerStationId)));
			//最后一次体检时间
			Date lastDate = workOrderInfoService.physicalTime(Integer.valueOf(powerStationId));
			if(lastDate==null){
				PowerStationDetailsVO.setLastPhysicalExaminationTime(TimeUtil.string2Date("1970-01-01"));
			}else{
				PowerStationDetailsVO.setLastPhysicalExaminationTime(lastDate);
			}
			//日发电量（今天之前9天记录）
			Date date = new Date();
			long time = 86400000;
			long startdate = date.getTime()-(time*9);
			long enddate = date.getTime()-time;
			
			String startTime = TimeUtil.date2String(new Date(startdate), "yyyy-MM-dd");
			String endTime = TimeUtil.date2String(new Date(enddate), "yyyy-MM-dd");
			startTime = startTime+" 00:00";
			endTime = endTime+" 23:59";
			PointInfoPO pointInfo = pointInfoService.getPointInfo("GenerationDaily", 0, Integer.valueOf(powerStationId));
			if(pointInfo!=null){
				MonitorHistoryPointData data = stationMonitorService.getMonitorHistoryData(pointInfo.getId(), TimeUtil.string2Date(startTime,"yyyy-MM-dd HH:mm"), 
						TimeUtil.string2Date(endTime,"yyyy-MM-dd HH:mm"), true);
				List<MonitorHistoryData> Generationrslist = data.getHistoryDatas();
				if(Generationrslist.size()!=0){
					int ii =0;
					for(int j = 0;j<Generationrslist.size();j++){
						MonitorHistoryData HistoryData = Generationrslist.get(j);
						if(HistoryData.getTime()==0){
							continue;
						}
						String datatime = TimeUtil.date2String(new Date(HistoryData.getTime()), "yyyy-MM-dd");
						Date ddat = TimeUtil.string2Date(datatime);
						if(arr[ii]==null){
							Boolean flag = false;
							for(int aa=0;aa<arr.length;aa++){
								if(arr[aa]!=null){
									if(TimeUtil.date2String(arr[aa].getTime(), "yyyy-MM-dd").equals(TimeUtil.date2String(ddat, "yyyy-MM-dd"))){
										flag = true;
									}
								}
							}
							if(flag){
								continue;
							}else{
								arr[ii] = new PointHistoryVO();
								arr[ii].setTime(ddat);
								arr[ii].setValue(HistoryData.getValue());
							}
						}else{
							if(arr[ii].getTime()==ddat){
								arr[ii].setValue(HistoryData.getValue());
							}else{
								ii++;
								if(ii==9){
									break;
								}
								if(arr[ii]==null){
									Boolean flag = false;
									for(int aa=0;aa<arr.length;aa++){
										if(arr[aa]!=null){
											if(TimeUtil.date2String(arr[aa].getTime(), "yyyy-MM-dd").equals(TimeUtil.date2String(ddat, "yyyy-MM-dd"))){
												flag = true;
											}
										}
									}
									if(flag){
										continue;
									}else{
										arr[ii] = new PointHistoryVO();
										arr[ii].setTime(ddat);
										arr[ii].setValue(HistoryData.getValue());
									}
								}
							}
						}
					} 
				}else{
					//假数据
					Date testdate = new Date();
					testdate = TimeUtil.string2Date(TimeUtil.date2String(testdate, "yyyy-MM-dd"));
					for(int ii=9;ii>0;ii--){
						if(arr[ii-1]==null){
							arr[ii-1] = new PointHistoryVO();
						}
						arr[ii-1].setTime(TimeUtil.addDate(testdate,(0-(10-ii))));
						arr[ii-1].setValue(0);
					}
				}
			}
			PowerStationDetailsVO.setGenerationDailyList(arr);
			//功率曲线（今天零点到当前时刻点的）
			PointInfoPO pointInfo2 = pointInfoService.getPointInfo("GeneratedActivePower", 0, Integer.valueOf(powerStationId));
			PointInfoPO pointInfo3 = pointInfoService.getPointInfo("GenerationDaily", 0, Integer.valueOf(powerStationId));
			if(pointInfo2!=null){
				PowerStationDetailsVO.setPowerId(pointInfo2.getId());
			}
			if(pointInfo3!=null){
				PowerStationDetailsVO.setGenerationDailyId(pointInfo3.getId());
			}
			return RestResponse.responseOk(PowerStationDetailsVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 获取电站详情信息
	 * @param powerStationId
	 * @return
	 */
	public PowerStationDetailsVO PowerStationDetail(String powerStationId){
		try{
			PowerStationDetailsVO PowerStationDetailsVO = new PowerStationDetailsVO();
			PointHistoryVO[] arr = new PointHistoryVO[10];
			//电站发电功率,日发电量（实时）
			AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(POWER_STATION,Integer.valueOf(powerStationId));  
			List<AnalogStructureData> result = datas.getData();
			for(int i=0;i<result.size();i++){
				AnalogStructureData data = result.get(i);
				if(data.getMeasuerName().equals("GeneratedActivePower")){
					PowerStationDetailsVO.setGeneratedActivePower(data.getValue());
				}else if(data.getMeasuerName().equals("GenerationDaily")){
					PowerStationDetailsVO.setGenerationDaily(data.getValue());
					PointHistoryVO PointHistoryVO = new PointHistoryVO();
					PointHistoryVO.setTime(new Date());
					PointHistoryVO.setValue(data.getValue()); 
					arr[9]=PointHistoryVO;
				}else if(data.getMeasuerName().equals("GenerationMonth")){
					PowerStationDetailsVO.setGenerationMonth(data.getValue());
				}else if(data.getMeasuerName().equals("GenerationYear")){
					PowerStationDetailsVO.setGenerationYear(data.getValue());
				}else if(data.getMeasuerName().equals("GenerationGross")){
					PowerStationDetailsVO.setGenerationGross(data.getValue());
				}
			}
			return PowerStationDetailsVO;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	/**
	 * 获取指定id电站的系统效率PR值
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getPRValue/{powerStationId}")
	@ResponseBody
	public RestResponseVO getPRValue(HttpServletRequest request,@PathVariable String powerStationId,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			PowerStationBaseInfoVO PowerStationBaseInfoVO = powerStationService.getPowerStationInfo(powerStationId);
			double InstallCapacity = Double.valueOf(PowerStationBaseInfoVO.getInstallCapacity()+"");
			RestResponseVO WeatherInfoData = getWeatherInfo(request,powerStationId,response);
			WeatherInfoVO WeatherInfoVO = (WeatherInfoVO) WeatherInfoData.getData();
			double RadiantExposure = WeatherInfoVO.getRadiantExposure();
			//辐照度,日发电量（实时）
			AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(POWER_STATION,Integer.valueOf(powerStationId));
			List<AnalogStructureData> result = datas.getData();
			double Efficiency = 0;
			for(int i=0;i<result.size();i++){
				AnalogStructureData data = result.get(i);
				if(data.getMeasuerName().equals("Efficiency")){
					Efficiency = data.getValue();
				}
			}
			double Pr = Efficiency*100;
			return RestResponse.responseOk(Pr);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 获取指定id电站逆变器的通讯异常数量、停机数、逆变器总数、故障运行数 
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getStopCommunicationAnomalyCounts/{powerStationId}")
	@ResponseBody
	public RestResponseVO getStopCommunicationAnomalyCounts(@PathVariable String powerStationId){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<InverterBaseInfoVO> Inverters = powerStationService.getInverterBaseInfo(powerStationId);
			InverterTypeCounts InverterTypeCounts = new InverterTypeCounts();
			int normalstop =0;
			int abNormalstop =0;
			int exception =0;
			int total = 0;
			int faultOperation = 0;
			int run = 0;
			int other = 0;
			for(int i =0;i<Inverters.size();i++){
				InverterBaseInfoVO InverterBaseInfoVO = Inverters.get(i);
				//通讯异常数量、停机数（实时）
				String measuerNames = "EquipmentStatus";
				AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(INVERTR_SERIES,InverterBaseInfoVO.getId());  
				List<AnalogStructureData> results = datas.getData();
				for(AnalogStructureData data:results){
					if(data.getMeasuerName().equals(measuerNames)){
						if((int)(data.getValue())==3){//3正常停机
							normalstop++;
						}else if((int)(data.getValue())==5){//通讯中断
							exception++;
						}else if((int)(data.getValue())==2){//故障运行
							faultOperation++;
						}else if((int)(data.getValue())==4){//4异常停机
							abNormalstop++;
						}else if((int)(data.getValue())==1){//1正常运行
							run++;
						}else if((int)(data.getValue())==6){//6其他
							other++;
						}
					} 
				}
				
				total++;
			}
			InverterTypeCounts.setException(exception);
			InverterTypeCounts.setNormalstop(normalstop);
			InverterTypeCounts.setAbNormalstop(abNormalstop);
			InverterTypeCounts.setTotal(total);
			InverterTypeCounts.setFaultOperation(faultOperation);
			InverterTypeCounts.setOther(other);
			InverterTypeCounts.setRun(run);
			return RestResponse.responseOk(InverterTypeCounts);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	 
	/**
	 * 获取指定电站气象站数据
	 * @param powerStationId
	 * @return
	 */
	@RequestMapping(value="/getWeatherInfo/{powerStationId}")
	@ResponseBody
	public RestResponseVO getWeatherInfo(HttpServletRequest request,@PathVariable String powerStationId,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			WeatherStationPO WeatherStationPO = weatherStationService.getWeatherStationInfo(powerStationId); 
			WeatherInfoVO WeatherInfoVO = new WeatherInfoVO();
			if(WeatherStationPO.getId()!=null){
				AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(ENVIRONMENT_MONITOR,WeatherStationPO.getId());  
				List<AnalogStructureData> results = datas.getData();
				
				for(int i=0;i<results.size();i++){
					AnalogStructureData data = results.get(i);
					double value = data.getValue();
					switch(data.getMeasuerName()){
					case "Temperature":
						WeatherInfoVO.setTemperature(value);
						break;
					case "RadiantExposure":
						WeatherInfoVO.setRadiantExposure(value);
						break;
					case "TemperatureofModules":
						WeatherInfoVO.setTemperatureofModules(value);
						break;
					case "humidity":
						WeatherInfoVO.setHumidity(value);
						break;
					case "Pressure":
						WeatherInfoVO.setPressure(value);
						break;
					case "WindSpeed":
						WeatherInfoVO.setWindSpeed(value);
						break;
					case "WindDirection":
						WeatherInfoVO.setWindDirection(value);
						break;
					case "Irradiance":
						WeatherInfoVO.setIrradiance(value);
						break;
					case "SunshineHours":
						WeatherInfoVO.setSunshineHours(value);
						break;
					case "DirectDailyAccumulation":
						WeatherInfoVO.setDirectDailyAccumulation(value);
						break;
					case "ScatteredDailyCumulative":
						WeatherInfoVO.setScatteredDailyCumulative(value);
						break;
					case "MinuteWindSpeed2":
						WeatherInfoVO.setMinuteWindSpeed2(value);
						break;
					case "MinuteWindSpeed10":
						WeatherInfoVO.setMinuteWindSpeed10(value);
						break;
					case "DirectExposure":
						WeatherInfoVO.setDirectRadiation(value);
						break;
					case "ScateredExposure":
						WeatherInfoVO.setScateredRadiation(value);
						break;
					case "CollectorPower":
						WeatherInfoVO.setCollectorPower(value);
						break;
					case "Rainfall":
						WeatherInfoVO.setRainFall(value);
						break;
					case "InclinedIrradiance":
						WeatherInfoVO.setInclinedIrradiance(value);
						break;
					case "InclinedExposure":
						WeatherInfoVO.setInclinedExposure(value);
						break;
					case "DirectIrradiance":
						WeatherInfoVO.setDirectIrradiance(value);
						break;
					case "ScateredIrradiance":
						WeatherInfoVO.setScateredIrradiance(value);
						break;
					//日辐射量
					case "ScatteredExposure":
						WeatherInfoVO.setScatteredExposure(value);
						break;
					//月辐照量
					case "RadiantExposureMonth":
						WeatherInfoVO.setRadiantExposureMonth(value);
						break;
					case "DirectExposureMonth":
						WeatherInfoVO.setDirectExposureMonth(value);
						break;
					case "ScateredExposureMonth":
						WeatherInfoVO.setScateredExposureMonth(value);
						break;
					case "InclinedExposureMonth":
						WeatherInfoVO.setInclinedExposureMonth(value);
						break;
						
					//年辐照量
					case "RadiantExposureYear":
						WeatherInfoVO.setRadiantExposureYear(value);
						break;
					case "DirectExposureYear":
						WeatherInfoVO.setDirectExposureYear(value);
						break;
					case "ScateredExposureYear":
						WeatherInfoVO.setScateredExposureYear(value);
						break;
					case "InclinedExposureYear":
						WeatherInfoVO.setInclinedExposureYear(value);
						break;
					default:
						break;
					}
				}
			}
			return RestResponse.responseOk(WeatherInfoVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 获取指定电站气象站数据
	 * @param powerStationId
	 * @return
	 */
	public WeatherInfoVO getWeatherDetail(String powerStationId){
		try{
			WeatherStationPO WeatherStationPO = weatherStationService.getWeatherStationInfo(powerStationId); 
			WeatherInfoVO WeatherInfoVO = new WeatherInfoVO();
			if(WeatherStationPO.getId()!=null){
				AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(ENVIRONMENT_MONITOR,WeatherStationPO.getId());  
				List<AnalogStructureData> results = datas.getData();
				
				for(int i=0;i<results.size();i++){
					AnalogStructureData data = results.get(i);
					double value = data.getValue();
					switch(data.getMeasuerName()){
					case "Temperature":
						WeatherInfoVO.setTemperature(value);
						break;
					case "RadiantExposure":
						WeatherInfoVO.setRadiantExposure(value);
						break;
					case "DirectExposure":
						WeatherInfoVO.setDirectRadiation(value);
						break;
					case "ScatteredExposure":
						WeatherInfoVO.setScateredRadiation(value);
						break;
					case "RainFall":
						WeatherInfoVO.setRainFall(value);
						break;
					case "InclinedIrradiance":
						WeatherInfoVO.setInclinedIrradiance(value);
						break;
					case "InclinedExposure":
						WeatherInfoVO.setInclinedExposure(value);
						break;
					case "DirectIrradiance":
						WeatherInfoVO.setDirectIrradiance(value);
						break;
					case "ScateredIrradiance":
						WeatherInfoVO.setScateredIrradiance(value);
						break;
					case "RadiantExposureMonth":
						WeatherInfoVO.setRadiantExposureMonth(value);
						break;
					case "InclinedExposureMonth":
						WeatherInfoVO.setInclinedExposureMonth(value);
						break;
					case "DirectExposureMonth":
						WeatherInfoVO.setDirectExposureMonth(value);
						break;
					case "ScateredExposureMonth":
						WeatherInfoVO.setScateredExposureMonth(value);
						break;
					case "RadiantExposureTotal":
						WeatherInfoVO.setRadiantExposureTotal(value);
						break;
					case "InclinedExposureTotal":
						WeatherInfoVO.setInclinedExposureTotal(value);
						break;
						
					default:
						break;
					}
				}
			}
			return WeatherInfoVO;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/**
	 * 获取电站的所有设备类型
	 * @param powerStationId
	 * @return 
	 */
	@RequestMapping(value="/getEquipmentType/{powerStationId}")
	@ResponseBody
	public RestResponseVO getEquipmentType(HttpServletRequest request,@PathVariable String powerStationId,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<EquipmentTypeVO> list = powerStationService.getEquipmentsList(Integer.valueOf(powerStationId));
//			EquipmentTypeVO inverter = new EquipmentTypeVO();
//			inverter.setId(3);
//			inverter.setName("逆变器");
//			list.add(inverter);
//			EquipmentTypeVO ammeter = new EquipmentTypeVO();
//			ammeter.setId(5);
//			ammeter.setName("电表");
//			list.add(ammeter);
			return RestResponse.responseOk(list);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:addPowerstation
	 * @Description:TODO(保存电站信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/addPowerstation")
	@ResponseBody
	public Object addPowerstation(@RequestBody PowerstationDTO param,HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 UserVO user = TokenUtil.getLoginUser();
			 if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 param.setCompanyId(user.getCompanyId());
			 logger.info("开始保存电站信息");
			 powerStationService.insertPowerstation(param);
			 return RestResponse.responseOk(true);
		} catch (Exception e) {
			 logger.error(e.getMessage(),e);
			 return RestResponse.responseError();
		}
	}
//	
	@RequestMapping(value="/getPowerstationList")
	@ResponseBody
	public Object getPowerstationList(Integer page,Integer pagesize,HttpServletRequest request,HttpServletResponse response,String key){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 UserVO user = TokenUtil.getLoginUser();
			 if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			} 
			ResultListVO<PowerStationListVO> result = powerStationService.getPowerstationList(user.getCompanyId(),key,Integer.valueOf(page),Integer.valueOf(pagesize));
			
			return RestResponse.responseList(request, result.getCounts(), result.getResultList()); 
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * @Title:delPowerStation
	 * @Description:TODO(删除单个电站 根据id) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/delPowerStation/{id}")
	@ResponseBody
	public Object delPowerStation(@PathVariable Integer id,HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 UserVO user = TokenUtil.getLoginUser();
			 if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			if(id==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "电站id为空！");
			}
			RoleAndPowerPO param =  new RoleAndPowerPO();
			param.setPowerstationId(id);
			List<RoleAndPowerPO> roleAndPowerList = roleAndPowerService.getRoleAndPower(param);
			if(roleAndPowerList.size()>0){
				for(RoleAndPowerPO rp : roleAndPowerList){
					List<UserAndRolePO> userAndRoleList = userAndRoleService.getRoleAndUser(rp.getRoleId());
					if(userAndRoleList.size()>0){
						return RestResponse.responseOk(false);
					}
				}
			}
			powerStationService.delPowerstation(id);
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
		
	}
	
	/**
	 * @Title:delPowerStation
	 * @Description:TODO(删除单个电站 根据id) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/updateStatus/{id}/{effectivity}")
	@ResponseBody
	public Object updateStatus(@PathVariable Integer id,@PathVariable Integer effectivity,HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 UserVO user = TokenUtil.getLoginUser();
			 if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			if(id==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "电站id为空！");
			}
			powerStationService.updateStatus(id,effectivity);
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
		
	}
}
