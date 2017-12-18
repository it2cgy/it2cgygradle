package com.yunengzhe.PVMTS.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.LetterConsigneeListVO;
import com.yunengzhe.PVMTS.domain.vo.LetterConsigneeReadVO;
import com.yunengzhe.PVMTS.domain.vo.LetterConsigneeVO;
import com.yunengzhe.PVMTS.domain.vo.LetterVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.LetterConsigneeService; 
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.CacheUserUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.authentication.vo.CacheUserInfo;

@Controller
@RequestMapping("/consignee")
public class LetterConsigneeController {
	
	private static final Logger logger = LoggerFactory.getLogger(LetterConsigneeController.class);
	
	@Autowired
	private LetterConsigneeService consigneeService;
	
	/**
	 * 加载发件箱数据
	 * @param session
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/getConsignee",method=RequestMethod.POST)
	@ResponseBody
	public Object getConsignee(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String,String>map){
		try{
			Integer page = Integer.valueOf(map.get("page")+"");
			Integer pagesize = Integer.valueOf(map.get("pagesize")+"");
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
				if(page==null){
					page = 1;
				}
				if(pagesize==null){
					pagesize = Paginator.DEFAULT_PAGESIZE;
				}
				ResultListVO resultListVO = consigneeService.getConsignee(user.getUserid(),page,pagesize);
				int total = resultListVO.getCounts();
				List<LetterConsigneeVO> list = resultListVO.getResultList();
				for(int i = 0;i<list.size();i++){
					LetterConsigneeVO lc = list.get(i);
					if(lc.getType()==1){
						total--;
					}
				}
				return RestResponse.responseList(request, resultListVO.getCounts(), resultListVO.getResultList()); 
		}catch(Exception e){
			return RestResponse.responseError();
		}
	}
	/**
	 * 在收件箱查看信件
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/readLetter/{id}")
	@ResponseBody
	public RestResponseVO readLetter(HttpServletRequest request,HttpServletResponse response,@PathVariable String id){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			LetterConsigneeReadVO LetterConsigneeReadVO = consigneeService.readLetter(id);
			return RestResponse.responseOk(LetterConsigneeReadVO);
		}catch(Exception e){
			return RestResponse.responseError();
		}
	}
	/**
	 * 在收件箱删除信件
	 * @param session
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delLetter/{ids}")
	@ResponseBody
	public RestResponseVO delLetter(HttpServletRequest request,HttpSession session,@PathVariable String ids,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			LetterVO letterVO = new LetterVO();
			if(org.apache.commons.lang3.StringUtils.isEmpty(ids)){
				letterVO.setError("参数类型不合法！");
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "参数为空");
			}
			letterVO = consigneeService.delLetter(ids);
			return RestResponse.responseOk(letterVO);
		
		}catch(Exception e){
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "删除失败");
		}
	}
	
}
