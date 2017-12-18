package com.yunengzhe.PVMTS.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.yunengzhe.PVMTS.domain.dto.SearchMeansurePointDataDTO;
import com.yunengzhe.PVMTS.domain.po.AmmeterPO;
import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.AmmeterDetailsVO; 
import com.yunengzhe.PVMTS.domain.vo.AnalogStructureParam;
import com.yunengzhe.PVMTS.domain.vo.AnalogStructureResults;
import com.yunengzhe.PVMTS.domain.vo.HistoryData;
import com.yunengzhe.PVMTS.domain.vo.HistoryDataResult;
import com.yunengzhe.PVMTS.domain.vo.MeasurePointInfo;
import com.yunengzhe.PVMTS.domain.vo.QueryHisPointsParam;
import com.yunengzhe.PVMTS.domain.vo.QueryPointParam;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureData;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureDatas;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryData;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryPointData;
import com.yunengzhe.PVMTS.service.AmmeterService;
import com.yunengzhe.PVMTS.service.AnaloginputService;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.ScadaService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.common.util.TimeUtil;
/**
 * 
 * @author liuchaoshuai
 *
 */
@Controller
@RequestMapping("/ammeter")
public class AmmeterController {
	
	private static final Logger logger = LoggerFactory.getLogger(AmmeterController.class);
	
//	@Autowired
//	public ScadaService scadaService;
	@Autowired
	private AnaloginputService analoginputService;
	
	@Autowired
	public StationMonitorService stationMonitorService; 

	@Autowired
	private PointInfoService pointInfoService;
	
	@Autowired
	private AmmeterService ammeterService;
	/**
	 * 获取电表某测点的历史数据
	 * @param startTime
	 * @param endTime
	 * @param measurePointName
	 * @param AmmeterId
	 * @return
	 */
	@RequestMapping(value="/getAmmeterMeasurePointHistoryInfo",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO getAmmeterMeasurePointHistoryInfo(HttpServletResponse response,HttpServletRequest request,
			@RequestBody SearchMeansurePointDataDTO searchMeansurePointDataDTO){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try{
				List<HistoryDataResult> result  = new ArrayList<HistoryDataResult>();
				List<HistoryData> datas = new ArrayList<HistoryData>();
				HistoryDataResult res = new HistoryDataResult(); 
				res.setStartTime(searchMeansurePointDataDTO.getStartTime());
				res.setEndTime(searchMeansurePointDataDTO.getEndTime());
				res.setMeasuerName(searchMeansurePointDataDTO.getMeasurePointName());
				res.setEquipcontainerID(searchMeansurePointDataDTO.getEquipmentId());
				res.setEquipcontainerTableID("5");
				res.setSpan("0");
				res.setDateSpan("300");
				res.setResult(datas);

				result.add(res);
				PointInfoPO pointInfo = pointInfoService.getPointInfo("measurePointName", 5, Integer.valueOf(searchMeansurePointDataDTO.getEquipmentId()));
				if(pointInfo!=null){
					MonitorHistoryPointData data = stationMonitorService.getMonitorHistoryData(pointInfo.getId(), TimeUtil.string2Date(searchMeansurePointDataDTO.getStartTime(),"yyyy-MM-dd HH:mm:ss"), 
							TimeUtil.string2Date(searchMeansurePointDataDTO.getEndTime(),"yyyy-MM-dd HH:mm:ss"), false);
					List<MonitorHistoryData> hisdata = data.getHistoryDatas();
					for(MonitorHistoryData his:hisdata){
						HistoryData hd = new HistoryData();
						hd.setTime(TimeUtil.date2String(new Date(his.getTime()), "yyyy-MM-dd HH:mm:ss"));
						hd.setValue(""+his.getValue());
						datas.add(hd);
					}
					 
				} 
				return RestResponse.responseOk(result); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "错误请求-参数有误");
		}
	}
	
