package com.yunengzhe.PVMTS.controller.page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.controller.TrackInfoController;
import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.po.TrackInfoPO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorRealData;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.TrackInfoService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
@Controller
@RequestMapping("/TrackPageController")
public class TrackPageController {
	@Autowired
	private TrackInfoController trackInfoController;
	@Autowired
	private StationMonitorService stationMonitorService;
	
	@Autowired
	private TrackInfoService trackInfoService;
	@Autowired
	private PointInfoService pointInfoService;
	
	@RequestMapping(value="/TrackPage")
	public String TrackPage(Model model,HttpServletRequest request,HttpServletResponse response,Integer id){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		}  
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
					
				}
			}
		}
		model.addAttribute("trackList",pointList);
		return "/controls/controls";
	}
}
