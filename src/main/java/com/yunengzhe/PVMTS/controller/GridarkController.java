package com.yunengzhe.PVMTS.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.AmmeterPO;
import com.yunengzhe.PVMTS.domain.po.GridarkPO;
import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.AmmeterDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureData;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureDatas;
import com.yunengzhe.PVMTS.service.GridarkService;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

@Controller
@RequestMapping("/gridark")
public class GridarkController {
	
	private static final Logger logger = LoggerFactory.getLogger(GridarkController.class);
	@Autowired
	private PointInfoService pointInfoService;
	@Autowired
	private GridarkService gridarkService;
	
	@Autowired
	private StationMonitorService stationMonitorService;
	/**
	 * 获取并网柜详情
	 * @param response
	 * @param AmmeterId
	 * @return
	 */
	@RequestMapping(value="/getGridarkDetails/{powerStationId}")
	@ResponseBody
	public RestResponseVO getGridarkDetails(HttpServletResponse response,HttpServletRequest request,@PathVariable String powerStationId){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			//通过电站id查询并网柜  一个电站只有一个并网柜
			GridarkPO gridark = gridarkService.getGridark(powerStationId);
			
			AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(gridark.getEquipmenttypeid(),gridark.getId());  
			List<AnalogStructureData> result = datas.getData();
			AmmeterDetailsVO AmmeterDetailsVO = new AmmeterDetailsVO();
			String local = (String)HttpSessionUtil.getAttribute("local");
			AmmeterDetailsVO.setName(gridark.getName());
			if("en_US".equals(local)){
				AmmeterDetailsVO.setName(gridark.getEnglishname());
			}
			AmmeterDetailsVO.setMeterType(Integer.valueOf(gridark.getMeterType()+"")); 
			if(StringUtil.empty(gridark.getSerialNumber())){
				AmmeterDetailsVO.setSerialNumber(gridark.getName());
			}else{
				AmmeterDetailsVO.setSerialNumber(gridark.getSerialNumber());
			}
			//交流功率id
			PointInfoPO pointInfo = pointInfoService.getPointInfo("ThreePhaseActivePower", 6, gridark.getId());
			if(pointInfo!=null){
				AmmeterDetailsVO.setPowerId(pointInfo.getId());
			}else{
				AmmeterDetailsVO.setPowerId(-1);
			}
			PointInfoPO pointInfo2 = pointInfoService.getPointInfo("GenerationDaily", 6, gridark.getId());
			PointInfoPO pointInfo3 = pointInfoService.getPointInfo("GenerationMonth", 6, gridark.getId());
			PointInfoPO pointInfo4 = pointInfoService.getPointInfo("GenerationYear", 6, gridark.getId());
			PointInfoPO pointInfo5 = pointInfoService.getPointInfo("PositiveActiveP", 6, gridark.getId());
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
