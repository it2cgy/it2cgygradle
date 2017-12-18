package com.yunengzhe.PVMTS.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.util.PathUtil;
import com.yunengzhe.PVMTS.util.OfficeView.OfficeToSwf;
import com.yunengzhe.commons.util.PropertiesUtil;

@Controller
@RequestMapping("/officeView")
public class OfficeViewController {
	/**
	 * 制度查看转flash
	 * @param request
	 * @return
	 */
	/*
	 * 获取swf文件的路径
	 */
	@RequestMapping(params="Method=path_filename")
	public JSONObject pathFilename(HttpServletRequest request){
		
		
		String path = request.getParameter("path");
		String filename = request.getParameter("filename");
		System.out.println("Method=path_filename>>>>>  " + path + "/" +  filename);
		//拼接swf文件的存储的路径
		String swfSavePath = PathUtil.swfSavePath(request, filename);
		String sourceFile  = PropertiesUtil.getString("upload_path") + "/" + path + "/" + filename;//PathUtil.sourceFilePath(request, path, filename);
		//从Office到Swf文件
		OfficeToSwf officeToSwf = new OfficeToSwf(sourceFile, swfSavePath);
		String swfPath = officeToSwf.conver();
		System.out.println(swfPath);
		
		//输出结果
		JSONObject object = new JSONObject();
		if(swfPath != null){
			object.put("result", true);
			object.put("path", PathUtil.getClientReqPath(request, swfPath));
		}else {
			object.put("result", false);
		}
		return object;
	}
	
	@RequestMapping(params="Method=id_tablename")
	public String idTablename(HttpServletRequest request, Integer id, String table){
		
		return null;
	}
	
	@RequestMapping(params="Method=path_file")
	public String pathFile(HttpServletRequest request, String path_file){
		//OfficeToPDF.office2PDF(sourceFile, destFile);
		/*String servletPath = PathUtil.servletPath(request);
		String swfSavePath = servletPath.substring(0, servletPath.lastIndexOf("\\")) + "\\" + "pvmts_swf_file";*/
		return null;
	}
	
}
