package com.yunengzhe.PVMTS.util.MD5;

import java.security.MessageDigest;

public class MD5Util {
	
	private static String DEFAUlT_CHARSET = "UTF-8";
	
	public static String MD5(String str){
		return MD5(str, DEFAUlT_CHARSET);
	}
	//加密
	/*public String MD51(String s, String charSet) {  
		//6512bd43d9caa6e02c990b0a82652dca  11
		//6512BD43D9CAA6E02C990B0A82652DCA
//        char hexDigits[] = { '0', '1', '2', '3', '4',  
//                             '5', '6', '7', '8', '9',  
//                             'A', 'B', 'C', 'D', 'E', 'F' };
        try {  
            //byte[] btInput = s.getBytes(charSet);
            //获得MD5摘要算法的 MessageDigest 对象  
	        MessageDigest mdInst = MessageDigest.getInstance("MD5");  
	        //使用指定的字节更新摘要  
	        mdInst.update(s.getBytes(charSet));  
	        //获得密文  
	        byte[] md = mdInst.digest();  
	        //把密文转换成十六进制的字符串形式  
	        int i;
	        StringBuffer buf = new StringBuffer();
	        for(int offset=0; offset<md.length; offset++ ){
	        	i = md[offset];
	        	if(i < 0)
	        		i += 256;
	        	if(i < 16)
	        		buf.append("0");
	        	buf.append(Integer.toHexString(i));
	        }
//            int j = md.length;  
//            char str[] = new char[j * 2];  
//            int k = 0;  
//            for (int i = 0; i < j; i++) {  
//                byte byte0 = md[i];  
//                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
//                str[k++] = hexDigits[byte0 & 0xf];  
//            } 
	        
            return buf.toString();//new String(str);  
	    }  
        catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}  */
	 
	public final static String MD5(String s, String charSet) {  
        char hexDigits[] = { '0', '1', '2', '3', '4',  
                			 '5', '6', '7', '8', '9',  
                			 'A', 'B', 'C', 'D', 'E', 'F' };  
        try {  
            byte[] btInput = s.getBytes(charSet);
            //获得MD5摘要算法的 MessageDigest 对象  
	        MessageDigest mdInst = MessageDigest.getInstance("MD5");  
	        //使用指定的字节更新摘要  
	        mdInst.update(btInput);  
	        //获得密文  
	        byte[] md = mdInst.digest();
	        //把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(str);  
	    }  
        catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
	 
    public static void main(String[] args) {  
        //System.out.print(MD5Util.MD5("yunengzhe"));  
    	System.out.println();
    }
}
