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

import com.alibaba.fastjson.JSONObject;
import com.yunengzhe.PVMTS.domain.dto.SearchMeansurePointDataDTO;
import com.yunengzhe.PVMTS.domain.po.InverterPO;
import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.AlarmCountByUipmentVO; 
import com.yunengzhe.PVMTS.domain.vo.AnalogStructureParam; 
import com.yunengzhe.PVMTS.domain.vo.HistoryData;
import com.yunengzhe.PVMTS.domain.vo.HistoryDataResult;
import com.yunengzhe.PVMTS.domain.vo.InverterDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.MeasurePointInfo;
import com.yunengzhe.PVMTS.domain.vo.MeasurePointValue;
import com.yunengzhe.PVMTS.domain.vo.PowerTemperatureTime;
import com.yunengzhe.PVMTS.domain.vo.QueryHisPointsParam;
import com.yunengzhe.PVMTS.domain.vo.QueryPointParam;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.longi.InverterToMeterVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureData;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureDatas;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryData;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryPointData;
import com.yunengzhe.PVMTS.service.AlarmInfoService;
import com.yunengzhe.PVMTS.service.AnaloginputService;
import com.yunengzhe.PVMTS.service.InverterService;
import com.yunengzhe.PVMTS.service.InverterToMeterService;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.ScadaService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.common.util.TimeUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

@Controller
@RequestMapping("/inverter")
public class InverterController {
	
	private static final Logger logger = LoggerFactory.getLogger(InverterController.class);
	
	@Autowired
	private InverterService inverterService;
//	@Autowired
//	public ScadaService scadaService;
	@Autowired
	private AnaloginputService analoginputService;
	@Autowired
	private AlarmInfoService alarmInfoService;
	
	
	@Autowired
	public StationMonitorService stationMonitorService; 

	@Autowired
	private PointInfoService pointInfoService;
	@Autowired
	private InverterToMeterService inverterToMeterService;

 
	
