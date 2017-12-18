package com.yunengzhe.PVMTS.controller;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.PVMTS.domain.po.EquipmentPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.EquipmentVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.AttachmentService;
import com.yunengzhe.PVMTS.service.EquipmentService;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);
	
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private AttachmentService attachmentService;
	

	@Autowired
	private PowerStationService powerStationService;
	/**
	 * 获取设备列表
	 * @param request
	 * @param response
	 * @param sourse
	 * @param page
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/getEquipmentList/{page}/{pagesize}/{powerStationId}/{sourse}",method=RequestMethod.GET)
	@ResponseBody
	public Object getEquipmentList(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer page,@PathVariable Integer pagesize,@PathVariable Integer powerStationId,@PathVariable String sourse){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			if(page==null){
				page=1;
			}
			if(pagesize==null){
				pagesize = Paginator.ALARM_MAX_PAGESIZE;
			}
			if(sourse==""){
				sourse=null;
			}
			String third = null;
			if(user.getRoleList().get(0).getRoleId()==4){
				third = "第三方";
			}
			List<EquipmentPO> list= equipmentService.getEquipmentList(page,pagesize,powerStationId,sourse = URLDecoder.decode(sourse,"UTF-8"),third);
			ResultListVO<EquipmentPO> resultListVO = new ResultListVO<EquipmentPO>();
			resultListVO.setCounts(equipmentService.getCount(powerStationId,sourse,third));
			resultListVO.setResultList(list);
			return RestResponse.responseList(request, resultListVO.getCounts(), resultListVO.getResultList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
	}
	/**
	 * 获取设备列表
	 * @param request
	 * @param response
	 * @param sourse
	 * @param page
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/getEquipmentList",method=RequestMethod.POST)
	@ResponseBody
	public Object getEquipmentList(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object>map){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			Integer page = 1;
			if(!StringUtil.empty(map.get("page").toString())){
				page= Integer.valueOf(map.get("page").toString());
			}
			Integer pagesize = Paginator.ALARM_MAX_PAGESIZE;
			if(!StringUtil.empty(map.get("pagesize").toString())){
				pagesize= Integer.valueOf(map.get("pagesize").toString());
			}
			String sourse =null;
			if(!StringUtil.empty(map.get("sourse").toString())){
				sourse=map.get("sourse").toString();
			}
			String third = null;
			if(user.getRoleList().get(0).getRoleId()==4){
				third = "第三方";
			}
			Integer powerStationId = Integer.valueOf(map.get("powerStationId").toString());
			List<EquipmentPO> list= equipmentService.getEquipmentList(page,pagesize,powerStationId,sourse,third);
			ResultListVO<EquipmentPO> resultListVO = new ResultListVO<EquipmentPO>();
			resultListVO.setCounts(equipmentService.getCount(powerStationId,sourse,third));
			resultListVO.setResultList(list);
			return RestResponse.responseList(request, resultListVO.getCounts(), resultListVO.getResultList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
	}
	/**
	 * 添加设备
	 * @param request
	 * @param response
	 * @param equipmentPO
	 * @return
	 */
	@RequestMapping(value = "/addEquipment",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO addEquipment(HttpServletRequest request,HttpServletResponse response,@RequestBody EquipmentVO equipmentVO){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			EquipmentPO equipmentPO = new EquipmentPO();
			equipmentPO.setDeprecition(equipmentVO.getDeprecition());
			equipmentPO.setPowerstationid(equipmentVO.getPowerstationid());
			equipmentPO.setEquipmentModel(equipmentVO.getEquipmentModel());
			equipmentPO.setEquipmentNumber(equipmentVO.getEquipmentNumber());
			equipmentPO.setEquipmentType(equipmentVO.getEquipmentType());
			equipmentPO.setFactory(equipmentVO.getFactory());
			equipmentPO.setFactoryDate(equipmentVO.getFactoryDate());
			equipmentPO.setLifetime(equipmentVO.getLifetime());
			equipmentPO.setPrice(equipmentVO.getPrice());
			equipmentPO.setProducedDate(equipmentVO.getProducedDate());
			equipmentPO.setRemark(equipmentVO.getRemark());
			equipmentPO.setServiceExpense(equipmentVO.getServiceExpense());
			equipmentPO.setUserId(user.getUserid());
			boolean addEquipment = equipmentService.addEquipment(equipmentPO);
			if(equipmentVO.getFilepath()!=""&&equipmentVO.getFilepath()!=null){
				String[] filename = equipmentVO.getFilename().split(",");
				String[] filepath = equipmentVO.getFilepath().split(",");
				String[] remark = equipmentVO.getRemarks().split(",");
				AttachmentPO attachmentPO = new AttachmentPO();
				for (int i = 0; i < filepath.length; i++) {
					if(filename.length>0&&filename!=null){
						if(filename[i].equals("0")){
							attachmentPO.setFilename(null);
						}else{
							attachmentPO.setFilename(filename[i]);
						}
					}
					attachmentPO.setFilepath(filepath[i]);
					if(remark[i].indexOf("\\")<0){
					}else{
						remark[i]=remark[i].substring(remark[i].lastIndexOf("\\")+1, remark[i].length());
					}
					attachmentPO.setRemarks(remark[i]);
					attachmentPO.setId(null);
					attachmentPO.setFiletype("equipment");
					attachmentPO.setUploadUser(user.getUserid());
					attachmentPO.setFormId(equipmentPO.getId()+"");
					attachmentService.saveAttachment(attachmentPO);
				}
			}
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
	}
	/**
	 * 修改设备
	 * @param request
	 * @param response
	 * @param equipmentPO
	 * @return
	 */
	@RequestMapping(value = "/updateEquipment",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO updateEquipment(HttpServletRequest request,HttpServletResponse response,@RequestBody EquipmentVO equipmentVO){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			EquipmentPO equipmentPO = new EquipmentPO();
			equipmentPO.setId(equipmentVO.getId());
			equipmentPO.setDeprecition(equipmentVO.getDeprecition());
			equipmentPO.setPowerstationid(equipmentVO.getPowerstationid());
			equipmentPO.setEquipmentModel(equipmentVO.getEquipmentModel());
			equipmentPO.setEquipmentNumber(equipmentVO.getEquipmentNumber());
			equipmentPO.setEquipmentType(equipmentVO.getEquipmentType());
			equipmentPO.setFactory(equipmentVO.getFactory());
			equipmentPO.setFactoryDate(equipmentVO.getFactoryDate());
			equipmentPO.setLifetime(equipmentVO.getLifetime());
			equipmentPO.setPrice(equipmentVO.getPrice());
			equipmentPO.setProducedDate(equipmentVO.getProducedDate());
			equipmentPO.setRemark(equipmentVO.getRemark());
			equipmentPO.setServiceExpense(equipmentVO.getServiceExpense());
			equipmentPO.setUserId(user.getUserid());
			boolean addEquipment = equipmentService.updateEquipment(equipmentPO);
			AttachmentPO attachmentPO = new AttachmentPO();
			attachmentPO.setFiletype("equipment");
			attachmentPO.setFormId(equipmentPO.getId()+"");
			attachmentService.deleteAttachment(attachmentPO);
			if(equipmentVO.getFilepath()!=""&&equipmentVO.getFilepath()!=null){
				String[] filename = equipmentVO.getFilename().split(",");
				String[] filepath = equipmentVO.getFilepath().split(",");
				String[] remark = equipmentVO.getRemarks().split(",");
				for (int i = 0; i < filepath.length; i++) {
					if(filename.length>0&&filename!=null){
						if(filename[i].equals("0")){
							attachmentPO.setFilename(null);
						}else{
							attachmentPO.setFilename(filename[i]);
						}
					}
					attachmentPO.setFilepath(filepath[i]);
					int indexOf = remark[i].indexOf("\\");
					if(indexOf>=0){
						remark[i]=remark[i].substring(remark[i].lastIndexOf("\\")+1, remark[i].length());
					}
					attachmentPO.setRemarks(remark[i]);
					attachmentPO.setId(null);
					attachmentPO.setUploadUser(user.getUserid());
					attachmentService.saveAttachment(attachmentPO);
				}
			}
			return RestResponse.responseOk(addEquipment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
	}
	/**
	 * 删除设备
	 * @param request
	 * @param response
	 * @param equipmentPO
	 * @return
	 */
	@RequestMapping(value = "/deletEquipment/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO deletEquipment(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer id){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			boolean deleteEquipment = equipmentService.deleteEquipment(id);
			return RestResponse.responseOk(deleteEquipment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
	}
	/**
	 * 根据id获取详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getEquipment/{id}",method=RequestMethod.GET)
	@ResponseBody
	public EquipmentVO getEquipment(@PathVariable Integer id) {
		EquipmentPO equipmentPO = equipmentService.getEquipment(id);
		EquipmentVO equipmentVO = new EquipmentVO();
		equipmentVO.setCreateTime(equipmentPO.getCreateTime());
		equipmentVO.setDeprecition(equipmentPO.getDeprecition());
		equipmentVO.setPowerstationid(equipmentPO.getPowerstationid());
		equipmentVO.setEquipmentModel(equipmentPO.getEquipmentModel());
		equipmentVO.setEquipmentNumber(equipmentPO.getEquipmentNumber());
		equipmentVO.setEquipmentType(equipmentPO.getEquipmentType());
		equipmentVO.setFactory(equipmentPO.getFactory());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(equipmentPO.getFactoryDate()!=null){
			equipmentVO.setFactoryDate(dateFormat.format(equipmentPO.getFactoryDate()));
		}
		equipmentVO.setId(equipmentPO.getId());
		equipmentVO.setLifetime(equipmentPO.getLifetime());
		equipmentVO.setPrice(equipmentPO.getPrice());
		if(equipmentPO.getProducedDate()!=null){
			equipmentVO.setProducedDate(dateFormat.format(equipmentPO.getProducedDate()));
		}
		equipmentVO.setRemark(equipmentPO.getRemark());
		equipmentVO.setServiceExpense(equipmentPO.getServiceExpense());
		equipmentVO.setUpdateTime(equipmentPO.getUpdateTime());
		equipmentVO.setUserId(equipmentPO.getUserId());
		AttachmentPO attachmentPO = new AttachmentPO();
		attachmentPO.setFormId(id+"");
		attachmentPO.setFiletype("equipment");
		equipmentVO.setAttachmentPO(attachmentService.getAttaList(attachmentPO));
		
		PowerStationBaseInfoVO powerStation = powerStationService.getPowerStationInfo(equipmentPO.getPowerstationid());
		if(powerStation!=null){
			equipmentVO.setPowerstationName(powerStation.getName());
		}
		return equipmentVO;
	}
	/**
	 * 校验同编号
	 * @param request
	 * @param response
	 * @param equipmentPO
	 * @return
	 */
	@RequestMapping(value = "/checkEquipmentNumber",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO checkEquipmentNumber(@RequestBody Map<String,String>map,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			String number = map.get("number");
			String id = map.get("id");
			if(number==null){
				return RestResponse.responseOk(0);
			}
//			if(flag == 1){
//				number = "#"+number;
//			}
			List<EquipmentPO> checkEquipmentNumber = equipmentService.checkEquipmentNumber(number,id);
			return RestResponse.responseOk(checkEquipmentNumber.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统报错");
		}
	}
}
