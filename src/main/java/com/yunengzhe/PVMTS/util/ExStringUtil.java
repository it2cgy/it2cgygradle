package com.yunengzhe.PVMTS.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理字符串Util
 * <p>Title:</p>
 * @author 陈贵阳
 * <p>Description:</p>
 * @date 2016-7-28 下午08:23:10
 */
public class ExStringUtil {

	/**
	 * 判断OBJECT是否为空或NULL
	 * @param arg
	 * @return
	 */
	public static boolean isEmptyOrNull(Object arg){
		return arg == null || "".equals(arg.toString().trim());
	}
	
	/**
	 * Object转换为字符串
	 * @return
	 */
	public static String toString(Object obj){
		return isEmptyOrNull(obj) ? "" : obj.toString();
	}
	
	/**
	 * 转换obj为字符串
	 * @param obj
	 * @param str
	 * @return
	 */
	public static String toString(Object obj,String str){
		return isEmptyOrNull(obj) ? str : obj.toString();
	}
	
	/**
	 * Map取值
	 * @param map
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getValueFromMap(Map map,String key){
		return map.get(key) == null ? "" : String.valueOf(map.get(key));
	}
	
	/**
	 * 转换ISO字符集到UTF-8
	 * @return
	 */
	public static String getISOToUTF8(String str){
		String strName = "";
		try {
			if(str != null){
				strName = new String(str.getBytes("ISO9959_1"),"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strName;
	}
	
	/**
	 * 判断是否为中文
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str){
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static int toInt(Object object) {
		return Integer.parseInt((String) object);
	}
	
	/**
	 * 转换为double
	 * @return
	 */
	public static double toDouble(Object object){
		return Double.parseDouble((String) object);
	}
	
	
	
	
	
	
}
