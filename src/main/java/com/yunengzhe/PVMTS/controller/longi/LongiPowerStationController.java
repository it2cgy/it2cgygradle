package com.yunengzhe.PVMTS.controller.longi;

import java.util.ArrayList;
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

import com.yunengzhe.PVMTS.domain.po.InverterToMeterPO;
import com.yunengzhe.PVMTS.domain.po.LogsInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.AnalogStructureResults;
import com.yunengzhe.PVMTS.domain.vo.InverterBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.longi.AmmeterVO;
import com.yunengzhe.PVMTS.domain.vo.longi.EquipmentCounts;
import com.yunengzhe.PVMTS.domain.vo.longi.InverterToMeterVO;
import com.yunengzhe.PVMTS.domain.vo.longi.InverterVO;
import com.yunengzhe.PVMTS.domain.vo.longi.MeasurementBoxAndInverterVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureData;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureDatas;
import com.yunengzhe.PVMTS.domain.vo.monitor.EquipPoints;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorRealData;
import com.yunengzhe.PVMTS.service.AlarmInfoService;
import com.yunengzhe.PVMTS.service.AmmeterService;
import com.yunengzhe.PVMTS.service.InverterService;
import com.yunengzhe.PVMTS.service.InverterToMeterService;
import com.yunengzhe.PVMTS.service.MeasurementBoxService;
import com.yunengzhe.PVMTS.service.PointCacheService;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.service.ScadaService;
import com.yunengzhe.PVMTS.service.WeatherStationService;
import com.yunengzhe.PVMTS.service.WorkOrderInfoService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.PointUtil;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.util.CacheManUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/longiPowerStation")
public class LongiPowerStationController {
	private static final Logger logger = LoggerFactory.getLogger(LongiPowerStationController.class);
	
	@Autowired
	private PowerStationService powerStationService;
	@Autowired
	public ScadaService scadaService;
	@Autowired
	private MeasurementBoxService measurementBoxService;
	@Autowired
	private InverterToMeterService inverterToMeterService;
	@Autowired
	public StationMonitorService stationMonitorService; 
	@Autowired
	private InverterService inverterService;
	@Autowired
	private AmmeterService ammeterService;
	
