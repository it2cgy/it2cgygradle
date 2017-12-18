package com.yunengzhe.PVMTS.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.SyncDatalogPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.role.RoleVO;
import com.yunengzhe.PVMTS.service.SyncDatalogService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/sync/")
public class SyncDatalogController {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncDatalogController.class);
	
	@Autowired
	private SyncDatalogService syncDatalogService;

	/**
	 * 设备列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list")
	public String List(Model model,String admin,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		model.addAttribute("admin",true);
		return "/sync/syncList";
	}
	
	/**
	 * 设备列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addSyncPage")
	public String addSync(Model model,String admin,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		model.addAttribute("admin",true);
		return "/sync/addSync";
	}
	
	/**
	 * 设备列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getSyncList")
	@ResponseBody
	public Object getList(String name,Integer page,Integer pagesize,HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 int userId = TokenUtil.getUserIdByToken(token);//通过token获取用户信息
			 if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 if(page==null){
					page = 1;
			 }
			 if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			 }
			 if(StringUtils.isBlank(name)){
				 name=null;
			 }
			 ResultListVO<SyncDatalogPO> listVO =syncDatalogService.getSyncData(name,Integer.valueOf(page),Integer.valueOf(pagesize));
			 return RestResponse.responseList(request, listVO.getCounts(), listVO.getResultList()); 
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
