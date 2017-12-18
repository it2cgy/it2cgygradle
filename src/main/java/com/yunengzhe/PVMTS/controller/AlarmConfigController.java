package com.yunengzhe.PVMTS.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.EditAlarmConfigDTO;
import com.yunengzhe.PVMTS.domain.dto.alarm.AddAlarmConfigDTO;
import com.yunengzhe.PVMTS.domain.po.AlarmConfigPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.AlarmConfigService;
import com.yunengzhe.PVMTS.util.ApiClient;
import com.yunengzhe.PVMTS.util.http.ResponseData;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.util.JsonUtil;
import com.yunengzhe.commons.util.PropertiesUtil;

@Controller
@RequestMapping("/alarmConfig")
public class AlarmConfigController {
	
	private static final Logger logger = LoggerFactory.getLogger(AlarmConfigController.class);
	public static final String scada_collect_url = PropertiesUtil.getString("scada_collect_url");
	@Autowired
	private AlarmConfigService alarmConfigService;

	@RequestMapping(value="/configList")
	@ResponseBody
	public Object configList(Integer page,Integer pagesize,@RequestBody Map<String,String> map,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			} 
			String url = scada_collect_url+"/alarmConfig/configList?page="+page+"&pagesize="+pagesize;
			ResponseData responseData = ApiClient.postJsonData(url, JsonUtil.beanToJson(map));

			if(responseData.getCode()<200 ||  responseData.getCode()>=300){
				RestResponse.responseList(request, 0, new ArrayList());
			}
			ResultListVO<AlarmConfigPO> result = (ResultListVO<AlarmConfigPO>) JsonUtil.jsonToBean(responseData.getBackData(), ResultListVO.class);
			
			String local = (String)HttpSessionUtil.getAttribute("local");
			if("en_US".equals(local)){
				List<AlarmConfigPO> alarmList = result.getResultList();
				for(int i=0;i<alarmList.size();i++){
					AlarmConfigPO cv = (AlarmConfigPO)JsonUtil.jsonToBean(JsonUtil.beanToJson(alarmList.get(i)),AlarmConfigPO.class);
					cv.setEquipmentcontainerName(cv.getEquipmentcontainerNameEn());
					cv.setMeasurePointDiscription(cv.getPointEnglishName());
					alarmList.set(i, cv);
				}
			}
			
			return RestResponse.responseList(request, result.getCounts(), result.getResultList());
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	@RequestMapping(value="/configEdit")
	@ResponseBody
	public Object configEdit(@RequestBody EditAlarmConfigDTO editConfigDTO,Integer page,Integer pagesize,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			} 
			editConfigDTO.setUserid(user.getUserid());
			String url = scada_collect_url+"/alarmConfig/configEdit.do";
			ResponseData responseData = ApiClient.postJsonData(url, JsonUtil.beanToJson(editConfigDTO));

			if(responseData.getCode()<200 ||  responseData.getCode()>=300){
				RestResponse.responseList(request, 0, new ArrayList());
			}
			return RestResponse.responseOk(responseData.getBackData());
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	@RequestMapping(value="/getconfigById/{id}")
	@ResponseBody
	public Object getconfigById(@PathVariable Integer id,Integer page,Integer pagesize,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			} 
			String url = scada_collect_url+"/alarmConfig/getconfigById/"+id;
			ResponseData responseData = ApiClient.get(url, null);

			if(responseData.getCode()<200 ||  responseData.getCode()>=300){
				RestResponse.responseList(request, 0, new ArrayList());
			}
			AlarmConfigPO result = (AlarmConfigPO) JsonUtil.jsonToBean(responseData.getBackData(), AlarmConfigPO.class);
			
			String local = (String)HttpSessionUtil.getAttribute("local");
			if("en_US".equals(local)){
					result.setEquipmentcontainerName(result.getEquipmentcontainerNameEn());
					result.setMeasurePointDiscription(result.getPointEnglishName());
			}
			
			return RestResponse.responseOk(result);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object deleteUsers(String ids,Integer page,Integer pagesize,HttpServletRequest request,HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(ids)){
				String url = scada_collect_url+"/alarmConfig/delete?ids="+ids;
				ResponseData responseData = ApiClient.get(url, null);

				if(responseData.getCode()<200 ||  responseData.getCode()>=300){
					return RestResponse.responseError();
				}
				//ResultListVO<AlarmConfigPO> result = (ResultListVO<AlarmConfigPO>) JsonUtil.jsonToBean(responseData.getBackData(), ResultListVO.class);
				//alarmConfigService.delConfig(ids); 
			} 
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}

		return RestResponse.responseOk();
	}
	@RequestMapping(value="/configadd")
	@ResponseBody
	public Object configList(@RequestBody AddAlarmConfigDTO addConfigDTO,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> results = new HashMap<String,Object>();
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			if(addConfigDTO.getType()==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "type is null");
			}
			if(addConfigDTO.getPointids()==null || addConfigDTO.getPointids().size()<=0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "points is null");
			}
			if(StringUtils.isBlank(addConfigDTO.getMessage())){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "message is null");
			}
			if(addConfigDTO.getValueOne()==null && addConfigDTO.getValueTwo()==null && addConfigDTO.getValueThree()==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "all value is null");
			}
			addConfigDTO.setUserid(user.getUserid());
			String url = scada_collect_url+"/alarmConfig/configadd";
			ResponseData responseData = ApiClient.postJsonData(url, JsonUtil.beanToJson(addConfigDTO));

			if(responseData.getCode()<200 ||  responseData.getCode()>=300){
				RestResponse.responseList(request, 0, new ArrayList());
			}
			//Integer count = Integer.valueOf(responseData.getBackData());
		
			//int count = alarmConfigService.addConfig(addConfigDTO, user.getUserid());
//			if(count!=null){
//
//				results.put("addcount", count);
//			}else{
//				results.put("addcount", 0);
//			}
			return results;
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}


}
