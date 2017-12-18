package com.yunengzhe.PVMTS.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.po.TrackInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorRealData;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.TrackInfoService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/trackInfoController")
public class TrackInfoController{
	
	private static final Logger logger = LoggerFactory.getLogger(TrackInfoController.class);
	
	@Autowired
	private TrackInfoService trackInfoService;
	@Autowired
	private PointInfoService pointInfoService;
	@Autowired
	private StationMonitorService stationMonitorService;
	/**
	 * 单双轴跟踪系统
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getTrackInfoList",method=RequestMethod.POST)
	@ResponseBody
	public Object getTrackInfoList(HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			List<TrackInfoPO> pointList = trackInfoService.getTrackInfoList();
			return RestResponse.responseOk(pointList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
	}
	
	/**
	 * 单双轴跟踪系统
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getTrackInfoListInfo")
	@ResponseBody
	public Object getTrackInfoListInfo(HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			List<MonitorRealData> monitorReal = new ArrayList<MonitorRealData>();
			List<TrackInfoPO> pointList = trackInfoService.getTrackInfoList(); 
			for (TrackInfoPO trackInfoPO : pointList) {
				List<PointInfoPO> pointsNOpage = pointInfoService.getPointsNOpage("6", trackInfoPO.getEquipmenttypeid()+"", trackInfoPO.getId()+"", null);
				for (PointInfoPO pointInfoPO : pointsNOpage) {
					if(pointInfoPO.getMeasurementtypeName().equals("SystemExceptionInfo")){
						MonitorRealData monitorRealData = stationMonitorService.getMonitorRealData(pointInfoPO.getId());
						trackInfoPO.setStatus(monitorRealData.getValue());
					}
					if(trackInfoPO.getId()==pointInfoPO.getEquipmentcontainerId()){
						MonitorRealData monitorRealData = stationMonitorService.getMonitorRealData(pointInfoPO.getId());
						if(pointInfoPO.getMeasurementtypeDescription().contains("实际组件角-方位角")){
							BigDecimal bigDecimal = new BigDecimal(monitorRealData.getValue());
							trackInfoPO.setFangvalue(bigDecimal.setScale(3, bigDecimal.ROUND_HALF_UP).doubleValue());
						}else if(pointInfoPO.getMeasurementtypeDescription().contains("实际组件角-高度角")){
							BigDecimal bigDecimal = new BigDecimal(monitorRealData.getValue());
							trackInfoPO.setQinvalue(bigDecimal.setScale(3, bigDecimal.ROUND_HALF_UP).doubleValue());
						}
						if(pointInfoPO.getMeasurementtypeDescription().contains("理论太阳角-方位角")){
							BigDecimal bigDecimal = new BigDecimal(monitorRealData.getValue());
							trackInfoPO.setLxfangvalue(bigDecimal.setScale(3, bigDecimal.ROUND_HALF_UP).doubleValue());
						}else if(pointInfoPO.getMeasurementtypeDescription().contains("理论太阳角-高度角")){
							BigDecimal bigDecimal = new BigDecimal(monitorRealData.getValue());
							trackInfoPO.setLxqinvalue(bigDecimal.setScale(3, bigDecimal.ROUND_HALF_UP).doubleValue());
						}
					}
				}
			}
			return RestResponse.responseOk(pointList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
	}

}
