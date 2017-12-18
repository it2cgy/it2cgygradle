package com.yunengzhe.PVMTS.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.CompanyPO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.service.CompanyService;
import com.yunengzhe.PVMTS.service.UserService;

/**
 * @ClassName:CompanyController
 * @Description:TODO(公司处理)
 * @author chenguiyang
 * @date 2017年6月14日上午9:25:10
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;

	
	/**
	 * @Title:companys
	 * @Description:TODO(获取公司列表) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/companys")
	@ResponseBody
	public Object companys(HttpServletRequest request,HttpServletResponse response){
		List<CompanyPO> list  = new ArrayList<CompanyPO>();
		try {
			list= companyService.getCompanies();
			return RestResponse.responseCode(1, "获取公司列表成功！", list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
}
