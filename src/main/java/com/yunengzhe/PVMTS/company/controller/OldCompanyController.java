//package com.yunengzhe.PVMTS.company.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.ws.rs.QueryParam;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.yunengzhe.PVMTS.company.entity.CheckedResult;
//import com.yunengzhe.PVMTS.company.entity.LocalConfig;
//import com.yunengzhe.PVMTS.company.entity.MTCompany;
//import com.yunengzhe.PVMTS.company.entity.MTPackagerecord;
//import com.yunengzhe.PVMTS.company.service.IOldCompanyService;
//import com.yunengzhe.PVMTS.userrole.entity.UserBean;
//import com.yunengzhe.PVMTS.util.Json.ReadJson;
//import com.yunengzhe.PVMTS.util.MD5.MD5Util;
//import com.yunengzhe.PVMTS.util.MybatisEasyUIHandler.EasyPage;
//import com.yunengzhe.PVMTS.util.MybatisEasyUIHandler.PageHelper;
///**
// * 运维公司控制层操作
// * @author zhanzhengguang
// *
// */
//@Controller
//@RequestMapping("/mtCompany")
//public class OldCompanyController {
//	
//	Logger logger = LoggerFactory.getLogger(OldCompanyController.class);
//	@Autowired
//	private IOldCompanyService companyServiceImpl;
//	
//	@RequestMapping(params="Method=addCompany")
//	public JSONObject addCompany(MTCompany company){
//		System.out.println("addCompany >>>>>>>  " + company);
//		JSONObject object = companyServiceImpl.addCompany(company);
//		return object;
//	}
//	@RequestMapping(params="Method=getRoleId")
//	public JSONObject getRoleId(HttpSession session){
//		UserBean user = (UserBean) session.getAttribute("user");
//		JSONObject object = new JSONObject();
//		object.put("user", user);
//		return object;
//	}
//	@RequestMapping(params="Method=changestatus")
//	public JSONObject changestatus(MTPackagerecord Packagerecord){
//		System.out.println("addCompany >>>>>>>  " + Packagerecord);
//		JSONObject object = companyServiceImpl.changestatus(Packagerecord);
//		return object;
//	}
//	@RequestMapping(params="Method=addRecode")
//	public JSONObject addRecode(MTPackagerecord Packagerecord){
//		System.out.println("addRecord >>>>>>>  " + Packagerecord);	
//		JSONObject object = companyServiceImpl.addRecode(Packagerecord);
////		companyServiceImpl.updatestatus("2017-04-01");
//		return object;
//	}
//	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(params="Method=pachageAdd", method = RequestMethod.GET)
//	public JSONObject pachageAdd(String name, Integer id){
//		Map map = new HashMap();
//		map.put("id", id);
//		map.put("name", name);
//		
//		List list = companyServiceImpl.pachageAdd(map);
//		System.out.println(list.toString());
//		JSONObject object = new JSONObject();
//		object.put("rows", list);
//		object.put("total", list.size());
//		return object;
//	}
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(params="Method=pachageList", method = RequestMethod.GET)
//	public JSONObject pachageList(String name, Integer id,String page,String rows){
//		Map map = new HashMap();
//		map.put("id", id);
//		map.put("name", name);
//		JSONObject object = companyServiceImpl.pachageList(map,Integer.parseInt(page),Integer.parseInt(rows));
//		return object;
//	}
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(params="Method=queryCompanyList", method = RequestMethod.GET)
//	public JSONObject queryCompanyList(HttpSession session,String page,String rows,String name, String contract_code, String uesr_name, Integer id){
//		JSONObject object = companyServiceImpl.queryCompanyList(name,page,rows,id);
//		logger.debug(object.toString());
//		return object;
//	}
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(params="Method=queryCompany", method = RequestMethod.GET)
//	public JSONObject queryCompany(HttpSession session,String page,String rows,String name, String contract_code, String uesr_name, Integer id){
//				String page_v = "0";
//		String rows_v = "10";
//		if(StringUtils.isNotBlank(page) && StringUtils.isNumeric(page)){
//			page_v = page;
//		}
//		
//		if(StringUtils.isNotBlank(rows) && StringUtils.isNumeric(rows)){
//			rows_v = rows;
//		}
//		
//		EasyPage easyPage = new EasyPage();
//		easyPage.setPage(page_v);
//		easyPage.setRows(rows_v);
//		PageHelper.startPage(easyPage);
////		
//		Map map = new HashMap();
//		map.put("id", id);
//		map.put("name", name);
//		map.put("contract_code", contract_code);
//		map.put("uesr_name", uesr_name);
//		map.put("page_v", page_v);
//		map.put("rows_v", rows_v);
//		List list = companyServiceImpl.queryCompany(map);
//		System.out.println(list.toString());
//		JSONObject object = new JSONObject();
//		object.put("rows", list);
//		object.put("total", list.size());
//		return object;
//	}
//	@RequestMapping(params="Method=updateCompany")
//	public JSONObject updateCompany(MTCompany company){
//		System.out.println("updateCompany >>>>>>>  " + company);
//		boolean bool = companyServiceImpl.updateCompany(company);
//		JSONObject object = new JSONObject();
//		if(bool){
//			object.put("result", true);
//		}else{
//			object.put("result", false);
//		}
//		return object;
//	}
//	
//	/**
//	 * 创建公司时检查公司是否存在
//	 * @param map
//	 * @return
//	 */
//	@ResponseBody
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/checkCompanyAccount" , method = RequestMethod.POST)
//	public JSONObject checkCompanyAccount(@RequestBody Map map){		
//		JSONObject jsonObject = new JSONObject();
//		jsonObject = companyServiceImpl.checkCompanyAccount(map);
//		return jsonObject;
//	}
//	
//	/**
//	 * 编辑管理员密码
//	 * @param map
//	 * @return
//	 */
//	@ResponseBody
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/editPsw" , method = RequestMethod.POST)
//	public JSONObject editPsw(HttpServletRequest request){		
//		Map map = new HashMap();
//		String companyId = request.getParameter("companyId");
//		String password = request.getParameter("password");
//		map.put("companyId", companyId);
//		map.put("password", MD5Util.MD5(password));
//		System.out.println("修改管理员的id和密码 》》》》  " + map );
//		JSONObject jsonObject = new JSONObject();
//		jsonObject = companyServiceImpl.editPsw(map);
//		return jsonObject;
//	}
//	
//	@RequestMapping(params="Method=getUserInfo")
//	public MTCompany getUserInfo(HttpSession session){
//		UserBean userS = (UserBean) session.getAttribute("user");
//	    UserBean user = new UserBean();
//		user.setCompany_id(userS.getCompany_id());
//		user.setId(userS.getId());
//		MTCompany mt = companyServiceImpl.getwilllostcount(userS.getCompany_id()+"");
//		return mt;
//	}
//	@RequestMapping(params="Method=getUserPackageType")
//	public MTCompany getUserPackageType(HttpSession session){
//		UserBean userS = (UserBean) session.getAttribute("user");
//	    UserBean user = new UserBean();
//		user.setCompany_id(userS.getCompany_id());
//		user.setId(userS.getId());
//		MTCompany mt = new MTCompany();
//		int type = companyServiceImpl.getUserPackageType(userS.getCompany_id()+"");
//		mt.setVersionType(type+"");
//		return mt;
//	}
//	
//	@RequestMapping(params="Method=getSystemType")
//	public LocalConfig getSystemType(HttpSession session){
//		LocalConfig LocalConfig = ReadJson.getMpg();
//		return LocalConfig;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="/checkedOnline",method = RequestMethod.POST)
//	public LocalConfig checkedOnline(@RequestBody LocalConfig LocalConfig){
//		return companyServiceImpl.checkedOnline(LocalConfig);
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="/getCheckedInfo",method = RequestMethod.POST)
//	public CheckedResult getCheckedInfo(@RequestBody CheckedResult CheckedResult){
//		CheckedResult checkedResult = companyServiceImpl.getCheckedInfo(CheckedResult);
//		return checkedResult;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="/getLocalList",method = RequestMethod.POST)
//	public LocalConfig getLocalList(@RequestBody LocalConfig LocalConfig){
//		LocalConfig localConfig = companyServiceImpl.getLocalList(LocalConfig);
//		return localConfig;
//	}
//	@ResponseBody
//	@RequestMapping(value="/insertLocal",method = RequestMethod.POST)
//	public LocalConfig insertLocal(@RequestBody LocalConfig LocalConfig){
//		LocalConfig localConfig = new LocalConfig();
//		localConfig = companyServiceImpl.insertLocal(LocalConfig);
//		return localConfig;
//	}
//}
