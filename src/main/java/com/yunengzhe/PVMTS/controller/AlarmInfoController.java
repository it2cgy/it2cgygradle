package com.yunengzhe.PVMTS.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.yunengzhe.PVMTS.domain.dto.AlarmMessageDTO;
import com.yunengzhe.PVMTS.domain.po.AlarmInfoPO;
import com.yunengzhe.PVMTS.domain.po.AlarmUserInfoPO;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.PVMTS.domain.po.FaultInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.AlarmBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.AlarmInfoVO;
import com.yunengzhe.PVMTS.domain.vo.AlarmVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationListVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.AlarmInfoService;
import com.yunengzhe.PVMTS.service.AlarmUserInfoService;
import com.yunengzhe.PVMTS.service.AttachmentService;
import com.yunengzhe.PVMTS.service.FaultInfoService;
import com.yunengzhe.PVMTS.service.MessageSystemInfoService;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.service.UserService;
import com.yunengzhe.PVMTS.util.AlarmCreateWord;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.util.TimeUtil;

@Controller
@RequestMapping("/alarmInfo")
public class AlarmInfoController {

	private static final Logger logger = LoggerFactory.getLogger(AlarmInfoController.class);

	@Autowired
	private AlarmInfoService alarmInfoService;

	@Autowired
	private FaultInfoService faultInfoService;

	@Autowired
	private PowerStationService powerStationService;

	@Autowired
	private AlarmUserInfoService alarmUserInfoService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private MessageSystemInfoService messageSystemInfoService;

