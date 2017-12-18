package com.yunengzhe.PVMTS.util.templateSMS;


import net.sf.json.JSONObject;

/**
 * @Description: 短信验证相关的方法
 * @author qinyanru
 * @date 2015年8月22日 下午4:46:12
 */
public class SmallMessage {

	/**
	 * @Description: 根据短信状态码，返回相应信息 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String smallMessageStatus(String status) {
		String resultCode = "";
		if ("000000".equals(status)) { 
			resultCode = "000000";
		}else if ("100008".equals(status)) {
			resultCode = "手机号码为空，请输入手机号码";
		}else if ("100006".equals(status)) {
			resultCode = "手机号不合法";
		}else if ("100015".equals(status)) {
			resultCode = "号码不合法";
	    }else if("100001".equals(status)){
	    	resultCode = "100001";
	    }  
		return resultCode;
	}
	
	/**
	 * @Description: 根据对比短信验证码的结果，返回对应的信息
	 * @return String    返回类型 
	 * @throws
	 */
	public static JSONObject comparySMStatus(int flag,String resultCode) {
		JSONObject object = new JSONObject();
		boolean result = false;
		if (flag == 0) { // 不在有效期内
			resultCode = "验证码不正确，请核对验证码！";
			result = false;
		}else if (flag == 2) {
			resultCode = "验证码不在有效期内，请重新发送！";
			result = false;
		}else if (flag == 1){
			// 有效期内
			result = true;
		}
		object.put("flag", flag);
		object.put("result", result);
		object.put("resultCode", resultCode);
		return object;
	}
	
	/**
	 * @Description: 发送新手机短信
	 * @return JSONObject    返回类型 
	 * @throws
	 */
	public static JSONObject sendNewMesssage(int flag,String resultCode,String to,String param) {
		boolean result = false;
		//判断短信验证码是否在有效期内
				switch (flag) {
				case 0:
					result = false;
					resultCode = "该电话号码已注册，请输入其他号码";
					break;
				case 1:
					result = false;
					resultCode = "验证码已发送过，不能重复发送";	
					break;
				case 2:
					String status = TemplateSMS.getSendSMSStatus(to, param);
				  
					if ("000000".equals(status)) { //成功发送验证码
//				    	map.put("code",random);
//				    	int a = userService.insertSmallMessage(map);
				    	resultCode = "success";
				    	result = true;
					}else if("100001".equals(status)){
						result = false;
						resultCode = "验证码发送失败，请及时联系管理员~";
					}else{
						result = false;
						resultCode = status;
					}
					break;
				}
				JSONObject object = new JSONObject();
				object.put("flag", flag);
				object.put("result", result);
				object.put("resultCode", resultCode);
		return object;
	}
	
}
