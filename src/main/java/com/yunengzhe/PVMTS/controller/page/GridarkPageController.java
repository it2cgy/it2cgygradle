package com.yunengzhe.PVMTS.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.controller.GridarkController;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/gridarkPageController")
public class GridarkPageController {

	@Autowired
	private GridarkController gridarkController;
	/**
	 * @Title:并网柜
	 * @Description:TODO(用一句话描述一下这个方式的作用) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/gridarkPage/{powerStationId}")
	public String gridarkPage(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable int powerStationId){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
//		RestResponseVO gridarkDetails = gridarkController.getGridarkDetails(response, request, powerStationId+"");
//		model.addAttribute("grid", gridarkDetails.getData());
		model.addAttribute("powerStationId", powerStationId);
		return "/gridark/gridark";
	}
}