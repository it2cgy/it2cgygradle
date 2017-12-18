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

import com.yunengzhe.commons.util.PropertiesUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class AlarmCreateWord {

	private static Configuration configuration = null;  
    private static Map<String, Template> allTemplates = null;  
    
	private static String windowsfilepath = "D://doc//";
    private static String linuxfilepath = "/home/doc/";
    static {  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
        configuration.setClassForTemplateLoading(LogCreateWord.class, "/ftl");
        allTemplates = new HashMap<String, Template>();   // Java 7 钻石语法  
        try {  
            allTemplates.put("longieb", configuration.getTemplate("alarm.ftl"));  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        }
    }  
  
   /* private CreateWord() {  
        throw new AssertionError();  
    }  
  */
	public static File createDoc(Map<?, ?> dataMap, String Reportname, String type,String downpath) throws IOException { 
    	//判断系统是windows还是linux分别存储不同的路径
		String os = System.getProperty("os.name");;
		String pathname = "";
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formar = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String url = "";
		String format = formar.format(date);
		if(downpath==null){
			if (os.startsWith("Windows")) {
				String path = new PropertiesUtil().getString("windowsreportfilepath");
				url = Reportname+"//"+format+"//"+Reportname+ ".doc";
				pathname = path+url;
			} else if (os.startsWith("Linux")) {
				String path =new PropertiesUtil().getString("linuxreportfilepath");
				url = Reportname+"//"+format+"//"+Reportname+ ".doc";
				pathname = path+url;
			}
		}else{
			if (os.startsWith("Windows")) {
				String path = downpath;
				url = Reportname+"//"+format+"//"+Reportname+ ".doc";
				pathname = path+url;
			} else if (os.startsWith("Linux")) {
				String path =downpath;
				url = Reportname+"//"+format+"//"+Reportname+ ".doc";
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
