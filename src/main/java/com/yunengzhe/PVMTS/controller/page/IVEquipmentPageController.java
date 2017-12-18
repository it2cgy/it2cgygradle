package com.yunengzhe.PVMTS.controller.page;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.service.IvEquipmentService;

@Controller
@RequestMapping("/ivequipmentPage")
public class IVEquipmentPageController {

	
	@Autowired
	private IvEquipmentService ivEquipmentService;
	/**
	 * @Title:并网柜
	 * @Description:TODO(用一句话描述一下这个方式的作用) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/{powerStationId}")
	public String ivEquipmentPage(Integer isThird,Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable int powerStationId){
		if(isThird==null || isThird==0){
			model.addAttribute("isThird",false);
		}else{
			model.addAttribute("isThird",true);
		}
		return "/ivequipment/ivequipment";
	}
	/**
	 * @Title:并网柜
	 * @Description:TODO(用一句话描述一下这个方式的作用) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/ivEquipmentThirdPage/{powerStationId}")
	public String ivEquipmentThirdPage(Integer isThird,Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable int powerStationId){
		return "/ivequipment/ivequipmentThird";
	}
	
	/**
	 * @Title:ivEquipmentDetails
	 * @Description:TODO(跳转到iv通道详细页面) 
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/ivEquipmentDetails")
	public String ivEquipmentDetails(int containerId,String equipmentname,Model model,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> result = ivEquipmentService.getIvEquipmentInfo(containerId);
		model.addAttribute("result",result);
		model.addAttribute("equipmentname",equipmentname);
		return "/ivequipment/ivequipmentDetails";
	}
	
}