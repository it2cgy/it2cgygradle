package com.yunengzhe.PVMTS;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunengzhe.PVMTS.controller.PowerStationController;
import com.yunengzhe.PVMTS.domain.dto.MailDTO;
import com.yunengzhe.PVMTS.domain.po.AlarmInfoPO;
import com.yunengzhe.PVMTS.domain.po.MailConfigPO;
import com.yunengzhe.PVMTS.domain.po.PowerStationPO;
import com.yunengzhe.PVMTS.domain.po.ReportDailyPO;
import com.yunengzhe.PVMTS.domain.po.ReportDailyPowerPO;
import com.yunengzhe.PVMTS.domain.vo.AmmeterInfoVO;
import com.yunengzhe.PVMTS.domain.vo.InverterInfoVO;
import com.yunengzhe.PVMTS.domain.vo.IvEquipmentVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.SaveReportDailyVO;
import com.yunengzhe.PVMTS.domain.vo.UserEmailVO;
import com.yunengzhe.PVMTS.domain.vo.WeatherInfoVO;
import com.yunengzhe.PVMTS.domain.vo.weather.WeatherVO;
import com.yunengzhe.PVMTS.service.AlarmInfoService;
import com.yunengzhe.PVMTS.service.DeviceRatedService;
import com.yunengzhe.PVMTS.service.IvEquipmentService;
import com.yunengzhe.PVMTS.service.MailConfigService;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.service.PushMailService;
import com.yunengzhe.PVMTS.service.ReportDailyPowerService;
import com.yunengzhe.PVMTS.service.ReportDailyService;
import com.yunengzhe.PVMTS.util.ApiClient;
import com.yunengzhe.PVMTS.util.CreateDateExcel;
import com.yunengzhe.PVMTS.util.CreateReportWord;
import com.yunengzhe.PVMTS.util.JsonUtil;
import com.yunengzhe.PVMTS.util.MailUtils;
import com.yunengzhe.PVMTS.util.http.ResponseData;
import com.yunengzhe.commons.util.BigDecimalUtils;
import com.yunengzhe.commons.util.SpringContextUtil;
import com.yunengzhe.commons.util.TimeUtil;

/**
 * @ClassName:AlarmThread
 * @Description:TODO(报警发送邮件处理)
 * @author chenguiyang
 * @date 2017年9月12日下午5:05:14
 */
public class ReportDailyThread extends Thread{


	private static final Logger logger = LoggerFactory.getLogger(ReportDailyThread.class);
	private ReportDailyPowerService reportDailyPowerService;
	private PowerStationService powerStationService;
	private DeviceRatedService deviceRatedService;
	private IvEquipmentService ivEquipmentService;
	private ReportDailyService reportDailyService;
	private PowerStationController powerStationController;

