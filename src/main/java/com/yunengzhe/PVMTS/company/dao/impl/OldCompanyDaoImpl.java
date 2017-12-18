//package com.yunengzhe.PVMTS.company.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.json.JSONObject;
//
//import org.mybatis.spring.support.SqlSessionDaoSupport;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.yunengzhe.PVMTS.company.dao.IOldCompanyDao;
//import com.yunengzhe.PVMTS.company.entity.CheckedResult;
//import com.yunengzhe.PVMTS.company.entity.LocalConfig;
//import com.yunengzhe.PVMTS.company.entity.MTCompany;
//import com.yunengzhe.PVMTS.company.entity.MTPackagerecord;
//import com.yunengzhe.PVMTS.userrole.dao.UserMgrDao;
//import com.yunengzhe.PVMTS.util.MD5.MD5Util;
//
//@Repository("oldCompanyDaoImpl")
//public class OldCompanyDaoImpl extends SqlSessionDaoSupport implements IOldCompanyDao {
//	
//	@Autowired
//	private UserMgrDao userMgrDaoImpl;
//	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public JSONObject addCompany(MTCompany company) {
//		int id =  getSqlSession().selectOne("mtCompany.getId");
//		company.setId(id);
//		int flag = getSqlSession().insert("mtCompany.addCompany", company);
//		
//		HashMap map = new HashMap();
//		int userid = userMgrDaoImpl.querySeqMtUserId();
//		map.put("id", userid);
//		map.put("company_id", id);
//		map.put("role_id", 1);
//		map.put("name", company.getUser_name());
//		map.put("password", MD5Util.MD5(company.getPassword()));
//		map.put("cellphone", company.getCellphone());
//		map.put("realname", company.getUser_realname());
//		map.put("company", company.getName());
//		int flag1 = getSqlSession().insert("userMgr.creatUser", map);
//		System.out.println("flag1"  + flag1);
//		
//		JSONObject object = new JSONObject();
//		if(flag > 0){
//			object.put("result", true);
//		}else{
//			object.put("result", false);
//		}
//		return object;
//	}
//	
//	public JSONObject addRecode(MTPackagerecord Packagerecord){
//		JSONObject obj = new JSONObject();
//		 getSqlSession().insert("mtPackagerecord.insertPackageRecord", Packagerecord);
//		return obj;
//	}
//
//	@SuppressWarnings("rawtypes")
//	public List queryCompany(Map map) {
//		List list = getSqlSession().selectList("mtCompany.queryCompany", map);
//		return list;
//	}
//	@SuppressWarnings("rawtypes")
//	public JSONObject changestatus(MTPackagerecord Packagerecord){
//		JSONObject obj = new JSONObject();
//		List list = getSqlSession().selectList("mtPackagerecord.changestatus", Packagerecord);
//		return obj;
//	}
//	
//	@SuppressWarnings("rawtypes")
//	public List pachageAdd(Map map){
//		List list = getSqlSession().selectList("mtPackagerecord.getPackageRecord", map);
//		return list;
//	}
//	
//	@SuppressWarnings("rawtypes")
//	public List getVersionType(Map map) {
//		List list = getSqlSession().selectList("mtPackagerecord.getVersionType", map);
//		return list;
//	}
//	
//	public JSONObject updatestatus(String endtime){
//		JSONObject object  = new JSONObject();
//		Map map = new HashMap();
//		map.put("endTime", endtime);
//		getSqlSession().selectList("mtPackagerecord.updateDiestatus", map);
//		getSqlSession().selectList("mtPackagerecord.updateLivestatus", map);
//		return object;
//	}
//	
//	public List selectlostcount(Map map){
//		return getSqlSession().selectList("mtPackagerecord.selectlostcount", map);
//	}
//	
//	public List selectlostCompanyid(Map map){
//		return getSqlSession().selectList("mtPackagerecord.selectlostCompanyid", map);
//	}
//	public List selectLiveUserCount(Map map){
//		return getSqlSession().selectList("mtPackagerecord.selectLiveUserCount", map);
//	}
//	
////	public List selectlostCompanyid(String endtime){
////		return getSqlSession().selectList("mtPackagerecord.updatestatus", endtime);
////	}
//	
//	public void updateUserType(Map map){
//		getSqlSession().selectList("mtPackagerecord.updateUserType", map);
//	}
////	public List selectUserType(Map map){
////		return getSqlSession().selectList("mtPackagerecord.updateUserType", map);
////	}
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public boolean updateCompany(MTCompany company) {
//		int flag = getSqlSession().update("mtCompany.updateCompany", company);
//		boolean state = false;
//		if(flag > 0){
//			state = true;
//		}
//		HashMap map = new HashMap();
//		map.put("id", company.getUser_id());
//		map.put("role_id", 1);
//		//map.put("password", MD5Util.MD5(company.getPassword()));
//		map.put("cellphone", company.getCellphone());
//		map.put("realname", company.getUser_realname());
//		map.put("company", company.getName());
//		
//		int flagUser = getSqlSession().update("userMgr.editUser", map);
//		System.out.println("flagUser " +  flagUser + "    " + flag);
//		
//		return state;
//	}
//
//	public JSONObject checkCompanyAccount(Map map) {
//		int flag = (int) getSqlSession().selectOne("mtCompany.checkCompanyAccount", map);
//		JSONObject object = new JSONObject();
//		if(flag > 0){
//			object.put("result", false);
//		}else{
//			object.put("result", true);
//		}
//		return object;
//	}
//
//	public JSONObject editPsw(Map map) {
//		int flag = getSqlSession().update("mtCompany.editPsw", map);
//		JSONObject jsonObject = new JSONObject();
//		if(flag > 0){
//			jsonObject.put("result", true);
//		}else{
//			jsonObject.put("result", false);
//		}
//		return jsonObject;
//	}
//	/**
//	 * 实现接口方法
//	 * 描述：获取在正常使用的账户个数
//	 * @param mp
//	 * @return
//	 */
//	public Integer getUsingCount(Map mp){
//		mp.put("company_id", mp.get("id"));
//		List countList = getSqlSession().selectList("mtPackagerecord.getUsingCount", mp);
//		int count = Integer.parseInt(countList.get(0)+"");
//		// TODO sql.xml
//		return count;
//	}
//	/**
//	 * 获取现有套餐
//	 * 实现接口方法
//	 * 描述：		
//	 * @param packagerecord
//	 * @return
//	 */
//	public List CheckedRecode(MTPackagerecord packagerecord){
//		return getSqlSession().selectList("mtPackagerecord.CheckedRecode", packagerecord);
//	}
//	/**
//	 * 根据公司名称和id获取公司信息
//	 * 实现接口方法
//	 * 描述：		
//	 * @param name
//	 * @param company_id
//	 * @return
//	 */
//	public List<MTCompany> getCompany(String name,String company_id){
//		Map map = new HashMap();
//		map.put("name", name);
//		map.put("company_id", company_id);
//		return getSqlSession().selectList("mtCompany.getCompany", map);
//	}
//	/**
//	 * 获取指定用户当前的套餐类型
//	 * 实现接口方法
//	 * 描述：		
//	 * @param company_id
//	 * @return
//	 */
//	public List<MTPackagerecord> getUserPackageType(String company_id){
//		Map map = new HashMap();
//		map.put("company_id", company_id);
//		return getSqlSession().selectList("mtPackagerecord.getUserPackageType", map);
//	}
//	/**
//	 * 获取指定用户的套餐列表
//	 * 实现接口方法
//	 * 描述：		
//	 * @param company_id
//	 * @return
//	 */
//	public List<MTPackagerecord> getPackage(String company_id,int start,int end){
//		Map map = new HashMap();
//		map.put("company_id", company_id);
//		map.put("start", start+"");
//		map.put("end", end+"");
//		return getSqlSession().selectList("mtPackagerecord.getPackage", map);
//	}
//	/**
//	 * 获取套餐数量
//	 * 实现接口方法
//	 * 描述：		
//	 * @param company_id
//	 * @param start
//	 * @param end
//	 * @return
//	 */
//	public List<MTPackagerecord> getPackageCount(String company_id){
//		Map map = new HashMap();
//		map.put("company_id", company_id);
//		return getSqlSession().selectList("mtPackagerecord.getPackageCount", map);
//	}
//	/**
//	 * 获取versiontype
//	 * 描述：		
//	 * @param packagerecord
//	 * @return
//	 */
//	public List getPackageVersionType(MTPackagerecord packagerecord){
//		return getSqlSession().selectList("mtPackagerecord.getPackageVersionType", packagerecord);
//	}
//	
//	public List<LocalConfig> selectLocalInfo(LocalConfig LocalConfig){
//		return getSqlSession().selectList("mtPackagerecord.selectLocalInfo", LocalConfig);
//	}
//	
//	/**
//	 * 存储网络校验结果
//	 * 描述：		
//	 * @param result
//	 */
//	public void insertResultInfo(CheckedResult result){
//		getSqlSession().selectList("mtPackagerecord.insertResultInfo", result);
//	}
//	
//	public List<CheckedResult> getCheckedInfo(CheckedResult checkedResult){
//		List<CheckedResult> list = getSqlSession().selectList("mtPackagerecord.getCheckedInfo", checkedResult);
//		return list;
//	}
//	public List getCheckedCount(CheckedResult checkedResult){
//		List list = getSqlSession().selectList("mtPackagerecord.getCheckedCount", checkedResult);
//		return list;
//	}
//	public List<LocalConfig> getLocalList(LocalConfig localConfig){
//		List<LocalConfig> list = getSqlSession().selectList("mtPackagerecord.getLocalList", localConfig);
//		return list;
//	}
//	public List getLocalListCount(LocalConfig localConfig){
//		List list = getSqlSession().selectList("mtPackagerecord.getLocalListCount", localConfig);
//		return list;
//	}
//	public void stopLocalConfig(LocalConfig localConfig){
//		getSqlSession().selectList("mtPackagerecord.stopLocalConfig", localConfig);
//	}
//	public void insertLocal(LocalConfig localConfig){
//		getSqlSession().selectList("mtPackagerecord.insertLocal", localConfig);
//	}
//	public List checkNumber(LocalConfig localConfig){
//		List list = getSqlSession().selectList("mtPackagerecord.checkNumber", localConfig);
//		return list;
//	}
//}
