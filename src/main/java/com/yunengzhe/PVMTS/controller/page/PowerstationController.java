package com.yunengzhe.PVMTS.controller.page;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.vo.PowerStationListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.util.TimeUtil;

/**
 * @ClassName:PowerstationController
 * @Description:TODO(电站管理)
 * @author chenguiyang
 * @date 2017年6月28日上午9:48:56
 */
@Controller
@RequestMapping("/powerstation")
public class PowerstationController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompantUserController.class);
	
	@Autowired
	private PowerStationService powerStationService;
	
	/**
	 * powerstationList
	 * @Description:TODO(跳转到电站列表页面) 
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/powerstationList")
	public String powerstationList(Model model,HttpServletRequest request,HttpServletResponse response,String admin,String searchkey){ 
		logger.info("---------------#跳转到电站列表页面#");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		}
		model.addAttribute("searchkey",searchkey);
		model.addAttribute("user",user);
		model.addAttribute("admin","1".equals(admin));
		return "/powerstation/powerstationList";
	}
	
	
	/**
	 * powerstationList
	 * @Description:TODO(跳转到新增电站页面) 
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/addPowerstation")
	public String addPowerstation(Model model,HttpServletRequest request,HttpServletResponse response,String admin){ 
		logger.info("---------------#跳转到新增电站页面#");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		}
		model.addAttribute("user",user);
		model.addAttribute("admin","1".equals(admin));
		return "/powerstation/addPowerstation";
	}
	
	/**
	 * @Title:editPowerstation
	 * @Description:TODO(编辑电站页面) 
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/editPowerstation")
	public String editPowerstation(String powerId,Model model,HttpServletRequest request,HttpServletResponse response,String admin){
		logger.info("---------------#跳转到新增电站页面#");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		}
		PowerStationListVO result = powerStationService.getPowerInfoById(powerId);
		model.addAttribute("powerstation",result);
		model.addAttribute("startProduceTime",TimeUtil.date2String(result.getStartProduceTime(),"yyyy-MM-dd"));
		model.addAttribute("admin","1".equals(admin));
		return "/powerstation/editPowerstation";
	}
	
	/**
	 * @Title:powerstationDetails
	 * @Description:TODO(电站详情) 
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/powerstationDetails")
	public String powerstationDetails(String powerId,Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("---------------#跳转到电站详情#");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		}
		PowerStationListVO result = powerStationService.getPowerInfoById(powerId);
		model.addAttribute("powerstation",result);
		model.addAttribute("startProduceTime",TimeUtil.date2String(result.getStartProduceTime(),"yyyy-MM-dd"));
		String lng = result.getLng().toString();
		String[] str = lng.split("\\.");
		String resultLng = str[0]+"°"+str[1].substring(0,2)+"′"+str[1].substring(2, 4)+"″";
		model.addAttribute("lng",resultLng);
		String lat = result.getLat().toString();
		String[] str2 = lat.split("\\.");
		String resultLat = str2[0]+"°"+str2[1].substring(0,2)+"′"+str2[1].substring(2, 4)+"″";
		model.addAttribute("lat",resultLat);
		int days = TimeUtil.sub2Date(new Date(), result.getStartProduceTime());
		model.addAttribute("runDays",days);
		List list = powerStationService.getEquipmentNum(result.getId());
		if(!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Map param = (Map) list.get(i);
				if(Integer.parseInt(param.get("id").toString())==3){
					model.addAttribute("inverter",param.get("num"));
				}
				if(Integer.parseInt(param.get("id").toString())==10){
					model.addAttribute("iv",param.get("num"));
				}
				if(Integer.parseInt(param.get("id").toString())==8){
					model.addAttribute("monitor",param.get("num"));
				}
			}
		}
		model.addAttribute("leyepowerStationId", powerId);
		return "/powerstation/powerstationDetail";
	}

}
