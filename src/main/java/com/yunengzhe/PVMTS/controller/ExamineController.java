package com.yunengzhe.PVMTS.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.ExaminePO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.service.ExamineService;


/**
 * @ClassName:ExamineController
 * @Description:TODO(电站体检检查标准处理)
 * @author chenguiyang
 * @date 2017年6月6日下午3:37:39
 */
@Controller
@RequestMapping("/examine")
public class ExamineController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExamineController.class);
	
	@Autowired
	private ExamineService examineService;

	/**
	 * @Title:examineInfo
	 * @Description:TODO(获取电站体检检查项目) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/examineInfo")
	@ResponseBody
	public Object examineInfo(HttpServletRequest request,HttpServletResponse response){
		try {
			List<ExaminePO> resultData = examineService.getExamineInfo();
			if(resultData==null){
				return RestResponse.responseCode(0, "无法加载检查项目!", resultData);
			}
			return RestResponse.responseCode(1, "获取电站体检检查项目！", resultData);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
