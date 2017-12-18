//package com.yunengzhe.PVMTS.util.properties;
//
//import java.util.Properties;
//
//public class Params {
//	
//	public static int environment;
//	public static String command;
//	public static String host;
//	public static int port;
//	public static String Lpdf2swfPath;
//	public static String uploadPath;
//	public static String uploadTempPath;
//	public static String scadaApiURL;
//
//	public static String localConfig;
//	static{
//		//Properties prop = new Properties();
//		//Properties prop = PropertiesUtil.loadProperties("mail.properties");
//		try {
//			Properties prop = new Properties();
//			prop.load(Params.class.getClassLoader().getResourceAsStream("params.properties"));
//			
//			environment =  Integer.valueOf(prop.getProperty("environment").trim()); 
//			command = prop.getProperty("command").trim();
//			host = prop.getProperty("host").trim();
//			port = Integer.valueOf(prop.getProperty("port").trim());
//			Lpdf2swfPath = prop.getProperty("Lpdf2swfPath").trim();
//			uploadPath = prop.getProperty("uploadPath").trim();
//			uploadTempPath = prop.getProperty("uploadTempPath").trim();
//			scadaApiURL = prop.getProperty("scadaApiURL").trim();
//			localConfig = prop.getProperty("localConfig").trim();
//			
//			System.out.println(environment);
//			System.out.println(command);
//			System.out.println(host);
//			System.out.println(Lpdf2swfPath);
//			System.out.println(port);
//			System.out.println(uploadPath);
//			System.out.println(uploadTempPath);
//			System.out.println(scadaApiURL);
//			System.out.println(localConfig);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