	@Override
	public void run() {
		logger.info("Start Create Report For Daily---#");
		try {
			if(reportDailyPowerService==null){
				reportDailyPowerService = (ReportDailyPowerService) SpringContextUtil.getBean("reportDailyPowerService");
			}
			if(powerStationService==null){
				powerStationService = (PowerStationService) SpringContextUtil.getBean("powerStationService");
			}
			if(deviceRatedService==null){
				deviceRatedService = (DeviceRatedService) SpringContextUtil.getBean("deviceRatedService");
			}
			if(ivEquipmentService==null){
				ivEquipmentService = (IvEquipmentService) SpringContextUtil.getBean("ivEquipmentService");
			}
			if(reportDailyService==null){
				reportDailyService = (ReportDailyService) SpringContextUtil.getBean("reportDailyService");
			}
			if(powerStationController==null){
				powerStationController = (PowerStationController) SpringContextUtil.getBean("powerStationController");
			}
			boolean createSuccess = false;
			for(int i =0;i<10;i++){
				if(!createSuccess){
					logger.info("开始重新生成每日报表----第"+(i+1)+"次");
					createSuccess=createReportDaily();
					if(createSuccess){
						break;
					}else{
						logger.error("生成报表失败！");
					}
				}
			}
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private  boolean createReportDaily(){
		try {
			List<SaveReportDailyVO> result = new ArrayList<SaveReportDailyVO>();
			List<ReportDailyPowerPO> reportDailyPower = reportDailyPowerService.getReportDailyPower();
			for (ReportDailyPowerPO reportDailyPowerPO : reportDailyPower) {
				logger.info("Check the dbase reportDailyPower ！");
				SaveReportDailyVO vo = new SaveReportDailyVO();
				Map<String,Object> map = new HashMap<String,Object>();
				//电站信息
				Date date = new Date();
				SimpleDateFormat formar = new SimpleDateFormat("yyyy年MM月dd日");
				String formatDate = formar.format(date);
				map.put("date", formatDate);
				PowerStationPO powerStationdetailInfo = powerStationService.getPowerStationdetailInfo(reportDailyPowerPO.getPowerId()+"");
				map.put("installCapacity", powerStationdetailInfo.getInstallCapacity());//装机容量
				//天气信息
				if(powerStationdetailInfo!=null){
					if(powerStationdetailInfo.getCityName()!=null){
						String url = "http://test.yunengzhe.com:846/tools/weatherInfo?cityName="+powerStationdetailInfo.getCityName();
						ResponseData responseData = ApiClient.get(url, null);
						if(responseData.getBackData()!=null){
							WeatherVO weatherVO = (WeatherVO) JsonUtil.jsonToBean(responseData.getBackData(), WeatherVO.class);
							map.put("temperature",weatherVO.getResults().get(0).getWeather_data().get(0).getTemperature());//气温
							map.put("weather",weatherVO.getResults().get(0).getWeather_data().get(0).getWeather());//天气状况
						}
					}
				}
				//发电量
				PowerStationDetailsVO data = (PowerStationDetailsVO)powerStationController.PowerStationDetail( reportDailyPowerPO.getPowerId()+"");
				map.put("generationGross", data.getGenerationGross());//总发电量
				map.put("generationYear", data.getGenerationYear());//年发电量
				map.put("generationMonth", data.getGenerationMonth());//月发电量
				map.put("generationDaily", data.getGenerationDaily());//日发电量
				WeatherInfoVO weatherInfoVO = (WeatherInfoVO)powerStationController.getWeatherDetail(reportDailyPowerPO.getPowerId()+"");
				map.put("radiantExposure", weatherInfoVO.getRadiantExposure());//水平面日
				map.put("radiantExposureMonth", weatherInfoVO.getRadiantExposureMonth());//水平面日
				map.put("inclinedExposure", weatherInfoVO.getInclinedExposure());//斜面日
				map.put("inclinedExposureMonth", weatherInfoVO.getInclinedExposureMonth());//斜面日
				map.put("directExposure", weatherInfoVO.getDirectRadiation());//直面日
				map.put("directExposureMonth", weatherInfoVO.getDirectExposureMonth());//直面日
				map.put("ScateredExposure", weatherInfoVO.getScateredRadiation());//散面日
				map.put("ScateredExposureMonth", weatherInfoVO.getScateredExposureMonth());//散面日
				map.put("rainFall", weatherInfoVO.getRainFall());//降雨量
				map.put("radiantsum",weatherInfoVO.getRadiantExposureTotal());//水平面辐照总累积量
				map.put("inclinedsum", weatherInfoVO.getInclinedExposureTotal());//斜面辐照总累积量
				List<InverterInfoVO> list = (List<InverterInfoVO>)powerStationController.getInverterDetail(reportDailyPowerPO.getPowerId()+"");
				for (InverterInfoVO inverterInfoVO : list) {
					inverterInfoVO.setRatedPower(BigDecimalUtils.round(Double.parseDouble(deviceRatedService.findByequepment(inverterInfoVO.getId(), 3).getRatedPower()+""), 3));
				}
				map.put("InverterList", list);//逆变器
				List<AmmeterInfoVO> list2 = (List<AmmeterInfoVO>)powerStationController.getAmmeterDetailInfo(reportDailyPowerPO.getPowerId()+"",1);//直流电表
				List<AmmeterInfoVO> Three = new ArrayList<AmmeterInfoVO>();
				List<AmmeterInfoVO> Genera = new ArrayList<AmmeterInfoVO>();
				for (AmmeterInfoVO ammeterInfoVO : list2) {
					ammeterInfoVO.setRatedPower(BigDecimalUtils.round(Double.parseDouble(deviceRatedService.findByequepment(ammeterInfoVO.getId(), 5).getRatedPower()+""), 3));
					if(ammeterInfoVO.getName().contains("DC")){
						Genera.add(ammeterInfoVO);
					}else if(ammeterInfoVO.getName().contains("AC")){
						Three.add(ammeterInfoVO);
					}
				}
				map.put("Genera", Genera);//直流电表
				map.put("Three", Three);//交流电表
				List<IvEquipmentVO> ivEquipmentThird = ivEquipmentService.getIvEquipmentDetail(reportDailyPowerPO.getPowerId());
				for (IvEquipmentVO ivEquipmentVO : ivEquipmentThird) {
					ivEquipmentVO.setRatedPower(BigDecimalUtils.round(Double.parseDouble(deviceRatedService.findByequepment(ivEquipmentVO.getId(), 10).getRatedPower()+""), 3));
				}
				map.put("ivList", ivEquipmentThird);

				File file = CreateReportWord.createDoc(map,formatDate+powerStationdetailInfo.getName()+"日报", "longieb",null);
				String url = file.getAbsolutePath();
				ReportDailyPO reportDailyPO = new ReportDailyPO();
				reportDailyPO.setReportname(formatDate+powerStationdetailInfo.getName()+"日报");
				reportDailyPO.setUrl(url);
				reportDailyService.addReportDaily(reportDailyPO);
				vo.setPowerId(powerStationdetailInfo.getId());
				vo.setPowerName(powerStationdetailInfo.getName());
				vo.setReportId(reportDailyPO.getId());
				result.add(vo);
				logger.debug("每日报表已生成,生成报表时间:"+formatDate+"报表信息:"+reportDailyPO);
				logger.info("-------检查每日报表是否创建成功");
				File filetemp = new File(file.getPath());
				if(!filetemp.exists() || reportDailyPO.getId()==null){
					logger.error("fail to create reportDaily -->创建每日报表失败，文件目录不存在或数据库中无数据");
					return false;
				}else{
					return true;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args){


	}
}