	/**
	 * 获取对应id电表的所有测点
	 * @param AmmeterId
	 * @return
	 */
	@RequestMapping(value="/getAmmeterMeasurePoints/{AmmeterId}")
	@ResponseBody
	public RestResponseVO getAmmeterMeasurePoints(HttpServletResponse response,HttpServletRequest request,@PathVariable String AmmeterId){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<MeasurePointInfo> list = analoginputService.getAmmeterMeasurePoints(AmmeterId);
			return RestResponse.responseOk(list);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 获取电表详情
	 * @param response
	 * @param AmmeterId
	 * @return
	 */
	@RequestMapping(value="/getAmmeterDetails/{AmmeterId}")
	@ResponseBody
	public RestResponseVO getAmmeterDetails(HttpServletResponse response,HttpServletRequest request,@PathVariable String AmmeterId){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			AmmeterPO ammeterPO = ammeterService.getAmmeterInfo(AmmeterId);
			AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(5,Integer.valueOf(AmmeterId));  
			List<AnalogStructureData> result = datas.getData();
			AmmeterDetailsVO AmmeterDetailsVO = new AmmeterDetailsVO();
			AmmeterDetailsVO.setName(ammeterPO.getName());
			AmmeterDetailsVO.setMeterType(Integer.valueOf(ammeterPO.getMeterType()+"")); 
			if(StringUtil.empty(ammeterPO.getSerialNumber())){
				AmmeterDetailsVO.setSerialNumber(ammeterPO.getName());
			}else{
				AmmeterDetailsVO.setSerialNumber(ammeterPO.getSerialNumber());
			}
			if(AmmeterDetailsVO.getMeterType()==1){
				//直流功率id
				PointInfoPO pointInfo = pointInfoService.getPointInfo("GeneratedActivePower", 5, Integer.valueOf(AmmeterId));
				if(pointInfo!=null){
					AmmeterDetailsVO.setPowerId(pointInfo.getId());
				}else{
					AmmeterDetailsVO.setPowerId(-1);
				}
			}else{
				//交流功率id
				PointInfoPO pointInfo = pointInfoService.getPointInfo("ThreePhaseActivePower", 5, Integer.valueOf(AmmeterId));
				if(pointInfo!=null){
					AmmeterDetailsVO.setPowerId(pointInfo.getId());
				}else{
					AmmeterDetailsVO.setPowerId(-1);
				}
			}
			PointInfoPO pointInfo2 = pointInfoService.getPointInfo("GenerationDaily", 5, Integer.valueOf(AmmeterId));
			PointInfoPO pointInfo3 = pointInfoService.getPointInfo("GenerationMonth", 5, Integer.valueOf(AmmeterId));
			PointInfoPO pointInfo4 = pointInfoService.getPointInfo("GenerationYear", 5, Integer.valueOf(AmmeterId));
			PointInfoPO pointInfo5 = pointInfoService.getPointInfo("PositiveActiveP", 5, Integer.valueOf(AmmeterId));
			AmmeterDetailsVO.setReverseActivePGrossId(pointInfo5.getId());
			AmmeterDetailsVO.setReverseActivePDailyId(pointInfo2.getId());
			AmmeterDetailsVO.setReverseActivePMonthId(pointInfo3.getId());
			AmmeterDetailsVO.setReverseActivePYearId(pointInfo4.getId());
			for(int i=0;i<result.size();i++){
				AnalogStructureData data = result.get(i);
				switch(data.getMeasuerName()){
				//日、月、累计发电量
				case "PhaseVoltageA":
					AmmeterDetailsVO.setPhaseVoltageA(data.getValue());
					break;
				case "PhaseVoltageB":
					AmmeterDetailsVO.setPhaseVoltageB(data.getValue());
					break;
				case "PhaseVoltageC":
					AmmeterDetailsVO.setPhaseVoltageC(data.getValue());
					break;
				case "PhaseCurrentA":
					AmmeterDetailsVO.setPhaseCurrentA(data.getValue());
					break;
				case "PhaseCurrentB":
					AmmeterDetailsVO.setPhaseCurrentB(data.getValue());
					break;
				case "PhaseCurrentC":
					AmmeterDetailsVO.setPhaseCurrentC(data.getValue());
					break;
				case "ActivePowerA":
					AmmeterDetailsVO.setActivePowerA(data.getValue());
					break;
				case "ActivePowerB":
					AmmeterDetailsVO.setActivePowerB(data.getValue());
					break;
				case "ActivePowerC":
					AmmeterDetailsVO.setActivePowerC(data.getValue());
					break;
				case "ThreePhaseActivePower":
					AmmeterDetailsVO.setThreePhaseActivePower(data.getValue());
					break;
				case "ThreePhaseReactivePower":
					AmmeterDetailsVO.setThreePhaseReactivePower(data.getValue());
					break;
				case "PowerFactor":
					AmmeterDetailsVO.setPowerFactor(data.getValue());
					break;
				case "Frequency":
					AmmeterDetailsVO.setFrequency(data.getValue());
					break;
				case "PositiveActiveFlatP":
					AmmeterDetailsVO.setPositiveActiveFlatP(data.getValue());
					break;
				case "PositiveActivePeakP":
					AmmeterDetailsVO.setPositiveActivePeakP(data.getValue());
					break;
				case "PositiveActiveValleyP":
					AmmeterDetailsVO.setPositiveActiveValleyP(data.getValue());
					break;
				case "PositiveActiveSharpP":
					AmmeterDetailsVO.setPositiveActiveSharpP(data.getValue());
					break;
				case "ReverseActiveFlatP":
					AmmeterDetailsVO.setReverseActiveFlatP(data.getValue());
					break;
				case "ReverseActivePeakP":
					AmmeterDetailsVO.setReverseActivePeakP(data.getValue());
					break;
				case "ReverseActiveValleyP":
					AmmeterDetailsVO.setReverseActiveValleyP(data.getValue());
					break;
				case "ReverseActiveSharpP":
					AmmeterDetailsVO.setReverseActiveSharpP(data.getValue());
					break;
				case "GenerationDaily"://当日正向有功总电能
					AmmeterDetailsVO.setPositiveActivePDaily(data.getValue());
					break;
				case "ReverseActivePDaily"://当日反向有功总电能
					AmmeterDetailsVO.setReverseActivePDaily(data.getValue());
					break;
				case "GenerationMonth"://当月正向有功总电能
					AmmeterDetailsVO.setPositiveActivePMonth(data.getValue());
					break;
				case "ReverseActivePMonth"://当月反向有功总电能
					AmmeterDetailsVO.setReverseActivePMonth(data.getValue());
					break;
				case "GenerationYear"://当年正向有功总电能
					AmmeterDetailsVO.setPositiveActivePYear(data.getValue());
					break;
				case "ReverseActivePYear"://当年反向有功总电能
					AmmeterDetailsVO.setReverseActivePYear(data.getValue());
					break;
				case "PositiveActiveP"://总正向有功总电能
					AmmeterDetailsVO.setPositiveActiveP(data.getValue());
					break;
				case "ReverseActiveP"://总反向有功总电能
					AmmeterDetailsVO.setReverseActiveP(data.getValue());
					break;
					/**
					 *三相功率 
					 */
				case "LookedAtEachOtherInPowerA"://视在
					AmmeterDetailsVO.setLookedAtEachOtherInPowerA(data.getValue());
					break;
				case "LookedAtEachOtherInPowerB"://视在
					AmmeterDetailsVO.setLookedAtEachOtherInPowerB(data.getValue());
					break;
				case "LookedAtEachOtherInPowerC"://视在
					AmmeterDetailsVO.setLookedAtEachOtherInPowerC(data.getValue());
					break;
				case "ReactivePowerA"://无功
					AmmeterDetailsVO.setReactivePowerA(data.getValue());
					break;
				case "ReactivePowerB"://无功
					AmmeterDetailsVO.setReactivePowerB(data.getValue());
					break;
				case "ReactivePowerC"://无功
					AmmeterDetailsVO.setReactivePowerC(data.getValue());
					break;
				case "TotalApparentPower"://无功
					AmmeterDetailsVO.setTotalApparentPower(data.getValue());
					break;
				//直流电表测点
				case "Voltage"://直流电压
					AmmeterDetailsVO.setVoltage(data.getValue());
					break;
				case "Current"://直流电流
					AmmeterDetailsVO.setCurrent(data.getValue());
					break;
				case "GeneratedActivePower"://直流功率
					AmmeterDetailsVO.setGeneratedActivePower(data.getValue());
					break;
				default:
					break;
				}
			}	
			return RestResponse.responseOk(AmmeterDetailsVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
