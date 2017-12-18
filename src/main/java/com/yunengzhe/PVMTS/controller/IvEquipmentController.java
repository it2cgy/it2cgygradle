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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.IvEquipmentVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.service.IvEquipmentService;
import com.yunengzhe.PVMTS.service.PointInfoService;
/**
 * @ClassName:IvEquipmentController
 * @Description:TODO(iv多通道测试设备)
 * @author chenguiyang
 * @date 2017年7月11日下午1:53:57
 */
@Controller
@RequestMapping("/ivEquipment")
public class IvEquipmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(IvEquipmentController.class);
	
	@Autowired
	private IvEquipmentService ivEquipmentService;
	@Autowired
	private PointInfoService pointInfoService;

	
	/**
	 * @Title:getIvEquipments
	 * @Description:TODO(获取iv多通道测试设备列表) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/getIvEquipments",method=RequestMethod.GET)
	@ResponseBody
	public Object getIvEquipments(int powerstationId,HttpServletRequest request,HttpServletResponse response,Integer page,Integer pagesize,String ids){
		try {
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			ResultListVO<IvEquipmentVO> result = ivEquipmentService.getIvEquipments(powerstationId,  page, pagesize,ids);
			return RestResponse.responseList(request, result.getCounts(), result.getResultList()); 
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
	/**
	 * @Title:getIvEquipments
	 * @Description:TODO(获取iv多通道测试设备列表) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/getIvEquipmentThird",method=RequestMethod.GET)
	@ResponseBody
	public Object getIvEquipmentThird(int powerstationId,String equipmentIds,HttpServletRequest request,HttpServletResponse response,Integer page,Integer pagesize){
		try {
			List<IvEquipmentVO> result = ivEquipmentService.getIvEquipmentThird(powerstationId,equipmentIds);
			return RestResponse.responseOk(result); 
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * @Title:getIvEquipments
	 * @Description:TODO(获取iv多通道测试设备通道编号，功率ID) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/getIvEquipmentInfos",method=RequestMethod.GET)
	@ResponseBody
	public Object getIvEquipmentInfos(int powerstationId,int equipmentType,int pointType,HttpServletRequest request,HttpServletResponse response){
		try {
			List<PointInfoPO> list = pointInfoService.getOnePointInequipments(powerstationId,equipmentType,pointType);
			return RestResponse.responseOk(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
