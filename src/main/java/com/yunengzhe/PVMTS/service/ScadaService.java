package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.domain.vo.AlarmHandleParam;
import com.yunengzhe.PVMTS.domain.vo.AlarmItemData;
import com.yunengzhe.PVMTS.domain.vo.AlarmItemsData;
import com.yunengzhe.PVMTS.domain.vo.AnalogStructureParam;
import com.yunengzhe.PVMTS.domain.vo.AnalogStructureResults;
import com.yunengzhe.PVMTS.domain.vo.HistoryDataResult;
import com.yunengzhe.PVMTS.domain.vo.HistoryDataResults;
import com.yunengzhe.PVMTS.domain.vo.QueryHisPointsParam;
import com.yunengzhe.PVMTS.domain.vo.QueryPointParam; 
import com.yunengzhe.PVMTS.util.ApiClient;
import com.yunengzhe.PVMTS.util.JsonUtil;
import com.yunengzhe.PVMTS.util.http.ResponseData;
import com.yunengzhe.commons.util.PropertiesUtil;

/**
 * 电表详细信息SERVICE
 * <p>Title:</p>
 * @author CHENGUIYANG
 * <p>Description:</p>
 * @date 2016-9-23 下午03:05:25
 */
@Service("scadaService")
public class ScadaService {
	public static Logger logger = LoggerFactory.getLogger(ScadaService.class); 

// 
//	/**
//	 * 请求历史数据
//	 * @param queryparam
//	 * @return
//	 */
//	public List<HistoryDataResult> getAnalogStructusHistoryData(QueryHisPointsParam queryparam ){
//		String queryparamStr = null;
//		HistoryDataResults resultsData = null;
//		try {  
//			queryparamStr = JsonUtil.beanToJson(queryparam);
//			ResponseData responseData = ApiClient.postJsonData(PropertiesUtil.getString("scada_api_url")+"/monitor/analoghistory",queryparamStr);
//			if(responseData.getCode()>=200 &&  responseData.getCode()<300){
//				resultsData = (HistoryDataResults) JsonUtil.jsonToBean(responseData.getBackData(), HistoryDataResults.class);
//			}
//		} catch (Exception e) {  
//			resultsData = null;
//			logger.error("发生异常",e);
//			e.printStackTrace();
//		}
// 
//		return resultsData.getResult();
//	}
//	
//
//	/**
//	 * 请求历史数据
//	 * @param queryparam
//	 * @return
//	 */
//	public HistoryDataResult getOneAnalogStructusHistoryData(QueryPointParam queryparam ){
//		String queryparamStr = null;
//		HistoryDataResults resultsData = null;
//		try {
//			QueryHisPointsParam param = new QueryHisPointsParam();
//			List<QueryPointParam> points = new ArrayList<QueryPointParam>();
//			points.add(queryparam);
//			param.setPointList(points);
//			queryparamStr = JsonUtil.beanToJson(param);
//			ResponseData responseData = ApiClient.postJsonData(PropertiesUtil.getString("scada_api_url")+"/monitor/analoghistory",queryparamStr);
//			if(responseData.getCode()>=200 &&  responseData.getCode()<300){
//				resultsData = (HistoryDataResults) JsonUtil.jsonToBean(responseData.getBackData(), HistoryDataResults.class);
//			}
//		} catch (Exception e) {  
//			resultsData = null;
//			logger.error("发生异常",e);
//			e.printStackTrace();
//		}
// 
//		return resultsData.getResult().get(0);
//	}
//	
//	/**
//	 * 请求实时报警数据
//	 * @param queryparam
//	 * @return
//	 */
//	public List<AlarmItemData> getAlarms(){
//		String queryparamStr = null;
//		AlarmItemsData resultsData = null;
//		try {  
//			ResponseData responseData = ApiClient.postJsonData(PropertiesUtil.getString("scada_api_url")+"/monitor/alarms","");
//			if(responseData.getCode()>=200 &&  responseData.getCode()<300){
//				resultsData = (AlarmItemsData) JsonUtil.jsonToBean(responseData.getBackData(), AlarmItemsData.class);
//			}
//		} catch (Exception e) {  
//			resultsData = null;
//			logger.error("发生异常",e);
//			e.printStackTrace();
//			return new ArrayList<AlarmItemData>();
//		}
// 
//		return resultsData.getResult();
//	}
// 
//	/**
//	 * 确认报警
//	 * @param queryparam
//	 * @return
//	 */
//	public void ackAlarm(AlarmHandleParam queryparam){
//		String queryparamStr = null;
//		AlarmItemsData resultsData = null;
//		try {  
//			queryparamStr = JsonUtil.beanToJson(queryparam);
//			ResponseData responseData = ApiClient.postJsonData(PropertiesUtil.getString("scada_api_url")+"/monitor/ackalarm",queryparamStr);
//			if(responseData.getCode()>=200 &&  responseData.getCode()<300){
//				logger.info("确认成功");
//				//resultsData = (AlarmItemsData) JsonUtil.jsonToBean(responseData.getBackData(), AlarmItemsData.class);
//			}
//		} catch (Exception e) {  
//			resultsData = null;
//			logger.error("发生异常",e);
//			e.printStackTrace(); 
//		}
//  
//	}
// 
}
