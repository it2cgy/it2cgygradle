package com.yunengzhe.PVMTS.controller.page;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.controller.InverterController;
import com.yunengzhe.PVMTS.controller.PowerStationController;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.InverterDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.util.JsonUtil;

@Controller
@RequestMapping("/inverterPage")
public class InverterPageController {
	
	@Autowired
	private InverterController inverterController;
	@Autowired
	private PowerStationController powerStationController;
	
	private static final Logger logger = LoggerFactory.getLogger(InverterPageController.class);
	@RequestMapping(value="/inverterList")
	public String inverterList(Model model,HttpServletRequest request,HttpServletResponse response,Integer id,Integer page,Integer pagesize){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		}
		if(page==null){
			page = 1;
		}
		if(pagesize==null){
			pagesize = 5;
		}
		model.addAttribute("page",page);
		model.addAttribute("pagesize",pagesize);
		model.addAttribute("inverterId",id);
		if(user.getRoleList().get(0).getRoleId()!=4){
			logger.info("---------------逆变器列表页面");
			return "/inverter/inverterList";
		}else{
			return "/inverter/thirdInverterList";
		}
	}
	
	@RequestMapping(value="/inverterDetail/{inverterId}")
	public String inverterDetail(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer inverterId,Integer page,Integer pagesize,Integer oldId){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		}
		if(page==null){
			page = 1;
		}
		if(pagesize==null){
			pagesize = Paginator.DEFAULT_PAGESIZE;
		}
		RestResponseVO res = inverterController.getInverteInfo(request,inverterId+"",response);
		try {
			logger.info(JsonUtil.beanToJson(res));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InverterDetailsVO inverterDetailsVO = (InverterDetailsVO) res.getData();
		Map<String,String> inverterInfo = new HashMap<String,String>();
		inverterInfo.put("inverterId", inverterId+"");
		inverterInfo.put("powerStationId", inverterDetailsVO.getPowerStationId()+"");
		model.addAttribute("InverterInfo",inverterInfo);
		model.addAttribute("name", inverterDetailsVO.getName());
		model.addAttribute("serialNumber", inverterDetailsVO.getSerialNumber());
		model.addAttribute("oldpage",page);
		model.addAttribute("oldpagesize",pagesize);
		model.addAttribute("oldInverterId",inverterId);
		if(oldId==null){
			model.addAttribute("oldId",-1);
		}else{
			model.addAttribute("oldId",oldId);
		}
		logger.info("--------------->逆变器页面");
		return "/inverter/inverterDetail";
	}
}
