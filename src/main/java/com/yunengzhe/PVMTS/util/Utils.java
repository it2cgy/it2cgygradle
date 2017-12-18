package com.yunengzhe.PVMTS.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Utils {
	public static JSONObject jArrayTojObject(JSONArray array){
		String arrayStr = array.toString();
		return JSONObject.fromObject("{" + arrayStr.substring(1, arrayStr.length()-1) + "}");
	}
	
	/**
     * 判断字符串是否为空
     * @param str 待判断的字符串
     * @return
     */
    public static boolean isNull(String str){
    	boolean result = false;
		if (str != null && !"".equals(str) && !"null".equalsIgnoreCase(str) && !"NaN".equalsIgnoreCase(str)) {
			result = false;
		} else {
			result = true;
		}
		return result;
    }

}
