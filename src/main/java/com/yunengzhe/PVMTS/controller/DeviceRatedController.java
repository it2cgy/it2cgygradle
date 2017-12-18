package com.yunengzhe.PVMTS.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.RatesDTO;
import com.yunengzhe.PVMTS.domain.po.DeviceRatedPO;
import com.yunengzhe.PVMTS.domain.po.NormalizationPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.service.DeviceRatedIvService;
import com.yunengzhe.PVMTS.service.DeviceRatedService;
import com.yunengzhe.PVMTS.util.ApiClient;
import com.yunengzhe.PVMTS.util.http.ResponseData;
import com.yunengzhe.commons.util.JsonUtil;
import com.yunengzhe.commons.util.PropertiesUtil;

@Controller
@RequestMapping("/deviceRated")
public class DeviceRatedController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeviceRatedController.class);
	public static final String scada_collect_url = PropertiesUtil.getString("scada_collect_url");
	public static final String iv_collect_url = PropertiesUtil.getString("iv_collect_url");
	@Autowired
	private DeviceRatedService deviceRatedService;

	@Autowired
	private DeviceRatedIvService deviceRatedIvService;

	
	/**
	 * <p>Object</p>
	 * <p>Description:获取所有额定功率配置列表</p>
	 * @author ynz
	 */
	@RequestMapping(value="/getAllRated")
	@ResponseBody
	public Object getAllRated(HttpServletRequest request,HttpServletResponse response,Integer tabId){
		try {
			if(tabId==null){
				return RestResponse.responseError();
			}
			List<NormalizationPO> result = new ArrayList<NormalizationPO>();
			if(tabId==1){//获取西安采集库的额定功率列表
				String url = scada_collect_url+"/normalization/normalizationList";
				ResponseData responseData = ApiClient.get(url,null);
				if(responseData.getCode()<200 ||  responseData.getCode()>=300){
					return RestResponse.responseOk(null);
				}
				ResultListVO<NormalizationPO> resultList = (ResultListVO<NormalizationPO>) JsonUtil.jsonToBean(responseData.getBackData(), ResultListVO.class);
				result = resultList.getResultList();
			}else{//iv通道额定功率获取 西安业务库
				String url = iv_collect_url+"/normalization/normalizationList";
				ResponseData responseData = ApiClient.get(url,null);
				if(responseData.getCode()<200 ||  responseData.getCode()>=300){
					return RestResponse.responseOk(null);
				}
				ResultListVO<NormalizationPO> resultList = (ResultListVO<NormalizationPO>) JsonUtil.jsonToBean(responseData.getBackData(), ResultListVO.class);
				result = resultList.getResultList();
			}
			return RestResponse.responseOk(result);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * <p>Object</p>
	 * <p>Description:更新所有公率</p>
	 * @author ynz
	 */
	@RequestMapping(value="/updateAllRate")
	@ResponseBody
	public Object updateAllRate(@RequestBody RatesDTO ratesDTO,HttpServletRequest request,HttpServletResponse response){
		try {
			if(ratesDTO.getTabId()==null){
				return RestResponse.responseError();
			}
			if(ratesDTO.getTabId()==1){
				String url = scada_collect_url+"/normalization/edit";//更新采集库 额定功率
				ResponseData responseData = ApiClient.postJsonData(url, JsonUtil.beanToJson(ratesDTO));

				if(responseData.getCode()<200 ||  responseData.getCode()>=300){
					return RestResponse.responseOk(false);
				}
				return RestResponse.responseOk(true);
			}else{//iv通道额定功率获取
				//deviceRatedIvService.updateRate(ratesDTO);
				String url = iv_collect_url+"/normalization/edit";//更新采集库 额定功率
				ResponseData responseData = ApiClient.postJsonData(url, JsonUtil.beanToJson(ratesDTO));

				if(responseData.getCode()<200 ||  responseData.getCode()>=300){
					return RestResponse.responseOk(false);
				}
				return RestResponse.responseOk(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
