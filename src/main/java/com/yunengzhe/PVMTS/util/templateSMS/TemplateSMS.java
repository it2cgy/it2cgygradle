package com.yunengzhe.PVMTS.util.templateSMS;



import com.yunengzhe.PVMTS.util.templateSMS.client.JsonReqClient;

import net.sf.json.JSONObject;


public class TemplateSMS {
	private static String accountSid="31eebb9982d067568d3cd45982eb995a";
	private static String authToken="ff8b3df00d0fd0dab1235f8d04498a9c";
	private static String appId="69f41f4c4a41427e84e4bb54f8a83f46";
	private static String templateId="12483";
	
	/**
	 * @Description: 获取发送短信的验证码
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getSendSMSStatus(String to,String param){
		JsonReqClient client= new JsonReqClient();
		String result = client.templateSMS(accountSid, authToken, appId, templateId, to, param);
		JSONObject jsStr = JSONObject.fromObject(result);
		JSONObject statusMap = JSONObject.fromObject(jsStr.get("resp"));
		String status = SmallMessage.smallMessageStatus(statusMap.get("respCode")+"");
		return status;
	}
	/**
	 * @Description: 获取发送短信的验证码
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getSendSMSStatus(String to,String param,String templateId){
		JsonReqClient client= new JsonReqClient();
		String result = client.templateSMS(accountSid, authToken, appId, templateId, to, param);
		JSONObject jsStr = JSONObject.fromObject(result);
		JSONObject statusMap = JSONObject.fromObject(jsStr.get("resp"));
		String status = SmallMessage.smallMessageStatus(statusMap.get("respCode")+"");
		return status;
	}
}
