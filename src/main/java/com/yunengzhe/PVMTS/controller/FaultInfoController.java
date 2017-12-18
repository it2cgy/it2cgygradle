package com.yunengzhe.PVMTS.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.yunengzhe.PVMTS.domain.dto.FaultSolveDTO;
import com.yunengzhe.PVMTS.domain.po.AlarmInfoPO;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.PVMTS.domain.po.FaultInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.FaultInfoVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.AlarmInfoService;
import com.yunengzhe.PVMTS.service.AttachmentService;
import com.yunengzhe.PVMTS.service.FaultInfoService;
import com.yunengzhe.PVMTS.service.FaultSolveService;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.util.FileUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/faultInfo")
public class FaultInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(FaultInfoController.class);
	@Autowired
	private FaultInfoService faultInfoService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private PowerStationService powerStationService;
	
	@Autowired
	private AlarmInfoService alarmInfoService;
	@Autowired
	private FaultSolveService faultSolveService;

	/**
	 * 请求故障详情接口
	 * 参数故障表id
	 * 返回故障对象
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getFaultInfo/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO getFaultInfo(@PathVariable int id,HttpServletRequest request,HttpServletResponse response){
		FaultInfoPO faultInfo = null;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			faultInfo = faultInfoService.getFaultInfo(id);
			AttachmentPO atta = new AttachmentPO();
			atta.setFormId(id+"");
			atta.setFiletype(FileUtil.WORKORDER_DISPATCHER);
			List<AttachmentPO> attaList = attachmentService.getAttaList(atta);
			if(attaList!=null){
				faultInfo.setDispatcherList(attaList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统出错");
		}
		logger.debug(faultInfo.toString());
		return RestResponse.responseOk(faultInfo);
	}
	/**
	 * 获取故障列表
	 * 运维web页面搜索参数
	 * 返回值 报警详情List和未提交总条数
	 * @param
	 * @return已测
	 */
	@RequestMapping(value = "/getAlarmList",method=RequestMethod.GET)
	@ResponseBody
	public List<FaultInfoPO> getFaultListBySearch(String search,Integer page,Integer pagesize){
		if(page==null){
			page = 1;
		}
		if(pagesize==null){
			pagesize = Paginator.DEFAULT_PAGESIZE;
		}
		List<FaultInfoPO> faultListBySearch = faultInfoService.getFaultListBySearch(search);
		return faultListBySearch;
	}
	/**
	 * 修改故障信息
	 * 参数 故障id  说明  
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateFault")
	@ResponseBody
	public RestResponseVO updateFault(HttpServletRequest request,HttpServletResponse response,@RequestBody FaultInfoVO faultInfoVO){
		boolean updateFault = false;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			FaultInfoPO faultInfoPO = new FaultInfoPO();
			faultInfoPO.setId(faultInfoVO.getId());
			faultInfoPO.setState(faultInfoVO.getState());
			faultInfoPO.setFaultStatus(faultInfoVO.getFaultStatus());
			updateFault = faultInfoService.updateFault(faultInfoPO);
			List<AttachmentPO> attachmentList = faultInfoVO.getAttachmentList();
			AttachmentPO alarmInfoPO2 = new AttachmentPO();
			alarmInfoPO2.setFormId(faultInfoPO.getId()+"");
			alarmInfoPO2.setFiletype("fault");
			attachmentService.deleteAttachment(alarmInfoPO2);
				try {
					if(attachmentList!=null){
						if(attachmentList.size()>0){
							for (AttachmentPO attachmentPO : attachmentList) {
								AttachmentPO alarmInfoPO3 = new AttachmentPO();
								alarmInfoPO3.setFormId(faultInfoVO.getId()+"");
								alarmInfoPO3.setFiletype("fault");
								alarmInfoPO3.setFilename(attachmentPO.getFilename());
								alarmInfoPO3.setFilepath(attachmentPO.getFilepath());
								alarmInfoPO3.setUploadUser(user.getUserid());
								attachmentService.saveAttachment(alarmInfoPO2);
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "上传故障文件错误");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "修改故障出错");
		}
		return RestResponse.responseOk(updateFault);
	}
	/**
	 * 用户手动添加故障
	 * 参数  电站id  设备类型   设备编号   故障级别  说明  
	 * 返回值  存储是否成功
	 * @param id
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public RestResponseVO insert(HttpServletRequest request,HttpServletResponse response,@RequestBody FaultInfoPO faultInfoPO){
		boolean insert = false;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			faultInfoPO.setFaultSource(2);
			faultInfoPO.setUserId(user.getUserid());//TODO
			insert = faultInfoService.insert(faultInfoPO);
			List<AttachmentPO> attachmentList = faultInfoPO.getAttachmentList();
			if(attachmentList!=null){
				try {
					for (AttachmentPO alarmInfoPO2 : attachmentList) {
						alarmInfoPO2.setFormId(faultInfoPO.getId()+"");
						alarmInfoPO2.setUploadUser(user.getUserid());
						alarmInfoPO2.setFiletype("fault");
						attachmentService.saveAttachment(alarmInfoPO2);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "上传故障文件错误");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统出错");
		} finally {
		}
		return RestResponse.responseOk(insert);
	}
	/**
	 * 我确认或发出的故障反馈
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getUserFaultList",method=RequestMethod.GET)
	@ResponseBody
	public Object getUserFaultList(HttpServletRequest request,HttpServletResponse response,Integer page,Integer pagesize){
		ResultListVO vo = null;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			vo = faultInfoService.getUserFaultList(user.getUserid(),page,pagesize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统出错");
		}
		logger.debug(vo.getResultList().toString());
		return  RestResponse.responseList(request, vo.getCounts(), vo.getResultList());
	}
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectHistoryList")
	@ResponseBody
	public List<FaultInfoPO> selectHistoryList(String search,int pagesize){
		List<FaultInfoPO> findAll = faultInfoService.selectHistoryList(search);
		return findAll;
	}
	
	/**
	 * @Title:saveFaultSolve
	 * @Description:TODO(保存故障处理信息) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/saveFaultSolve",method=RequestMethod.POST)
	@ResponseBody
	public Object saveFaultSolve(@RequestBody FaultSolveDTO param,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			 faultSolveService.updateSolve(param,user.getUserid());
			 faultInfoService.updateFaultInfo(param.getFaultId(),param.getSolveResult());
			 if(param.getSolveResult()==2){//2为已解决  当故障解决时 解除报警
				 AlarmInfoPO alarm = new AlarmInfoPO();
				 alarm.setId(param.getAlarmId());
				 alarm.setStatus(2);
				 alarmInfoService.affirm(alarm);
			 }
			 return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
