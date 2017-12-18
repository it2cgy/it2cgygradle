package com.yunengzhe.PVMTS.util;

import java.util.Calendar;


/**
 * <p>Title:字符串处理工具类</p>
 * @author CHENGUIYANG
 * <p>Description:此类为公共处理类   待完善</p>
 * @date 2016-7-25 下午03:16:30
 */
public class StringUtil {
	
	public StringUtil(){}
	
	/**
	 * 得到字符串中某个字符的个数
	 * @param str 字符串
	 * @param c 字符
	 * @return
	 */
	public static final int getCharnum(String str ,char c){
		int num = 0;
		char[] chars = str.toCharArray();
		for(int i = 0;i<chars.length;i++){
			if(c == chars[i]){
				num++;
			}
		}
		return num;
	}
	
	/**
	 * 返回当前年
	 * @return
	 */
	public static String getYear(){
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(1));
	}
	
	/**
	 * 返回当前月
	 * @return
	 */
	public static String getMonth(){
		Calendar calendar = Calendar.getInstance();
		String temp = String.valueOf(calendar.get(2)+1);
		if(temp.length()<2)
			 temp =  "0"+temp;
		return temp;
	}
	
	/**
	 * 验证字符串是否为空
	 * @param param
	 * @return
	 */
	public static boolean empty(String param){
		return param == null || param.trim().length()<1;
	}
	
	/**
	 * 验证字符串中是否都为数字
	 * @param param
	 * @return
	 */
	public static boolean isNum(String param){
//		return param.matches("[0-9]{1,}");
		return param.matches("^(-?\\d+)(\\.\\d+)?$");
	}
	
	/**
	 * 转化当前传入参数为字符串
	 * @param param
	 * @return
	 */
	public static String toString(Object object){
		return object.toString();
	}
	
	/**
	 * 转化当前传入参数为整数类型
	 * @param param
	 * @return
	 */
	public static int toInt(String param){
		return Integer.parseInt(param);
	}
	/**
	 * 转化当前传入参数为double类型
	 * @param param
	 * @return
	 */
	public static double toDouble(String param){
		return Double.parseDouble(param);
	}
	
	
	public static void main(String[] agrs){
		System.out.println("1.22"+isNum("1.22"));
		System.out.println("isNum"+isNum("1.2a"));
		System.out.println("1"+isNum("1"));
		System.out.println("-1.22"+isNum("-1.22"));
		System.out.println("-1"+isNum("-1"));
		System.out.println(" "+isNum(" "));
		System.out.println(""+isNum(""));
	}
}
