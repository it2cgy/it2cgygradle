package com.yunengzhe.PVMTS.company.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import net.sf.json.JSONObject;

import com.yunengzhe.PVMTS.company.entity.CheckedResult;
import com.yunengzhe.PVMTS.company.entity.LocalConfig;
import com.yunengzhe.PVMTS.company.entity.MTCompany;
import com.yunengzhe.PVMTS.company.entity.MTPackagerecord;

public interface IOldCompanyService {

	JSONObject addCompany(MTCompany company); 
	
	@SuppressWarnings("rawtypes")
	List queryCompany(Map map);
	
	@SuppressWarnings("rawtypes")
	List pachageAdd(Map map);
	
	JSONObject changestatus(MTPackagerecord Packagerecord);
	
	JSONObject addRecode(MTPackagerecord Packagerecord); 
	boolean updateCompany(MTCompany company);
	
	/**
	 * 创建公司时检查公司是否存在
	 * @param map
	 * @return
	 */
	JSONObject checkCompanyAccount(Map map);
	
	/**
	 * 修改管理员密码
	 * @param map
	 * @return
	 */
	JSONObject editPsw(Map map);

	List updatestatus();

	MTCompany getwilllostcount(String company_id);
	/**
	 * 获取运维公司用户列表（重写分页）
	 * 描述：		
	 * @param map
	 * @return
	 */
	JSONObject queryCompanyList(String name,String page,String rows,Integer id);
	/**
	 * 获取指定用户的套餐列表
	 * 描述：		
	 * @param map
	 * @param page 
	 * @param rows 
	 * @return
	 */
	JSONObject pachageList(Map map, int page,int rows);
	/**
	 * 获取用户的套餐版本（3用户、5用户）
	 * 描述：		
	 * @param Packagerecord
	 * @return
	 */
	public int getUserPackageType(String company_id);
	/**
	 * 获取versiontype
	 * 描述：		
	 * @param packagerecord
	 * @return
	 */
	public List getPackageVersionType(String company_id);
	
	/**
	 * 网络化校验
	 * 描述：		
	 * @param localConfig
	 * @return
	 */
	public LocalConfig checkedOnline(LocalConfig localConfig);
	/**
	 * 获取本地化网络校验结果
	 * 描述：		
	 * @param checkedResult
	 * @return
	 */
	public CheckedResult getCheckedInfo(CheckedResult checkedResult);
	/**
	 * 描述：	获取本地化配置数据列表	
	 * @param localConfig
	 * @return
	 */
	public LocalConfig getLocalList(LocalConfig localConfig);
	/**
	 * 描述：添加本地化配置		
	 * @param localConfig
	 * @return 
	 */
	LocalConfig insertLocal(LocalConfig localConfig);
	
}
