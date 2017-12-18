package com.yunengzhe.PVMTS.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.controller.LogsInfoController;
import com.yunengzhe.PVMTS.dao.PointInfoDao;
import com.yunengzhe.PVMTS.domain.po.LogsCategoryPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.EquipmentInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.CompanyService;
import com.yunengzhe.PVMTS.service.LogsCategoryService;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/logsPage")
public class LogsPageController {

	@Autowired
	private LogsInfoController logsInfoController;
	@Autowired
	private PowerStationService powerStationService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private PointInfoDao pointInfoDaoImpl;
	@Autowired
	private PointInfoService pointInfoService;
	@Autowired
	private LogsCategoryService logsCategoryService;
//	String token = "DA58A5485E52B2A5DA3EA90F367A1636";
	private static final Logger logger = LoggerFactory.getLogger(CompantUserController.class);
	@RequestMapping(value="/toLogsList")
	public String toLogsList(Model model,HttpServletRequest request,HttpServletResponse response,String admin){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		}
		List<PowerStationBaseInfoVO> powerStationBaseInfo = powerStationService.getPowerStationBaseInfo("",user.getCompanyId()+"","", null);
		model.addAttribute("category", logsCategoryService.getLogsCateList());
		model.addAttribute("powerStations", powerStationBaseInfo);
		model.addAttribute("admin",true);
		return "/logs/logsList";
	}
	@RequestMapping(value="/toLogsAdd")
	public String toLogsAdd(Model model,HttpServletRequest request,HttpServletResponse response,String admin){ 
		
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		}
		model.addAttribute("user",user);
		String roleId = null;
		if(user.getRoleList().get(0).getRoleId()!=2){
			roleId=user.getRoleList().get(0).getRoleId().toString();
		}
		List<PowerStationBaseInfoVO> powerStationBaseInfo = powerStationService.getPowerStationBaseInfo("",user.getCompanyId()+"","", roleId);
		model.addAttribute("category", logsCategoryService.getLogsCateList());
		model.addAttribute("powerStations", powerStationBaseInfo);
		model.addAttribute("admin",true);
		logger.info("--------------->日志添加页面");
		return "/logs/logsAdd";
	}
	@RequestMapping(value="/toLogsEdit/{id}")
	public String toLogsEdit(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable int id,String admin){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		} 
		RestResponseVO readLogs = logsInfoController.readLogs(request,response,id+"");
		model.addAttribute("user",user);
		model.addAttribute("logs", readLogs);
		String roleId = null;
		if(user.getRoleList().get(0).getRoleId()!=2){
			roleId=user.getRoleList().get(0).getRoleId().toString();
		}
		List<PowerStationBaseInfoVO> powerStationBaseInfo = powerStationService.getPowerStationBaseInfo("",user.getCompanyId()+"","", roleId);		
		model.addAttribute("powerStations", powerStationBaseInfo);
		List<LogsCategoryPO> logsCateList = logsCategoryService.getLogsCateList();
		logger.info(model.toString());
		model.addAttribute("admin",true);
		model.addAttribute("powerStations", powerStationBaseInfo);
		model.addAttribute("category", logsCateList);
		return "/logs/logsEdit";
	}
	@RequestMapping(value="/toLogsDetail/{id}")
	public String toLogsDetail(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable int id,String admin){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		} 
		RestResponseVO readLogs = logsInfoController.readLogs(request,response,id+"");
		model.addAttribute("category", logsCategoryService.getLogsCateList());
		model.addAttribute("user",user);
		model.addAttribute("logs", readLogs);
		List<PowerStationBaseInfoVO> powerStationBaseInfo = powerStationService.getPowerStationBaseInfo("",user.getCompanyId()+"","", null);
		model.addAttribute("powerStations", powerStationBaseInfo);
		logger.info(model.toString());
		model.addAttribute("admin",true);
		return "/logs/logsDetail";
	}
	@RequestMapping(value="/toLogsDispose/{id}")
	public String toLogsDispose(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable int id,String admin){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		} 
		RestResponseVO readLogs = logsInfoController.readLogs(request,response,id+"");
		model.addAttribute("category", logsCategoryService.getLogsCateList());
		model.addAttribute("user",user);
		model.addAttribute("logs", readLogs);
		List<PowerStationBaseInfoVO> powerStationBaseInfo = powerStationService.getPowerStationBaseInfo("",user.getCompanyId()+"","", null);
		model.addAttribute("powerStations", powerStationBaseInfo);
		logger.info(model.toString());
		model.addAttribute("admin",true);
		return "/logs/logsDispose";
	}
}