	/**
	 * 获取逆变器信息列表（包含实时数据）
	 * @param powerStationId 
	 * @return
	 */
	@RequestMapping(value="/getInverterInfo",method=RequestMethod.POST)
	@ResponseBody
	public Object getInverterInfo(HttpServletRequest request,@RequestBody Map<String,String>inverterstr,HttpServletResponse response){
		try{
			String powerStationId = inverterstr.get("powerStationId");
			Integer page = Integer.valueOf(inverterstr.get("page"));
			Integer pagesize = Integer.valueOf(inverterstr.get("pagesize"));
			String inverterId = inverterstr.get("inverterId");
			String isThirdUser = inverterstr.get("isThirdUser");
			
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<InverterVO> result = new ArrayList<InverterVO>();
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			//是否是第三方用户
			int thirdUserid = 0;
			if("1".equals(isThirdUser)){
				thirdUserid = user.getUserid();
			}
			//获取分页逆变器列表
			ResultListVO resultListVO = powerStationService.getInverterPageInfo(powerStationId,page,pagesize,inverterId,thirdUserid,null);
			List<InverterBaseInfoVO> inverterlist = resultListVO.getResultList();
			if(inverterlist.size()==0){
				return RestResponse.responseList(request, 0, inverterlist);
			}
			//获取分页后逆变器对应测量箱
			String ids="";
			for(int i=0;i<inverterlist.size();i++){
				if(i==0){
					ids+=inverterlist.get(i).getId();
				}else{
					ids+=","+inverterlist.get(i).getId();
				}
			}
			List<MeasurementBoxAndInverterVO> measureInverter = measurementBoxService.getMeasureBoxInverter(ids);
			String local = (String)HttpSessionUtil.getAttribute("local");
			for(int j=0;j<measureInverter.size();j++){
				MeasurementBoxAndInverterVO measurementBoxAndInverterVO = measureInverter.get(j);
				InverterVO inverterVO = new InverterVO();
				inverterVO.setId(measurementBoxAndInverterVO.getInverterId());
				inverterVO.setMeasurementBoxName(measurementBoxAndInverterVO.getName());
				inverterVO.setMeasurementBoxSerialNumber(measurementBoxAndInverterVO.getName());
				if("en_US".equals(local)){
					inverterVO.setMeasurementBoxName(measurementBoxAndInverterVO.getEnglishname());
					inverterVO.setMeasurementBoxSerialNumber(measurementBoxAndInverterVO.getEnglishname());
				}
				inverterVO.setName(measurementBoxAndInverterVO.getInverterName());
				inverterVO.setSerialNumber(measurementBoxAndInverterVO.getInverterSerialNumber());
				inverterVO.setMeasurementBoxId(measurementBoxAndInverterVO.getId());
				//获取逆变器的实时数据
				AnalogStructureDatas data = stationMonitorService.getMonitorEquipRealData(3,measurementBoxAndInverterVO.getInverterId());
				for(int k=0;k<data.getData().size();k++){
					AnalogStructureData analogStructureData = data.getData().get(k);
					if(analogStructureData.getMeasuerName().equals("Temperature")){
						inverterVO.setTemperature(analogStructureData.getValue());
					}
					if(analogStructureData.getMeasuerName().equals("PowerFactor")){
						inverterVO.setPowerFactor(analogStructureData.getValue());
					}
					if(analogStructureData.getMeasuerName().equals("ThreePhaseActivePower")){
						inverterVO.setThreePhaseActivePower(analogStructureData.getValue());
					}
					if(analogStructureData.getMeasuerName().equals("TotalInputPower")){
						inverterVO.setTotalInputPower(analogStructureData.getValue());
					}
					if(analogStructureData.getMeasuerName().equals("GenerationDaily")){ 
						inverterVO.setGenerationDaliy(analogStructureData.getValue());
					}
					if(analogStructureData.getMeasuerName().equals("EquipmentStatus")){
						inverterVO.setEquipmentStatus(analogStructureData.getValue());
					}
				}
				//获取电表信息
				List<InverterToMeterVO> meterlist = inverterToMeterService.getMeters(measurementBoxAndInverterVO.getInverterId());
				List<AmmeterVO> ammeterList = new ArrayList<AmmeterVO>();
				for(int m=0;m<meterlist.size();m++){
					InverterToMeterVO inverterToMeterVO = meterlist.get(m);
					AmmeterVO ammeterVO = new AmmeterVO();
					ammeterVO.setId(inverterToMeterVO.getElectricMeterId());
					ammeterVO.setModel(inverterToMeterVO.getMeterModel());
					ammeterVO.setName(inverterToMeterVO.getMeterName());
					ammeterVO.setSerialNumber(inverterToMeterVO.getMeterSerialNumber());
					ammeterVO.setAmmeterType(inverterToMeterVO.getMeterType());
					AnalogStructureDatas ammeterdata = stationMonitorService.getMonitorEquipRealData(5,inverterToMeterVO.getElectricMeterId());
					for(int g=0;g<ammeterdata.getData().size();g++){
						AnalogStructureData ammeterinfo = ammeterdata.getData().get(g);
						if(inverterToMeterVO.getMeterType()==1){//直流电表
							if(ammeterinfo.getMeasuerName().equals("GeneratedActivePower")){
								ammeterVO.setPower(ammeterinfo.getValue());
								continue;
							}
							if(ammeterinfo.getMeasuerName().equals("PositiveActiveP")){
								ammeterVO.setGenerationGross(ammeterinfo.getValue());
								continue;
							}
							if(ammeterinfo.getMeasuerName().equals("GenerationDaily")){
								ammeterVO.setGenerationDaliy(ammeterinfo.getValue());
								continue;
							}
						}else{//交流电表
							if(ammeterinfo.getMeasuerName().equals("ThreePhaseActivePower")){
								ammeterVO.setPower(ammeterinfo.getValue());
								continue;
							}
							if(ammeterinfo.getMeasuerName().equals("PositiveActiveP")){
								ammeterVO.setGenerationGross(ammeterinfo.getValue());
								continue;
							}
							if(ammeterinfo.getMeasuerName().equals("GenerationDaily")){
								ammeterVO.setGenerationDaliy(ammeterinfo.getValue());
								continue;
							}
						}
						
					}
					ammeterList.add(ammeterVO);
				}
				inverterVO.setAmmeterList(ammeterList);
				result.add(inverterVO);
			}
			if(result.size()==0){
				return RestResponse.responseList(request, 0, result);
			}else{
				return RestResponse.responseList(request, resultListVO.getCounts(), result);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 获取逆变器信息列表（包含实时数据）
	 * App获取电表箱列表
	 * @param powerStationId 
	 * @return
	 */
	@RequestMapping(value="/getMeasureBoxList",method=RequestMethod.POST)
	@ResponseBody
	public Object getMeasureBoxList(HttpServletRequest request,@RequestBody Map<String,String>inverterstr,HttpServletResponse response){
		try{
			String powerStationId = inverterstr.get("powerStationId");
			Integer page = Integer.valueOf(inverterstr.get("page"));
			Integer pagesize = Integer.valueOf(inverterstr.get("pagesize"));
			String inverterId = inverterstr.get("inverterId");
			String isThirdUser = inverterstr.get("isThirdUser");
			String seachName = inverterstr.get("seachName");
			
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<InverterVO> result = new ArrayList<InverterVO>();
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			//是否是第三方用户
			int thirdUserid = 0;
			if("1".equals(isThirdUser)){
				thirdUserid = user.getUserid();
			}
			//获取分页逆变器列表
			ResultListVO resultListVO = powerStationService.getInverterPageInfo(powerStationId,page,pagesize,inverterId,thirdUserid,null);
			List<InverterBaseInfoVO> inverterlist = resultListVO.getResultList();
			if(inverterlist.size()==0){
				return RestResponse.responseList(request, 0, inverterlist);
			}
			//获取分页后逆变器对应测量箱
			String ids="";
			for(int i=0;i<inverterlist.size();i++){
				if(i==0){
					ids+=inverterlist.get(i).getId();
				}else{
					ids+=","+inverterlist.get(i).getId();
				}
			}
			List<MeasurementBoxAndInverterVO> measureInverter = measurementBoxService.getMeasureBoxInverter(ids);
			for(int j=0;j<measureInverter.size();j++){
				MeasurementBoxAndInverterVO measurementBoxAndInverterVO = measureInverter.get(j);
				if(!measurementBoxAndInverterVO.getName().contains(seachName)){
					continue;
				}
				InverterVO inverterVO = new InverterVO();
				inverterVO.setId(measurementBoxAndInverterVO.getInverterId());
				inverterVO.setName(measurementBoxAndInverterVO.getInverterName());
				inverterVO.setSerialNumber(measurementBoxAndInverterVO.getInverterSerialNumber());
				inverterVO.setMeasurementBoxId(measurementBoxAndInverterVO.getId());
				inverterVO.setMeasurementBoxName(measurementBoxAndInverterVO.getName());
				inverterVO.setMeasurementBoxSerialNumber(measurementBoxAndInverterVO.getName());
				
				//获取电表信息
				List<InverterToMeterVO> meterlist = inverterToMeterService.getMeters(measurementBoxAndInverterVO.getInverterId());
				List<AmmeterVO> ammeterList = new ArrayList<AmmeterVO>();
				int drictmeter = 0;
				int threePhasemeter = 0;
				for(int m=0;m<meterlist.size();m++){
					InverterToMeterVO inverterToMeterVO = meterlist.get(m);
					AmmeterVO ammeterVO = new AmmeterVO();
					ammeterVO.setId(inverterToMeterVO.getElectricMeterId());
					ammeterVO.setModel(inverterToMeterVO.getMeterModel());
					ammeterVO.setName(inverterToMeterVO.getMeterName());
					ammeterVO.setSerialNumber(inverterToMeterVO.getMeterSerialNumber());
					ammeterVO.setAmmeterType(inverterToMeterVO.getMeterType());
					if(inverterToMeterVO.getMeterType()==1){
						drictmeter++;
					}else{
						threePhasemeter++;
					}
					ammeterList.add(ammeterVO);
				}
				inverterVO.setDrictmeterCounts(drictmeter);
				inverterVO.setThreePhasemeterCounts(threePhasemeter);
				inverterVO.setAmmeterList(ammeterList);
				result.add(inverterVO);
			}
			if(result.size()==0){
				return RestResponse.responseList(request, 0, result);
			}else{
				return RestResponse.responseList(request, resultListVO.getCounts(), result);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取逆变器信息列表（App包含实时数据）
	 * @param powerStationId 
	 * @return
	 */
	@RequestMapping(value="/getInverterBaseInfo",method=RequestMethod.POST)
	@ResponseBody
	public Object getInverterBaseInfo(HttpServletRequest request,@RequestBody Map<String,String>inverterstr,HttpServletResponse response){
		try{
			String powerStationId = inverterstr.get("powerStationId");
			Integer page = Integer.valueOf(inverterstr.get("page"));
			Integer pagesize = Integer.valueOf(inverterstr.get("pagesize"));
			String inverterId = inverterstr.get("inverterId");
			String isThirdUser = inverterstr.get("isThirdUser");
			String seachName = inverterstr.get("seachName");
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<InverterVO> result = new ArrayList<InverterVO>();
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			//是否是第三方用户
			int thirdUserid = 0;
			if("1".equals(isThirdUser)){
				thirdUserid = user.getUserid();
			}
			//获取分页逆变器列表
			ResultListVO resultListVO = powerStationService.getInverterPageInfo(powerStationId,page,pagesize,inverterId,thirdUserid,seachName);
			List<InverterBaseInfoVO> inverterlist = resultListVO.getResultList();
			if(inverterlist.size()==0){
				return RestResponse.responseList(request, 0, inverterlist);
			}
			//获取分页后逆变器对应测量箱
			String ids="";
			for(int i=0;i<inverterlist.size();i++){
				if(i==0){
					ids+=inverterlist.get(i).getId();
				}else{
					ids+=","+inverterlist.get(i).getId();
				}
			}
			List<MeasurementBoxAndInverterVO> measureInverter = measurementBoxService.getMeasureBoxInverter(ids);
			for(int j=0;j<measureInverter.size();j++){
				MeasurementBoxAndInverterVO measurementBoxAndInverterVO = measureInverter.get(j);
				InverterVO inverterVO = new InverterVO();
				inverterVO.setId(measurementBoxAndInverterVO.getInverterId());
				inverterVO.setName(measurementBoxAndInverterVO.getInverterName());
				inverterVO.setSerialNumber(measurementBoxAndInverterVO.getInverterSerialNumber());
				inverterVO.setMeasurementBoxId(measurementBoxAndInverterVO.getId());
				inverterVO.setMeasurementBoxName(measurementBoxAndInverterVO.getName());
				inverterVO.setMeasurementBoxSerialNumber(measurementBoxAndInverterVO.getName());
				//获取逆变器的实时数据
				AnalogStructureDatas data = stationMonitorService.getMonitorEquipRealData(3,measurementBoxAndInverterVO.getInverterId());
				for(int k=0;k<data.getData().size();k++){
					AnalogStructureData analogStructureData = data.getData().get(k);
					if(analogStructureData.getMeasuerName().equals("Temperature")){
						inverterVO.setTemperature(analogStructureData.getValue());
					}
					if(analogStructureData.getMeasuerName().equals("PowerFactor")){
						inverterVO.setPowerFactor(analogStructureData.getValue());
					}
					if(analogStructureData.getMeasuerName().equals("ThreePhaseActivePower")){
						inverterVO.setThreePhaseActivePower(analogStructureData.getValue());
					}
					if(analogStructureData.getMeasuerName().equals("TotalInputPower")){
						inverterVO.setTotalInputPower(analogStructureData.getValue());
					}
					if(analogStructureData.getMeasuerName().equals("GenerationDaily")){ 
						inverterVO.setGenerationDaliy(analogStructureData.getValue());
					}
					if(analogStructureData.getMeasuerName().equals("EquipmentStatus")){
						inverterVO.setEquipmentStatus(analogStructureData.getValue());
					}
				}
				result.add(inverterVO);
			}
			if(result.size()==0){
				return RestResponse.responseList(request, 0, result);
			}else{
				return RestResponse.responseList(request, resultListVO.getCounts(), result);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取指定电站下所有设备的数量
	 * @param request
	 * @param powerStationId
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getEquipmentCounts/{powerStationId}")
	@ResponseBody
	public RestResponseVO getEquipmentCounts(HttpServletRequest request,@PathVariable Integer powerStationId,HttpServletResponse response,String isThirdUser){
		try{
			
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			
			int thirdUserid = 0;
			if("1".equals(isThirdUser)){
				thirdUserid = user.getUserid();
			}
			
			Integer inverterCounts = inverterService.getInverterCounts(powerStationId,thirdUserid);
			Integer[] ammeter = ammeterService.getAmmeterCounts(powerStationId,thirdUserid);
			Integer measureBoxCounts = measurementBoxService.getMeasureBoxCounts(powerStationId,thirdUserid);
			EquipmentCounts equipmentCounts = new EquipmentCounts();
			equipmentCounts.setAmmeter1Counts(ammeter[0]);
			equipmentCounts.setAmmeter2Counts(ammeter[1]);
			equipmentCounts.setAmmeter3Counts(ammeter[2]);
			equipmentCounts.setInverterCounts(inverterCounts);
			equipmentCounts.setMeasureBoxCounts(measureBoxCounts);
			return RestResponse.responseOk(equipmentCounts);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
