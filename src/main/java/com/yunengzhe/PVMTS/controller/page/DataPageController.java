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

import com.yunengzhe.PVMTS.domain.vo.CurveDetailVO;

import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.CurveInfoService;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

@Controller
@RequestMapping("/dataPage")
public class DataPageController {


	@Autowired
	private PowerStationService powerStationService;
	@Autowired
	private CurveInfoService curveInfoService;
	//String token = "DA58A5485E52B2A5DA3EA90F367A1636";
	private static final Logger logger = LoggerFactory.getLogger(CompantUserController.class);
	@RequestMapping(value="/dataList")
	public String curveList(HttpServletRequest request,HttpServletResponse response){
		logger.info("--------------->自定义曲线列表页面");
	 
		return "/data/dataList";
	}
	@RequestMapping(value="/addData")
	public String addCurve(Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		} 
		model.addAttribute("user",user);
		logger.info("--------------->自定义曲线添加页面");
		return "/data/addData";
	}
	@RequestMapping(value="/editData/{id}")
	public String editCurve(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		logger.info("--------------->自定义曲线修改页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		} 
		List<CurveDetailVO> list = curveInfoService.getCurveDetail(id+"");
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			for(int i=0;i<list.size();i++){
				CurveDetailVO cv = list.get(i);
				for(int j=0;j<cv.getPointList().size();j++){
					cv.getPointList().get(j).setPointName(cv.getPointList().get(j).getPointEnglishName());
				}
			}
		}
		model.addAttribute("curveDetailVO",list.get(0));
		return "/data/editData";
	}
	@RequestMapping(value="/dataDetail/{id}")
	public String curveDetail(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		logger.info("--------------->自定义曲线详情页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		} 
		model.addAttribute("user",user);
		List<CurveDetailVO> list = curveInfoService.getCurveDetail(id+"");
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			for(int i=0;i<list.size();i++){
				CurveDetailVO cv = list.get(i);
				for(int j=0;j<cv.getPointList().size();j++){
					cv.getPointList().get(j).setPointName(cv.getPointList().get(j).getPointEnglishName());
				}
			}
		}
		model.addAttribute("CurveDetailVO",list.get(0));
		return "/data/dataDetail";
	}
}
