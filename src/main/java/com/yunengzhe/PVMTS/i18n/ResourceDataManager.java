package com.yunengzhe.PVMTS.i18n;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

import ch.qos.logback.core.net.server.Client;

public class ResourceDataManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceDataManager.class);
	private static Map<String,Map<String,String>> localMap = null;
	/**
	 * 初始化
	 * @throws Exception 
	 */
	public static void initDataUS(){
		localMap = new HashMap<String,Map<String,String>>();
		//开始日志
		logger.info("init i18n resource US--------------->");
		//英文
		Properties prop = new Properties();
		try {
			prop.load(new InputStreamReader(Client.class.getClassLoader().getResourceAsStream("messages/messages_en_US.properties"),"UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		} 
		
		Enumeration<Object> enums = prop.keys();
		Map<String,String> enData =new HashMap<String,String>();
		while(enums.hasMoreElements()){
			String name =  (String) enums.nextElement(); 
			String value = prop.getProperty(name, "");
			if(enData.containsKey(name)){
				//资源冲突，抛异常
				try {
					logger.error("资源冲突，抛异常");
					throw new Exception();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			enData.put(name, value);
		}
		localMap.put("en_US", enData);
		logger.info("end i18n resource US--------------->");
		//结束日志
		
		//开始日志
				logger.info("init i18n resource CN--------------->");
				//中文 
				Properties propzh_cn = new Properties();
				try {
					propzh_cn.load(new InputStreamReader(Client.class.getClassLoader().getResourceAsStream("messages/messages_zh_CN.properties"),"UTF-8"));
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(e.getMessage(),e);
				}   
				Enumeration<Object> enums_zh = propzh_cn.keys(); 
				Map<String,String> enDatazh = new HashMap<String,String>();
				while(enums_zh.hasMoreElements()){
					String namezh = (String) enums_zh.nextElement(); 
					String value = propzh_cn.getProperty(namezh, "");
					if(enDatazh.containsKey(namezh)){
							try {
								logger.error("资源冲突，抛异常");
								throw new Exception();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						//资源冲突，抛异常
					}

					enDatazh.put(namezh, value);
				}
				localMap.put("zh_CN", enDatazh);
				logger.info("end i18n resource CN--------------->");
				//结束日志
	}
	/**
	 * 
	 * @param local
	 * @return
	 */
	public static Map<String,String> getLocalMap(String local){ 
		if(localMap==null){
			initDataUS();
		}
		 if(StringUtils.isBlank(local)){
			 local = "zh_CN";
		 }
		 
		 Map<String,String> data = localMap.get(local);
		 if(data==null || data.size()<=0){
			 local = "zh_CN";
			 data = localMap.get(local);
		 }
		return data; 
	}

}
