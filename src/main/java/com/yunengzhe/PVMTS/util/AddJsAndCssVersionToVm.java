package com.yunengzhe.PVMTS.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
 
/**
 * 
 * @Version:1.0.0
 */
public class AddJsAndCssVersionToVm {
    /**
     * 默认jsp 文件路径
     */
   /* private static String path = "E:/gitcode/YNZ/src/main/webapp/jsp/PVDIY";
    *//**
     * 默认js 文件路径
     *//*
    private static String jspath = "E:/gitcode/YNZ/src/main/webapp/assets/js/PVmap";*/
	
	 /**
	  * task runJsVM(type:JavaExec){
	classpath = sourceSets.main.runtimeClasspath
	main = "com.yunengzhe.PVMTS.util.AddJsAndCssVersionToVm"
}
	  * str[0] 地址
	  * str[1.2.3...]不包含改变版本的名称（包括路径名称）
	  * @param str
	  */
    public static void main(String[] str) {
//    	for (int i = 0; i < str.length; i++) {
//    		if(str[i]!=null&&str[i]!=""){
//        		str[i]=str[i];
//        	}else{
//        		str[i]="gfdsgesthsyrygffjyhfdhg";
//        	}
//		}
    	Properties properties = System.getProperties();
    	String property = properties.getProperty("java.class.path");
    	String string = property.split(";")[0];
    	int indexOf = string.indexOf("bin");
    	String substring = string.substring(0, indexOf-1);
    	System.out.println(substring);
    	String[] str1 = new String[6];
    	str1[0]=substring+"//src//main//webapp//page";
    	str1[1]="datatable";
    	str1[2]="bootstrap";
    	str1[3]="baidu";
    	str1[4]="min";
    	str1[5]="jquery";
        AddJsAndCssVersionToVm.execute(str1);
    }
    
    
    /**
     * 初始化
     * @param str 
     * @Version:
     */
    public static void execute(String[] str) {
    	
    	if(str.length<=0){
    		System.out.printf("please input file folder!! eg:java -jar appname.jar E:/test/webapp filter");
    		System.exit(1);
    	}
    	File file = new File(str[0]);
    	String property = file.getAbsolutePath();
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        String version = df.format(date);
        String replaceAll = property.replaceAll("\\\\", "/");
        Map<String, String> map = getJsMap(replaceAll,str);
        addVersionToVm(replaceAll, version,map,str);
    }
    /**
     * 获取js 与css 版本map
     * @Version: 
     * @param path
     * @return
     */
    public static Map<String, String> getJsMap(String path,String[] str) {
        Map<String, String> map = new HashMap<String, String>();
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null)
            return map;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getVersionMap(files[i].getAbsolutePath(), map,str);
            } else {
                String strFileName = files[i].getAbsolutePath().toLowerCase();
                files[i].getName();
            	// 如果是符合条件的文件，则添加版本信息
            	if (strFileName.endsWith(".js")) {
        			try {
        				String md5 = getFileMD5String(files[i]);
        				map.put(files[i].getName(), md5);
        				System.out.println(strFileName + "---------------"
        						+ md5 + "-----" + files[i].getName());
        				System.out.println(files[i].getPath());
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
            	}
            }
        }
        return map;
    }
 
    /**
     * 遍历web目录中的vm文件，给js和css的引用加上版本号
     * @param path 路径
     * @param version 默 认版本
     * @param map 版本map
     */
    private static void addVersionToVm(String path,String version,Map<String,String> map,String[] str){
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null){
            return;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                addVersionToVm(files[i].getAbsolutePath(), version,map,str);
            } else {
                String strFileName = files[i].getAbsolutePath().toLowerCase();
                // 如果是符合条件的文件，则添加版本信息
                if ( strFileName.endsWith(".html")
                        || strFileName.endsWith(".jsp")) {
                    // RandomAccessFile raf = null;
                    InputStream is = null;
                    OutputStream os = null;
                    List<String> contentList = new ArrayList<String>();
 
                    // 读文件
                    try {
                        is = new FileInputStream(files[i]);
                        Reader r = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(r);
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            String modLine = getModLine(line, version,map,str);
                            if (modLine != null) {
                                line = modLine;
                            }
                            line = line + "\r\n";
                            contentList.add(line);
                        }
                        // 关闭流
                        br.close();
                        r.close();
                    } catch (Exception e) {
                        System.out.println("读文件失败");
                        e.printStackTrace();
                    } finally {
                        if (null != is) {
                            try {
                                is.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
 
                    }
 
                    // 写文件
                    try {
                        os = new FileOutputStream(files[i]);
                        Writer w = new OutputStreamWriter(os);
                        BufferedWriter bw = new BufferedWriter(w);
                        for (Iterator<String> it = contentList.iterator(); it
                                .hasNext();) {
                            String line = it.next();
                            bw.write(line);
                        }
                        // 更新到文件
                        bw.flush();
                        // 关闭流
                        bw.close();
                        w.close();
                    } catch (Exception e) {
                        System.out.println("写文件失败");
                        e.printStackTrace();
                    } finally {
                        if (null != os) {
                            try {
                                os.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * 获取js 与css map key 文件名   value md5值
     * @Version: 
     * @param path 路径
     * @param map map
     */
    public static void getVersionMap(String path, Map<String, String> map,String[] str) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null){
            return;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getVersionMap(files[i].getAbsolutePath(), map,str);
            } else {
                String strFileName = files[i].getAbsolutePath().toLowerCase();
    				// 如果是符合条件的文件，则添加版本信息
					if (strFileName.endsWith(".js") || strFileName.endsWith(".css")) {
						try {
							String md5 = getFileMD5String(files[i]);
							map.put(strFileName, md5);
						} catch (IOException e) {
							e.printStackTrace();
						}
        		}	
            }
        }
 
    }
 
    /**
     * 
     * 查找文件需要的版本号的js 与css 行
     * @param line 行字符
     * @param version 默认版本
     * @param map md5 map
     * @return 
     */
    private static String getModLine(String line, String version, Map<String, String> map,String[] str) {
        // 增加js版本
        line = line.trim();
        if (line.startsWith("<script") && line.endsWith("</script>")) {
        	String modLine = null;
        	if(line.contains(".js")){
        		for (int i = 1; i < str.length; i++) {
        			if(line.contains(str[i])){
            			 return line;
            		}
				}
        			int pos = line.indexOf(".js");
        			// 默认加时间为版本号
        			modLine = line.substring(0, pos) + ".js?version=" + version+ "\" type='text/javascript'></script>";
        			String[] nameStr = line.split("/");
        			for(int i=0;i<nameStr.length;i++){
        				String jsname = nameStr[i];
        				if(jsname.indexOf(".js")!=-1){
        					int jsindex = jsname.indexOf(".js") +3;
        					String  key = jsname.substring(0, jsindex);
        					String md5=map.get(key);
        					if(md5!=null){
        						modLine = line.substring(0, pos) + ".js?version=" + md5 + "\" type='text/javascript'></script>";
        					}
        					System.out.println("--------------"+modLine);
        				}
        			}
        		}
        	return modLine;
        } else if (line.startsWith("<link")
                && (line.endsWith(".css\">")||line.contains(".css"))) {
        	int pos = line.indexOf(".css");
        	String modLine = line.substring(0, pos) + ".css?version=" + version+ "\" rel=\"stylesheet\" type=\"text/css\"  />";
        	String[] nameStr = line.split("/");
        	for (int i = 1; i < str.length; i++) {
    			if(line.contains(str[i])){
        			 return line;
        		}
			}
	            for(int i=0;i<nameStr.length;i++){
	                String jsname = nameStr[i];
	                if(jsname.indexOf(".css")!=-1){
	                    int jsindex = jsname.indexOf(".js") +4;
	                    String  key = jsname.substring(0, jsindex);
	                    String md5=map.get(key);
	                    if(md5!=null){
	                        modLine = line.substring(0, pos) + ".css?version=" + md5+ "\" rel='stylesheet' type='text/css'  />";
	                    }
	                    System.out.println("--------------"+modLine);
	                }
	            }
            return modLine;
        } else {
            return null;
        }
    }

    
    
    
    
    
    
    
protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',  
        '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
protected static MessageDigest messagedigest = null;  
static {  
    try {  
        messagedigest = MessageDigest.getInstance("MD5");  
    } catch (NoSuchAlgorithmException e) {  
    }  
}  

public static String getFileMD5String(File file) throws IOException {  
    FileInputStream in = new FileInputStream(file);  
    FileChannel ch = in.getChannel();  
    MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,  
            file.length());  
    messagedigest.update(byteBuffer);  
    return bufferToHex(messagedigest.digest());  
}  

public static String getMD5String(String s) {  
    return getMD5String(s.getBytes());  
}  

public static String getMD5String(byte[] bytes) {  
    messagedigest.update(bytes);  
    return bufferToHex(messagedigest.digest());  
}  

private static String bufferToHex(byte bytes[]) {  
    return bufferToHex(bytes, 0, bytes.length);  
}  

private static String bufferToHex(byte bytes[], int m, int n) {  
    StringBuffer stringbuffer = new StringBuffer(2 * n);  
    int k = m + n;  
    for (int l = m; l < k; l++) {  
        appendHexPair(bytes[l], stringbuffer);  
    }  
    return stringbuffer.toString();  
}  

private static void appendHexPair(byte bt, StringBuffer stringbuffer) {  
    char c0 = hexDigits[(bt & 0xf0) >> 4];  
    char c1 = hexDigits[bt & 0xf];  
    stringbuffer.append(c0);  
    stringbuffer.append(c1);  
}  

public static boolean checkPassword(String password, String md5PwdStr) {  
    String s = getMD5String(password);  
    return s.equals(md5PwdStr);  
}  
}