	/**
	 * 获取逆变器某测点的历史数据
	 * @param startTime
	 * @param endTime
	 * @param measurePointName
	 * @param InverterId
	 * @return
	 */
	@RequestMapping(value="/getInverterMeasurePointHistoryInfo",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO getInverterMeasurePointHistoryInfo(HttpServletRequest request,
			@RequestBody SearchMeansurePointDataDTO searchMeansurePointDataDTO,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<HistoryDataResult> result  = new ArrayList<HistoryDataResult>();
			List<HistoryData> datas = new ArrayList<HistoryData>();
			HistoryDataResult res = new HistoryDataResult(); 
			res.setStartTime(searchMeansurePointDataDTO.getStartTime());
			res.setEndTime(searchMeansurePointDataDTO.getEndTime());
			res.setMeasuerName(searchMeansurePointDataDTO.getMeasurePointName());
			res.setEquipcontainerID(searchMeansurePointDataDTO.getEquipmentId());
			res.setEquipcontainerTableID("3");
			res.setSpan("0");
			res.setDateSpan("300");
			res.setResult(datas);

			result.add(res);
			PointInfoPO pointInfo = pointInfoService.getPointInfo("measurePointName", 3, Integer.valueOf(searchMeansurePointDataDTO.getEquipmentId()));
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
	 * 获取对应id逆变器的所有测点
	 * @param InverterId
	 * @return
	 */
	@RequestMapping(value="/getInverterMeasurePoints/{InverterId}")
	@ResponseBody
	public RestResponseVO getInverterMeasurePoints(HttpServletRequest request,@PathVariable String InverterId,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
				List<MeasurePointInfo> list = analoginputService.getInverterMeasurePoints(InverterId);
				if(list.size()==0){
					return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "所查逆变器不存在，或者测点信息没有配置");
				}else{
				return RestResponse.responseOk(list);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取指定id的逆变器详情
	 * @param InverterId
	 * @return
	 */
	@RequestMapping(value="/getInverteInfo/{InverterId}")
	@ResponseBody
	public RestResponseVO getInverteInfo(HttpServletRequest request,@PathVariable String InverterId,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			//获取电表信息
			List<InverterToMeterVO> meterlist = inverterToMeterService.getMeters(Integer.valueOf(InverterId));
			int metertype = 0;
			for(int iij=0;iij<meterlist.size();iij++){
				if(meterlist.get(iij).getMeterType()==2){
					metertype = 1;//单相
				}
				if(meterlist.get(iij).getMeterType()==3){
					metertype = 2;//三相
				}
			}
			InverterDetailsVO InverterDetailsVO = new InverterDetailsVO();
			InverterDetailsVO.setTotalWorkingHours(0);
			//调取scada
			AnalogStructureParam queryparam1 = new AnalogStructureParam();
			String measuerNames1 = "GenerationDaily,GenerationMonth,GenerationGross,GenerationYear,"
					+"TotalInputPower,ThreePhaseActivePower,Efficiency,Temperature,"
					+ "VoltageA,CurrentA,VoltageB,CurrentB,VoltageC,CurrentC,Frequency,TotalWorkingHours"
					+ "DCVMPPT1,DCIMPPT1,DCVMPPT2,DCIMPPT2,DCVMPPT3,DCIMPPT3";
			queryparam1.setMeasuerNames(measuerNames1);
			queryparam1.setEquipcontainerID(Integer.valueOf(InverterId));
			queryparam1.setEquipcontainerTableID(3);//设备类型为3：逆变器
			//AnalogStructureResults resultsData1 = scadaService.getAnalogStructusData(queryparam1);
			//List<AnalogStructureData> result1 = resultsData1.getResult();
			
			AnalogStructureDatas datas = stationMonitorService.getMonitorEquipRealData(3,Integer.valueOf(InverterId));  
			 
			List<AnalogStructureData> result1 = datas.getData();
			List<MeasurePointValue> phaseVoltageParam = new ArrayList<MeasurePointValue>();
			List<MeasurePointValue> mppt = new ArrayList<MeasurePointValue>();
			double CurrentA =0;
			double CurrentB =0;
			double CurrentC =0;
			double Frequency =0;  
			double DCIMPPT1 =0;
			double DCIMPPT2 =0;
			double DCIMPPT3 =0;
			for(int i=0;i<result1.size();i++){
				AnalogStructureData data = result1.get(i);
				MeasurePointValue MeasurePointValue = new MeasurePointValue();
				switch(data.getMeasuerName()){
				//日、月、累计发电量
				case "GenerationDaily":
					InverterDetailsVO.setGenerationDaily(data.getValue());
					break;
				case "GenerationMonth":
					InverterDetailsVO.setGenerationMonth(data.getValue());
					break;
				case "GenerationYear":
					InverterDetailsVO.setGenerationYear(data.getValue());
					break;
				case "GenerationGross":
					InverterDetailsVO.setGenerationGross(data.getValue());
					break;
				case "TotalInputPower":
					InverterDetailsVO.setTotalInputPower(data.getValue());
					break;
				case "ThreePhaseActivePower":
					InverterDetailsVO.setThreePhaseActivePower(data.getValue());
					break;
				case "Efficiency":
					InverterDetailsVO.setEfficiency(data.getValue());
					break;
				case "Temperature":
					InverterDetailsVO.setTemperature(data.getValue());
					break;
				case "PowerFactor":
					InverterDetailsVO.setPowerFactor(data.getValue());
					break;
				case "TotalWorkingHours":
					InverterDetailsVO.setTotalWorkingHours(data.getValue()); //累计
					break;
					//直流、交流参数
				case "VoltageA":
					MeasurePointValue.setName("A相");
					MeasurePointValue.setValue(String.format("%.3f", data.getValue()).toString()+"V");
					phaseVoltageParam.add(MeasurePointValue);
					break;
				case "VoltageB":
					MeasurePointValue.setName("B相");
					MeasurePointValue.setValue(String.format("%.3f", data.getValue()).toString()+"V");
					phaseVoltageParam.add(MeasurePointValue);
					break;
				case "VoltageC":
					MeasurePointValue.setName("C相");
					MeasurePointValue.setValue(String.format("%.3f", data.getValue()).toString()+"V");
					phaseVoltageParam.add(MeasurePointValue);
					break;
				case "DCV1":
					MeasurePointValue.setName("MPPT1");
					MeasurePointValue.setValue(String.format("%.3f", data.getValue()).toString()+"V");
					mppt.add(MeasurePointValue);
					break;
				case "DCV2":
					MeasurePointValue.setName("MPPT2");
					MeasurePointValue.setValue(String.format("%.3f", data.getValue()).toString()+"V");
					mppt.add(MeasurePointValue);
					break;
				case "DCV3":
					MeasurePointValue.setName("MPPT3");
					MeasurePointValue.setValue(String.format("%.3f", data.getValue()).toString()+"V");
					mppt.add(MeasurePointValue);
					break;
				case "CurrentA":
					CurrentA = data.getValue();
					break;
				case "CurrentB":
					CurrentB = data.getValue();
					break;
				case "CurrentC":
					CurrentC = data.getValue();
					break;
				case "Frequency":
					Frequency = data.getValue();
					break;
				case "DCI1":
					DCIMPPT1 = data.getValue();
					break;
				case "DCI2":
					DCIMPPT2 = data.getValue();
					break;
				case "DCI3":
					DCIMPPT3 = data.getValue();
					break;
				default:
					break;
				}
			}
			for(int j=0;j<phaseVoltageParam.size();j++){
				MeasurePointValue mpvalue = phaseVoltageParam.get(j);
				String name = mpvalue.getName();
				String value = mpvalue.getValue();
				if(name.equals("A相")){
					value = value+" "
							+String.format("%.3f", CurrentA).toString()+"A "
							+String.format("%.3f", Frequency).toString()+"HZ";
				}
				if(name.equals("B相")){
					value = value+" "
							+String.format("%.3f", CurrentB).toString()+"A "
							+String.format("%.3f", Frequency).toString()+"HZ";
				}
				if(name.equals("C相")){
					value = value+" "
							+String.format("%.3f", CurrentC).toString()+"A "
							+String.format("%.3f", Frequency).toString()+"HZ";
				}
				mpvalue.setValue(value);
			}
			for(int k=0;k<mppt.size();k++){
				MeasurePointValue mpvalue = mppt.get(k);
				String name = mpvalue.getName();
				String value = mpvalue.getValue();
				if(name.equals("MPPT1")){
					value = value+" "
							+String.format("%.3f", DCIMPPT1).toString()+"A";
				}
				if(name.equals("MPPT2")){
					value = value+" "
							+String.format("%.3f", DCIMPPT2).toString()+"A";
				}
				if(name.equals("MPPT3")){
					value = value+" "
							+String.format("%.3f", DCIMPPT3).toString()+"A";
				}
				mpvalue.setValue(value);
			}
			InverterDetailsVO.setMppt(mppt);
			List<MeasurePointValue> phaseVoltageParamvo = new ArrayList<MeasurePointValue>();
			if(metertype==1){
				for(MeasurePointValue MeasurePointValue :phaseVoltageParam){
					if(MeasurePointValue.getName().equals("A相")){
						phaseVoltageParamvo.add(MeasurePointValue);
					}
				}
				InverterDetailsVO.setPhaseVoltageParam(phaseVoltageParamvo);
			}
			if(metertype==2){
				InverterDetailsVO.setPhaseVoltageParam(phaseVoltageParam);
			}
			//报警1、2、3级数量
			List<AlarmCountByUipmentVO>alarmsList = alarmInfoService.getAlarmCountByUipment(Integer.valueOf(InverterId),3);
			if(alarmsList.size()==0){
				InverterDetailsVO.setLevel1(0);
				InverterDetailsVO.setLevel2(0);
				InverterDetailsVO.setLevel3(0);
			}else{
				for(int m = 0;m<alarmsList.size();m++){
					if(alarmsList.get(m).getAlarmGrade()==1){
						InverterDetailsVO.setLevel1(alarmsList.get(m).getCount());
					}else if(alarmsList.get(m).getAlarmGrade()==2){
						InverterDetailsVO.setLevel2(alarmsList.get(m).getCount());
					}else if(alarmsList.get(m).getAlarmGrade()==3){
						InverterDetailsVO.setLevel3(alarmsList.get(m).getCount());
					}
				}
			}
			if(InverterDetailsVO.getLevel1()==null){
				InverterDetailsVO.setLevel1(0);
			}
			if(InverterDetailsVO.getLevel2()==null){
				InverterDetailsVO.setLevel2(0);
			}
			if(InverterDetailsVO.getLevel3()==null){
				InverterDetailsVO.setLevel3(0);
			}
			InverterPO InverterPO = inverterService.getInverterInfo(InverterId);
			InverterDetailsVO.setName(InverterPO.getName());
			InverterDetailsVO.setSerialNumber(InverterPO.getSerialNumber());
			InverterDetailsVO.setPowerStationId(Integer.valueOf(InverterPO.getFarmid()+""));
			//获取功率-温度时间曲线
			String start = TimeUtil.date2String(new Date(), "yyyy-MM-dd");
			String end = TimeUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
			List<PowerTemperatureTime> pttlist = new ArrayList<PowerTemperatureTime>();
			PointInfoPO pointInfo = pointInfoService.getPointInfo("ThreePhaseActivePower", 3, Integer.valueOf(InverterId));
			if(pointInfo!=null){
				MonitorHistoryPointData data = stationMonitorService.getMonitorHistoryData(pointInfo.getId(), TimeUtil.string2Date(start,"yyyy-MM-dd"), 
						TimeUtil.string2Date(end,"yyyy-MM-dd HH:mm:ss"), false);
				List<MonitorHistoryData> hisdata = data.getHistoryDatas();
				for(MonitorHistoryData his:hisdata){
					PowerTemperatureTime ptt = new PowerTemperatureTime();
					ptt.setTime(new Date(his.getTime()));
					ptt.setPower(his.getValue());
					pttlist.add(ptt);
				}
				 
			}
			PointInfoPO pointInfo2 = pointInfoService.getPointInfo("Temperature", 3, Integer.valueOf(InverterId));
			if(pointInfo2!=null){
				MonitorHistoryPointData data = stationMonitorService.getMonitorHistoryData(pointInfo2.getId(), TimeUtil.string2Date(start,"yyyy-MM-dd"), 
						TimeUtil.string2Date(end,"yyyy-MM-dd HH:mm:ss"), false);
				List<MonitorHistoryData> hisdata = data.getHistoryDatas();
				for(int n=0;n<hisdata.size();n++){
					MonitorHistoryData his = hisdata.get(n);
					PowerTemperatureTime ptt = pttlist.get(n);
					ptt.setTemperature(his.getValue());
					pttlist.add(ptt);
				}
				 
			}
			PointInfoPO pointInfo3 = pointInfoService.getPointInfo("ThreePhaseActivePower", 3, Integer.valueOf(InverterId));
			PointInfoPO pointInfo4 = pointInfoService.getPointInfo("GenerationDaily", 3, Integer.valueOf(InverterId));
			PointInfoPO pointInfo5 = pointInfoService.getPointInfo("GenerationMonth", 3, Integer.valueOf(InverterId));
			PointInfoPO pointInfo6 = pointInfoService.getPointInfo("GenerationYear", 3, Integer.valueOf(InverterId));
			PointInfoPO pointInfo7 = pointInfoService.getPointInfo("GenerationGross", 3, Integer.valueOf(InverterId));
			InverterDetailsVO.setGenerationMonthId(pointInfo5.getId());
			InverterDetailsVO.setGenerationYearId(pointInfo6.getId());
			InverterDetailsVO.setGenerationDailyId(pointInfo4.getId());
			InverterDetailsVO.setGenerationGrossId(pointInfo7.getId());
			InverterDetailsVO.setThreePhaseActivePowerId(pointInfo3.getId());
			InverterDetailsVO.setPowerTemperatureTime(pttlist);
			return RestResponse.responseOk(InverterDetailsVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
