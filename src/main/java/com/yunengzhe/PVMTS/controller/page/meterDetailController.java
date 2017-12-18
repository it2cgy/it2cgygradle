package com.yunengzhe.PVMTS.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.controller.AmmeterController;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.AmmeterDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/meter")
public class meterDetailController {

	@Autowired
	private AmmeterController ammeterController;
	
	@RequestMapping(value="/meterDetail/{meterid}")
	public String toLogsList(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable String meterid,Integer page,Integer pagesize,Integer oldId){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
//		RestResponseVO ammeterDetails = ammeterController.getAmmeterDetails(response, request, meterid);
//		model.addAttribute("meter", (AmmeterDetailsVO)ammeterDetails.getData());
		model.addAttribute("meterId", meterid);
		model.addAttribute("oldpage",page);
		model.addAttribute("oldpagesize",pagesize);
		if(oldId==null){
			model.addAttribute("oldId",-1);
		}else{
			model.addAttribute("oldId",oldId);
		}
		return "/meter/meterDetail";
	}
}
