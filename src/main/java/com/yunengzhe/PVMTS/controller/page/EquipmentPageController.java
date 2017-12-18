package com.yunengzhe.PVMTS.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.controller.EquipmentController;
import com.yunengzhe.PVMTS.domain.vo.EquipmentVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/equipmentPage")
public class EquipmentPageController {
	
	@Autowired
	private EquipmentController equipmentController;
	

	@Autowired
	private PowerStationService powerStationService;
	/**
	 * 设备列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/equipmentList")
	public String equipmentList(Model model,String admin,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		model.addAttribute("admin",true);
		return "/equipment/equipmentList";
	}
	/**
	 * 添加设备
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/equipmentAdd")
	public String equipmentAdd(Model model,String admin,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		model.addAttribute("admin",true);
		return "/equipment/equipmentAdd";
	}
	/**
	 * 编辑设备
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/equipmentEdit/{id}")
	public String equipmentEdit(Model model,String admin,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer id){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		EquipmentVO equipment = equipmentController.getEquipment(id);
		model.addAttribute("equipment", equipment);
		model.addAttribute("admin",true);
		return "/equipment/equipmentEdit";
	}
	/**
	 * 设备详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/equipmentDetail/{id}")
	public String equipmentDetail(Model model,String admin,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer id){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		EquipmentVO equipment = equipmentController.getEquipment(id);
		equipment.setFilename("?filename="+equipment.getFilename());
		model.addAttribute("equipment", equipment);
		model.addAttribute("admin",true);
		return "/equipment/equipmentDetail";
	}
}
