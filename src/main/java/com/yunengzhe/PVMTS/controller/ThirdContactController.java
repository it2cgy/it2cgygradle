package com.yunengzhe.PVMTS.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.ThirdContactPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.service.ThirdContactService;


/**
 * @ClassName:ThirdContactController
 * @Description:TODO(第三方联系人处理)
 * @author chenguiyang
 * @date 2017年6月7日下午2:00:37
 */
@Controller
@RequestMapping("/thirdContact")
public class ThirdContactController {
	
	private static final Logger logger = LoggerFactory.getLogger(ThirdContactController.class);
	
	@Autowired
	private ThirdContactService thirdContactService;
	
	
	
	/**
	 * @Title:thirdContact
	 * @Description:TODO(获取第三方联系人信息) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/thirdContact/{orderNum}",method=RequestMethod.GET)
	@ResponseBody
	public Object thirdContact(@PathVariable String orderNum,HttpServletRequest request,HttpServletResponse response){
		try {
			logger.debug("根据工单编号获取第三方联系人信息");
			List<ThirdContactPO> listData = thirdContactService.thirdContact(orderNum);
			if(listData==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "获取备品备件信息失败或无备品备件信息！");
			}
			return RestResponse.responseOk(listData);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}

}