	@Autowired
	private UserService userService;
	/**
	 * 获取该用户未读报警总条数
	 * 参数 报警表id
	 * 返回值 是否成功
	 * @param
	 * @return
	 */
	//String token = "DA58A5485E52B2A5DA3EA90F367A1636";
	@RequestMapping(value = "/getNoReadCount",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO getNoReadCount(HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			int count = alarmInfoService.getNoReadCount(user.getUserid());
			return RestResponse.responseOk(count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取报警详情
	 * 参数报警表id
	 * 返回值报警详情表(电站信息,近三个月历史报警信息，附件)
	 * @param id
	 * @return  已测
	 */
	@RequestMapping(value = "/getAlarmByid/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO getAlarmByid(@PathVariable int id,HttpServletRequest request,HttpServletResponse response){
		AlarmInfoPO alarmInfoData;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		//获取报警详情
		try {
			alarmInfoData = alarmInfoService.getAlarmByid(id);
			if(alarmInfoData==null){
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "请求id参数不正确");
			}
			//设备类型
			//获取电站信息
			if(alarmInfoData.getPowerStationId()!=null){
				PowerStationBaseInfoVO ammeterBaseInfo = powerStationService.getPowerStationInfo(alarmInfoData.getPowerStationId()+"");
				if(ammeterBaseInfo!=null){
					alarmInfoData.setPowerStationBaseInfo(ammeterBaseInfo);
				}
			}
			//获取附件信息
			AttachmentPO atta = new AttachmentPO();
			atta.setFormId(id+"");
			atta.setFiletype("alarm");
			List<AttachmentPO> attaList = attachmentService.getAttaList(atta);
			if(attaList!=null){
				alarmInfoData.setAttachmentList(attaList);
			}
			//查看详情后，加入已读用户表
			AlarmUserInfoPO alarmUserInfo = alarmUserInfoService.checkIsRead(alarmInfoData.getId(),user.getUserid());
			if(alarmUserInfo==null){
				alarmUserInfo = new AlarmUserInfoPO();
				alarmUserInfo.setAlarmId(alarmInfoData.getId());
				alarmUserInfo.setUserId(user.getUserid());
				alarmUserInfoService.insert(alarmUserInfo);
			}
			//获取该电站三个月历史记录
			if(alarmInfoData.getPowerStationId()!=null){
				List<AlarmInfoPO> alarmHostoryList = alarmInfoService.getAlarmHostoryList(alarmInfoData.getPowerStationId());
				if(alarmHostoryList!=null){
					alarmInfoData.setAlarmInfoList(alarmHostoryList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
		return RestResponse.responseOk(alarmInfoData);
	}
	/**
	 * 获取报警列表
	 * 参数int alarmGrade报警级别,String alarmMessage报警原因,String beforeTime报警时间范围前搜索,String lateTime报警时间范围之后搜索,int pageSize显示条数,int powerStationId电站
	 * 返回值 报警详情List和未提交总条数
	 * @param
	 * @return已测
	 */
	@RequestMapping(value = "/getAlarmList",method=RequestMethod.GET)
	@ResponseBody
	public Object getAlarmList(HttpServletRequest request,HttpServletResponse response,AlarmVO alarm){
		ResultListVO<AlarmInfoVO> vo = null;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			if(alarm.getPage()==null){
				alarm.setPage(1);
			}
			if(alarm.getPagesize()==null){
				alarm.setPagesize(Paginator.ALARM_MAX_PAGESIZE);
			}
			if(alarm.getStatus()==null||alarm.getStatus()==1||alarm.getStatus()==0){
				vo = alarmInfoService.getAlarmList(alarm,user.getUserid());
			}else{
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "status参数不对");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
		logger.debug("getAlarmList------------>"+vo.toString());
		return RestResponse.responseList(request, vo.getCounts(), vo.getResultList());
	}
	/**
	 * 获取报警列表
	 * 运维web页面搜索参数
	 * 返回值 报警详情List和未提交总条数
	 * @param
	 * @return已测
	 */
	@RequestMapping(value = "/getAlarmListBySearch",method=RequestMethod.GET)
	@ResponseBody 
	public Object getAlarmListBySearch(HttpServletRequest request,HttpServletResponse response,String search,Integer page,Integer pagesize,Integer status,Integer powerStationId){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		List<AlarmInfoPO> alarmListInfo;
		ResultListVO<AlarmInfoPO> vo = new ResultListVO<AlarmInfoPO>();
		try {
			String search2="";
			if(StringUtils.isNotBlank(search)){
				search2 = URLDecoder.decode(search,"UTF-8");
			}
			if(page==null){
				page=1;
			}
			if(pagesize==null){
				pagesize = Paginator.ALARM_MAX_PAGESIZE;
			}
			String third=null;
			if(user.getRoleList().get(0).getRoleId()==4){
				third="第三方";
			}
			alarmListInfo = alarmInfoService.getAlarmListBySearch(search2,status,powerStationId,page,pagesize,third,null,null,null);
			vo.setResultList(alarmListInfo);
			vo.setCounts(alarmInfoService.getCountBySearch(search2,status,powerStationId,third,null,null,null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
		logger.info("获取报警列表---"+alarmListInfo.toString());
		return RestResponse.responseList(request, vo.getCounts(), vo.getResultList());
	}
	/**
	 * 获取报警列表
	 * 运维web页面搜索参数
	 * 返回值 报警详情List和未提交总条数
	 * @param
	 * @return已测
	 */
	@RequestMapping(value = "/getAlarmListBySearchPost",method=RequestMethod.POST)
	@ResponseBody 
	public Object getAlarmListBySearchPost(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String>map){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		List<AlarmInfoPO> alarmListInfo;
		ResultListVO<AlarmInfoPO> vo = new ResultListVO<AlarmInfoPO>();
		try {
			String search = (String) map.get("search");
			String search2="";
			if(StringUtils.isNotBlank(search)){
				search2 = URLDecoder.decode(search,"UTF-8");
			}
			
			if(map.get("powerStationId")!=null){ 
				RestResponse.responseList(request, 0, new ArrayList());
			}
			String equipmentName = (String) map.get("equipmentName");
			String equipmentName2="";
			if(StringUtils.isNotBlank(equipmentName)){
				equipmentName2 = URLDecoder.decode(equipmentName,"UTF-8");
			}
			String pointName = map.get("pointName").toString();
			String pointName2="";
			if(StringUtils.isNotBlank(pointName)){
				pointName2 = URLDecoder.decode(pointName,"UTF-8");
			}

			String appSeachName = (String) map.get("appSeachName"); 
			String appSeachName2="";
			if(StringUtils.isNotBlank(appSeachName)){
				appSeachName2 = URLDecoder.decode(appSeachName,"UTF-8");
			}
			Integer page = Integer.valueOf(map.get("page"));
			if(page==null){
				page=1;
			}
			Integer pagesize = Integer.valueOf(map.get("pagesize"));
			if(pagesize==null){
				pagesize = Paginator.ALARM_MAX_PAGESIZE;
			}
			Integer status = Integer.valueOf(map.get("status"));
			Integer powerStationId = null;
			if(map.get("powerStationId")!=null){ 
				Integer.valueOf(map.get("powerStationId"));
			}
			String third=null;
			if(user.getRoleList().get(0).getRoleId()==4){
				third="第三方";
			}
			alarmListInfo = alarmInfoService.getAlarmListBySearch(search2,status,powerStationId,page,pagesize,third,equipmentName2,pointName2,appSeachName2);
			vo.setResultList(alarmListInfo);
			vo.setCounts(alarmInfoService.getCountBySearch(search2,status,powerStationId,third,equipmentName2,pointName2,appSeachName2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
		logger.info("获取报警列表---"+alarmListInfo.toString());
		return RestResponse.responseList(request, vo.getCounts(), vo.getResultList());
	}
	/**
	 * 删除报警
	 * 参数 报警表id
	 * 返回值 是否成功
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/delete/{alarmId}",method=RequestMethod.GET)
	@ResponseBody
	public Object delete(HttpServletRequest request,HttpServletResponse response,@PathVariable int alarmId){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		boolean flag;
		try {
			flag = alarmInfoService.delete(alarmId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
		return RestResponse.responseOk(flag);
	}
	/**
	 * 添加报警
	 * 参数 报警表id
	 * 返回值 是否成功
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO insert(HttpServletRequest request,HttpServletResponse response,@RequestBody AlarmInfoPO alarm){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		boolean flag = false;
		try {
			flag = alarmInfoService.insert(alarm,user.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
		return RestResponse.responseOk(flag);
	}
	/**
	 * 更改报警状态
	 * 参数 说明及附件  报警id
	 * 确认以后，将信息存入故障表
	 * @param
	 * @return已测
	 */
	@RequestMapping(value = "/affirm",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO affirm(HttpServletRequest request,HttpServletResponse response,@RequestBody AlarmInfoPO alarmInfoPO){
		if(alarmInfoPO.getId()==null || alarmInfoPO.getId()<=0){
			return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请求id传值没有");
		}
		boolean insert = false;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			alarmInfoPO.setUid(user.getUserid());//报警确认人解决人
			AlarmInfoPO affirm = alarmInfoService.affirm(alarmInfoPO);
			if(affirm==null){
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "请求id参数不正确");
			}
			insert=affirm.getId()>0;
			logger.info("确认报警---"+affirm.toString());
			if(affirm.getId()!=null&&alarmInfoPO.getStatus()==1){//0未确认  1已确认  2已解决
				if(alarmInfoPO.getAttachmentList()!=null){
					try {
						for (AttachmentPO alarmInfoPO2 : alarmInfoPO.getAttachmentList()) {
							alarmInfoPO2.setId(null);
							alarmInfoPO2.setFormId(alarmInfoPO.getId()+"");
							alarmInfoPO2.setUploadUser(user.getUserid());
							alarmInfoPO2.setFiletype("alarm");
							attachmentService.saveAttachment(alarmInfoPO2);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "上传报警文件错误");
					}
				}
				FaultInfoPO faultInfoPO = new FaultInfoPO();
				faultInfoPO.setEquipmentId(affirm.getEquipmentId());
				faultInfoPO.setEquipmentType(affirm.getEquipmentType());
				faultInfoPO.setFaultGrade(affirm.getAlarmGrade());
				faultInfoPO.setFaultMessage(affirm.getAlarmMessage());
				faultInfoPO.setAlarmId(affirm.getId());
				//来源来自报警1  手动添加2
				faultInfoPO.setFaultSource(1);
				faultInfoPO.setUserId(user.getUserid());
				//状态默认0
				faultInfoPO.setFaultStatus(0);
				faultInfoPO.setPowerStationId(affirm.getPowerStationId());
				faultInfoPO.setState(affirm.getState());
				insert = faultInfoService.insert(faultInfoPO);
				if(alarmInfoPO.getAttachmentList()!=null){
					try {
						for (AttachmentPO alarmInfoPO2 : alarmInfoPO.getAttachmentList()) {
							alarmInfoPO2.setId(null);
							alarmInfoPO2.setFilename(null);
							alarmInfoPO2.setFilepath(null);
							alarmInfoPO2.setUploadTime(null);
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
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
		return RestResponse.responseOk(insert);
	}


	/**
	 * @Title:confirmAlarmById
	 * @Description:TODO(确认报警 --单条 根据报警id更新状态) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/confirmAlarmById")
	@ResponseBody
	public Object confirmAlarmById(@RequestBody AlarmInfoPO alarmInfoPO,HttpServletRequest request,HttpServletResponse responses){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			alarmInfoPO.setStatus(1);
			alarmInfoPO.setUid(user.getUserid());
			alarmInfoPO.setHandler(user.getNickname());
			alarmInfoPO.setHandTime(new Date());
			return RestResponse.responseOk(alarmInfoService.confirmAlarm(alarmInfoPO));	
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * @Title:confirmAlarmById
	 * @Description:TODO(确认报警 --单条 根据报警id更新状态) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/msgConfirmAlarmById",method=RequestMethod.POST)
	@ResponseBody
	public Object msgConfirmAlarmById(@RequestBody AlarmMessageDTO alarmMessageDTO,HttpServletRequest request,HttpServletResponse responses){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			int alarmId = alarmMessageDTO.getId();
			String opreateMessage = alarmMessageDTO.getMessage();
			logger.info("确认报警信息"+alarmId);
			AlarmInfoPO alarm = new AlarmInfoPO();
			alarm.setId(alarmId);
			alarm.setStatus(1);
			alarm.setUid(user.getUserid());//报警确认人解决人
			alarm.setOpreateMessage(opreateMessage);
			alarmInfoService.confirmAlarm(alarm);
			return RestResponse.responseOk(true);	
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * @throws IOException 
	 * @Title:getReportDetails
	 * @Description:TODO下载
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/downloadAlarm")
	public String  downloadAlarm(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return null;
		}
		Date date = new Date(System.currentTimeMillis());

		AlarmInfoPO alarmByid = alarmInfoService.getAlarmDispose(id);
		PowerStationListVO powerInfoById = powerStationService.getPowerInfoById(alarmByid.getPowerStationId()+"");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("alarmMessage", alarmByid.getAlarmMessage());
		map.put("mid", "LJSZDZ"+TimeUtil.date2String(alarmByid.getHandTime(), "yyyyMMddHHmm")+alarmByid.getEquipmentcontainerName()+"/"+alarmByid.getId());
		map.put("eventTime", alarmByid.getEventTime());
		map.put("descriptionType", alarmByid.getDescriptionType());
		map.put("equipmentcontainerName", alarmByid.getEquipmentcontainerName());
		map.put("alarmGrade", alarmByid.getAlarmGrade());
		map.put("handling", alarmByid.getHandling());
		map.put("handTime", alarmByid.getHandTime());
		map.put("handler", alarmByid.getHandler());
		map.put("powerStationName", powerInfoById.getPowerStationName());
		map.put("investFirmName", powerInfoById.getInvestFirmName());
		map.put("corporation", alarmByid.getCorporation());
		map.put("remaker", alarmByid.getRemaker());
		map.put("measurePointDiscription", alarmByid.getMeasurePointDiscription());
		File file = null;  
		InputStream fin = null;  
		OutputStream out = response.getOutputStream();  
		try {
			// 调用工具类WordGenerator的createDoc方法生成Word文档  
			file = AlarmCreateWord.createDoc(map,"alarm", "longieb",null);
			// 以流的形式下载文件。
			fin = new FileInputStream(file);
			response.setCharacterEncoding("utf-8");  
			response.setContentType("application/wsword");  
			//设置浏览器以下载的方式处理该文件默认名为resume.doc  
			response.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("报警处理报告", "UTF-8")+".doc");  
			out = response.getOutputStream();
			byte[] buffer = new byte[512];  // 缓冲区  
			int bytesToRead = -1;
			while((bytesToRead = fin.read(buffer)) != -1) {  
				out.write(buffer, 0, bytesToRead);  
			}
		} finally {  
			if(fin != null) fin.close();  
			if(out != null) out.flush();  
			//if(file != null) file.delete(); // 删除临时doc文件  
			//if(filepdf != null) filepdf.delete(); // 删除临时pdf文件  
		}  
		return null;
	}

	/**
	 * @Title:confirmAlarmById
	 * @Description:TODO(获取报警详情) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/getAlarmBaseInfo/{id}")
	@ResponseBody
	public Object getAlarmBaseInfo(@PathVariable Integer id,HttpServletRequest request,HttpServletResponse responses){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			AlarmInfoPO alarmByid = alarmInfoService.getAlarmDispose(id);
			PowerStationListVO powerInfoById = powerStationService.getPowerInfoById(alarmByid.getPowerStationId()+"");
			AlarmBaseInfoVO av = new AlarmBaseInfoVO();
			av.setaInfo(alarmByid);
			av.setPwInfo(powerInfoById);
			return RestResponse.responseOk(av);	
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
