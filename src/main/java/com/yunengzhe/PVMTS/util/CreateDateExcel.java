package com.yunengzhe.PVMTS.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jcraft.jsch.Logger;
import com.yunengzhe.commons.util.PropertiesUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 导出excel Utils
 * @author Administrator
 *
 */
public class CreateDateExcel {
	private static Configuration configuration = null;  
    private static Map<String, Template> allTemplates = null;  
	private static String alarmDownloadpath = PropertiesUtil.getString("alarmDownloadPath");//密码
//	private static String windowsfilepath = "D://doc//";
//    private static String linuxfilepath = "/home/doc/";
    static {  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
        configuration.setClassForTemplateLoading(CreateDataWord.class, "/ftl");
        allTemplates = new HashMap<String, Template>();   // Java 7 钻石语法  
        try {  
            allTemplates.put("longieb", configuration.getTemplate("data.ftl"));  
            allTemplates.put("alarmMail", configuration.getTemplate("mail.ftl"));  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        }
    }  
    /**
     * 创建报警excel附件
     * @param dataMap
     * @param type
     * @return
     */
    public static File createAlarmExcel(Map<?, ?> dataMap, String Reportname, String type) throws IOException {
    	//判断系统是windows还是linux分别存储不同的路径
		String os = System.getProperty("os.name");;
		String pathname = "";
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formar = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒SSS");
		String url = "";
		String format = formar.format(date);
		if(alarmDownloadpath!=null){
				String path = alarmDownloadpath;
//				File fileDel = new File(path+"reports");
//				fileDel.delete();
//				url = "reports"+"//"+format+"//"+Reportname+ ".xls";
				url = "//"+Reportname+ ".xls";
				pathname = path+url;
		}
        File f = new File(pathname);
        if(!f.getParentFile().exists()){
        	f.getParentFile().mkdirs();
        }
        Template t = allTemplates.get(type);  
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
        	FileOutputStream fos = new FileOutputStream(f);
        	OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
        	Writer bw = new BufferedWriter(osw);
            t.process(dataMap, bw);
            fos.close();
            bw.close();
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        } 
        return f;  
    }  
    
    public static File createExcel(Map<?, ?> dataMap, String Reportname, String type,String downpath) throws IOException { 
    	//判断系统是windows还是linux分别存储不同的路径
		String os = System.getProperty("os.name");;
		String pathname = "";
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formar = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒SSS");
		String url = "";
		String format = formar.format(date);
		if(downpath==null){
			if (os.startsWith("Windows")) {
				String path = new PropertiesUtil().getString("windowsreportfilepath");
				url = "reports"+"//"+format+"//"+Reportname+ ".xls";
				pathname = path+url;
			} else if (os.startsWith("Linux")) {
				String path =new PropertiesUtil().getString("linuxreportfilepath");
				url = "reports"+"//"+format+"//"+Reportname+ ".xls";
				pathname = path+url;
			}
		}else{
			if (os.startsWith("Windows")) {
				String path = downpath;
				url = "reports"+"//"+format+"//"+Reportname+ ".xls";
				pathname = path+url;
			} else if (os.startsWith("Linux")) {
				String path =downpath;
				url = "reports"+"//"+format+"//"+Reportname+ ".xls";
				pathname = path+url;
			}
		}
        
        File f = new File(pathname);
        if(!f.getParentFile().exists()){
        	f.getParentFile().mkdirs();
        }
        Template t = allTemplates.get(type);  
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
        	FileOutputStream fos = new FileOutputStream(f);
        	OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
        	Writer bw = new BufferedWriter(osw);
            t.process(dataMap, bw);
            fos.close();
            bw.close();
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        } 
        return f;  
    }  
}