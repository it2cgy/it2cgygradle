/** 
 * 项目:PVMTS 
 * 文件名:ReadJson.java 
 * 包名:com.yunengzhe.PVMTS.util.Json 
 * 创建日期:2017年4月26日下午2:26:15 
 */ 
package com.yunengzhe.PVMTS.util.Json;

import com.yunengzhe.PVMTS.company.entity.LocalConfig;
import com.yunengzhe.commons.util.AESUtil;
import com.yunengzhe.commons.util.PropertiesUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReadJson {
	public static LocalConfig mpg;
	
	public static LocalConfig getMpg() {
		return mpg;
	}
	public static void setMpg(LocalConfig mpg) {
		ReadJson.mpg = mpg;
	}
//	public static String ReadJson(){
//		LocalConfig mpg= new LocalConfig();
//		String str= JsonUtil.ReadFile(Params.localConfigDES);
//		return str;  
//	}  
	static{
	    String config = JsonUtil.ReadFile(PropertiesUtil.getString("local_config"));
		String password = config.substring(0,8);
		String result1 = config.substring(8,config.length());
		String str = "";
		String decryResult;
		try {
			decryResult = AESUtil.decrypt(result1,password);
			str = new String(decryResult);
			System.out.println("解密后："+decryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mpg= new LocalConfig();
		JSONArray json = JSONArray.fromObject(str);
		JSONObject obj = json.getJSONObject(0);
		mpg.setProducttype("1");
		mpg.setIp(obj.get("ip")+"");
		mpg.setMac(obj.get("mac")+"");      
		mpg.setEndTime(obj.get("endTime")+"");
		mpg.setType(obj.get("type")+"");
		mpg.setAccountCount(Integer.valueOf(obj.get("accountCount")+""));
		mpg.setNumber(obj.get("number")+"");
		mpg.setUrl(obj.get("url")+"");
	}
}
