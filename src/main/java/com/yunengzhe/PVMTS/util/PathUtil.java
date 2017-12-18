package com.yunengzhe.PVMTS.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yunengzhe.PVMTS.domain.vo.UserVO; 
import com.yunengzhe.commons.authentication.HttpSessionUtil;

/**
 * 制度查看调用
 * @author dell
 *
 */
public class PathUtil {
	
	/*
	 * 拼接swf文件路径，保存到tomcat下面
	 */
	public static String swfSavePath(HttpServletRequest request, String filename){
		UserVO user = (UserVO) HttpSessionUtil.getAttribute("user");
		String servletPath = request.getSession().getServletContext().getRealPath("");
		int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  		//0-15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //如果是window系统就转换，Linux不转换，为了是路径都转换成"/"形式
        servletPath = servletPath.replaceAll("\\\\", "/");
		String swfSavePath = servletPath + "/" + "pvmts_swf_file" + "/" + user.getCompanyId() + "/" + dir1 + "/" + dir2;  //如 ：pvmts_swf_file\2\3; pvmts_swf_file\3\5;
		System.out.println("转换 后的   servletPath >>>  " + swfSavePath);
		return swfSavePath;
	}
	
	/*
	 * 拼接images文件路径，保存到tomcat下面
	 */
	public static String imageSavePath(HttpServletRequest request, String filename){
		UserVO user = (UserVO) HttpSessionUtil.getAttribute("user");
		
		String servletPath = request.getSession().getServletContext().getRealPath("");
		int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  		//0-15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //如果是window系统就转换，Linux不转换，为了是路径都转换成"/"形式
        servletPath = servletPath.replaceAll("\\\\", "/");
		String imageSavePath = servletPath + "/" + "pvmts_images_file" + "/" + user.getCompanyId() + "/" + dir1 + "/" + dir2;  //如 ：pvmts_swf_file\2\3; pvmts_swf_file\3\5;
		System.out.println("转换 后的   servletPath_imagesSavePath >>>  " + imageSavePath);
		return imageSavePath;
	}
	
	//获取客服端请求的路径
	public static String getClientReqPath(HttpServletRequest request, String path){
		String servletPath = request.getSession().getServletContext().getRealPath("").replaceAll("\\\\", "/");
		String subPath = path.replaceAll("\\\\", "/").replace(servletPath, "").substring(1);
		//如果是window系统就转换，Linux不转换，为了是路径都转换成"/"形式
		return subPath.replaceAll("\\\\", "/");
	}
	/*public static String contextPath(HttpServletRequest request){
		String servletPath = request.getSession().getServletContext().getRealPath("");
		return servletPath.substring(0, servletPath.lastIndexOf("\\"));
	}
	
	public static String sourceFilePath(HttpServletRequest request, String path,  String filename){
		String servletPath = request.getSession().getServletContext().getRealPath("");
		String contextPath = servletPath.substring(0, servletPath.lastIndexOf("\\"));
		return contextPath + "\\" + path + "\\" + filename;
	}*/
}
