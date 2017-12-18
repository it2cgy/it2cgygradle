package com.yunengzhe.PVMTS.company.dao;

import java.util.List;
import java.util.Map;

import com.yunengzhe.PVMTS.company.entity.CheckedResult;
import com.yunengzhe.PVMTS.company.entity.LocalConfig;
import com.yunengzhe.PVMTS.company.entity.MTCompany;
import com.yunengzhe.PVMTS.company.entity.MTPackagerecord;

import net.sf.json.JSONObject;

public interface IOldCompanyDao {
	
	JSONObject addCompany(MTCompany company);
	JSONObject addRecode(MTPackagerecord Packagerecord);
	
	@SuppressWarnings("rawtypes")
	List queryCompany(Map map);
	JSONObject changestatus(MTPackagerecord Packagerecord);
	
	List pachageAdd(Map map);
	
	List selectlostcount(Map map);
//	List selectlostCompanyid(String endtime);
	
//	List selectUserType(Map map);
	void updateUserType(Map map);
	
	List getVersionType(Map map);
	JSONObject updatestatus(String endtime);
	
	boolean updateCompany(MTCompany company);
	
	/**
	 * 创建公司时检查公司是否存在
	 * @param map
	 * @return
	 */
	public JSONObject checkCompanyAccount(Map map);

	
	/**
	 * 修改管理员密码
	 * @param map
	 * @return
	 */
	JSONObject editPsw(Map map);
	List selectlostCompanyid(Map strmap);
	List selectLiveUserCount(Map strmap);
	/**
	 * 描述：	获取正在正常使用中的账户数量	
	 * @param mp
	 * @return
	 */
	Integer getUsingCount(Map mp);
	/**
	 * 获取当前套餐种类
	 * 描述：		
	 * @param packagerecord
	 * @return
	 */
	List CheckedRecode(MTPackagerecord packagerecord);
	/**
	 * 通过公司名、公司ID获取公司信息
	 * 描述：		
	 * @param name
	 * @return
	 */
	List<MTCompany> getCompany(String name,String company_id);
	/**
	 * 获取用户所在公司当前的套餐类型
	 * 描述：		
	 * @param company_id
	 * @return
	 */
	List<MTPackagerecord> getUserPackageType(String company_id);
	/**
	 * 获取指定用户的套餐列表
	 * 描述：		
	 * @param company_id
	 * @param end 
	 * @param start 
	 * @return
	 */
	List<MTPackagerecord> getPackage(String company_id, int start, int end);
	/**
	 * 获取套餐数量
	 * 描述：		
	 * @param company_id
	 * @return
	 */
	List getPackageCount(String company_id);
	/**
	 * 获取versiontype
	 * 描述：		
	 * @param packagerecord
	 * @return
	 */
	public List getPackageVersionType(MTPackagerecord packagerecord);
	/**
	 * 获取本地化公司有效配置信息
	 * 描述：		
	 * @param mTPackagerecord
	 * @return
	 */
	public List<LocalConfig> selectLocalInfo(LocalConfig LocalConfig);
	/**
	 * 存储网络校验结果
	 * 描述：		
	 * @param result
	 */
	public void insertResultInfo(CheckedResult result);
	/**
	 * 获取本地化网络校验结果
	 * 描述：		
	 * @param checkedResult
	 * @return
	 */
	List<CheckedResult> getCheckedInfo(CheckedResult checkedResult);
	/**
	 * 描述：	获取网络校验结果个数	
	 * @param checkedResult
	 * @return
	 */
	List getCheckedCount(CheckedResult checkedResult);
	/**
	 * 描述：获取本地化配置数据列表	
	 * @param localConfig
	 * @return
	 */
	List<LocalConfig> getLocalList(LocalConfig localConfig);
	/**
	 * 描述：获取对应条件下配置文件数量		
	 * @param localConfig
	 * @return
	 */
	List getLocalListCount(LocalConfig localConfig);
	/**
	 * 描述：配置文件停用操作		
	 * @param localConfig
	 * @return
	 */
	void stopLocalConfig(LocalConfig localConfig);
	/**
	 * 描述：新增本地化配置		
	 * @param localConfig
	 */
	void insertLocal(LocalConfig localConfig);
	/**
	 * 描述：	判断合同是否已存在	
	 * @param localConfig
	 * @return
	 */
	List checkNumber(LocalConfig localConfig);
	
}
