package com.yunengzhe.PVMTS.util;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.Enumeration;
import java.util.HashMap; 
import java.util.Map;
import java.util.Properties;
 

import ch.qos.logback.core.net.server.Client;

public class PropertiesUtil {
	 
	public static Map<String,String> config = new HashMap<String,String>();
	static{
		Properties propzh_cn = new Properties(); 
			try {
				File file = new File("./config/system.properties");
				if(!file.exists()){
					file = new File("system.properties");
				}
				System.out.println(file.getCanonicalPath());;
				if(file.exists()){ 
					propzh_cn.load(new InputStreamReader(new FileInputStream(file),"UTF-8"));
				}else{
					propzh_cn.load(new InputStreamReader(Client.class.getClassLoader().getResourceAsStream("system.properties"),"UTF-8"));
				}
			
			} catch (IOException e) {
				e.printStackTrace(); 
			}    
		Enumeration<Object> enums_zh = propzh_cn.keys();  
		while(enums_zh.hasMoreElements()){
			String namezh = (String) enums_zh.nextElement(); 
			String value = propzh_cn.getProperty(namezh, "");
			 System.out.println(namezh+":"+value);
			 config.put(namezh, value);
		}
	}

	
	public static String getValue(String key) {
		return config.get(key);
	}
	
	public static String getString(String key) {
		return config.get(key);
	}	
	
	
	public static String getString(String key, String defaultValue) {
		return config.get(key)==null?defaultValue:config.get(key);
	}
	 
	public static int getInt(String key) {
		String value = config.get(key);
		return Integer.valueOf(value);
	}
	
	public static int getInt(String key, int defaultValue) {
		String value = config.get(key);
		if(value==null){
			return defaultValue;
		}
		return Integer.valueOf(value);
	}
	
	public static Integer getInteger(String key, Integer defaultValue) {
		String value = config.get(key);
		if(value==null){
			return defaultValue;
		}
		return Integer.valueOf(value);
	}
	
	public static long getLong(String key) {
		String value = config.get(key); 
		return Long.valueOf(value);
	}
	
	public static long getLong(String key, long defaultValue) {
		String value = config.get(key);
		if(value==null){
			return defaultValue;
		}
		return Long.valueOf(value);
	}
	
	public static Long getLong(String key, Long defaultValue) {
		String value = config.get(key);
		if(value==null){
			return defaultValue;
		}
		return Long.valueOf(value);
	}
	 
	public static float getFloat(String key) {
		String value = config.get(key); 
		return Float.valueOf(value);
	}
	
	public static float getFloat(String key, float defaultValue) {
		String value = config.get(key);
		if(value==null){
			return defaultValue;
		}
		return Float.valueOf(value);
	}
	
	public static Float getFloat(String key, Float defaultValue) {
		String value = config.get(key);
		if(value==null){
			return defaultValue;
		}
		return Float.valueOf(value);
	}
	
	public static double getDouble(String key) {
		String value = config.get(key); 
		return Double.valueOf(value);
	}
	
	public static double getDouble(String key, double defaultValue) {
		String value = config.get(key);
		if(value==null){
			return defaultValue;
		}
		return Double.valueOf(value);
	}
	
	public static Double getFloat(String key, Double defaultValue) {
		String value = config.get(key);
		if(value==null){
			return defaultValue;
		}
		return Double.valueOf(value);
	}
	
	public static void main(String[] args){
	String aa=	PropertiesUtil.getString("data_forward_list");
	System.out.println(PropertiesUtil.getInt("aaa",1));
	System.out.println(PropertiesUtil.getInt("port"));
	System.out.println(PropertiesUtil.getDouble("port"));
	System.out.println(PropertiesUtil.getFloat("port"));
	
	}
}
