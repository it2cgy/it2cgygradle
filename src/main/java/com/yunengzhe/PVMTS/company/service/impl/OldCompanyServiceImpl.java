//package com.yunengzhe.PVMTS.company.service.impl;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.yunengzhe.PVMTS.company.dao.IOldCompanyDao;
//import com.yunengzhe.PVMTS.userrole.dao.UserMgrDao;
//import com.yunengzhe.PVMTS.userrole.entity.UserBean;
//import com.yunengzhe.PVMTS.util.Json.ReadJson;
//import com.yunengzhe.PVMTS.company.entity.CheckedResult;
//import com.yunengzhe.PVMTS.company.entity.LocalConfig;
//import com.yunengzhe.PVMTS.company.entity.MTCompany;
//import com.yunengzhe.PVMTS.company.entity.MTPackagerecord;
//import com.yunengzhe.PVMTS.company.service.IOldCompanyService;
//
//@Service("companyServiceImpl")
//public class OldCompanyServiceImpl implements IOldCompanyService{
//
//	@Autowired
//	private IOldCompanyDao oldCompanyDaoImpl;
//	
//	@Autowired UserMgrDao userMgrDaoImpl; 
//
//	public JSONObject addCompany(MTCompany company) {
//		JSONObject obj = new JSONObject();
//		oldCompanyDaoImpl.addCompany(company);
//	return obj;
//		
//	}
//	@SuppressWarnings("rawtypes")
//	public JSONObject changestatus(MTPackagerecord Packagerecord){
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date now = new Date();
//		String endTime = df.format(now);
////		String endTime = "2017-04-01";
//		Map strmap = new HashMap();
//		Packagerecord.setEndTime(endTime);
//		oldCompanyDaoImpl.changestatus(Packagerecord);
//		strmap.put("endTime", endTime);
////		iceUser(Map strmap);
//		strmap.put("company_id", Packagerecord.getCompany_id());
//		iceUser(strmap);
//		return null;
//	}
//	@SuppressWarnings("rawtypes")
//	public List updatestatus(){
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date now = new Date();
//		String endTime = df.format(now);
////		String endTime = "2017-04-01";
//		Map strmap = new HashMap();
//		strmap.put("endTime", endTime);
//		List list = oldCompanyDaoImpl.selectlostCompanyid(strmap);//求过期套餐
//		oldCompanyDaoImpl.updatestatus(endTime);//改变过期套餐的状态 生效操作 过期操作
//		for(int j=0;j<list.size();j++){
//			MTPackagerecord Packagerecord =(MTPackagerecord) list.get(j);
//			strmap.put("company_id", Packagerecord.getCompany_id());
//			System.out.println("company_id = "+Packagerecord.getCompany_id());
//			iceUser(strmap);
//		}
//		return list;
//	}
//	public void iceUser(Map strmap){
//		List livelist = oldCompanyDaoImpl.selectlostcount(strmap);
//		int count =0;
//		for(int i =0;i<livelist.size();i++){
//			MTPackagerecord Pcord =(MTPackagerecord) livelist.get(i);
//			count = Pcord.getAccountCount()+count;
//		}
//		System.out.println("company_id 公司可用子账户个数： "+count);
//		List num = oldCompanyDaoImpl.selectLiveUserCount(strmap);
//		int number = num.size();
//		System.out.println(num);
//		if(number>count){
//			int todie = number - count;
//			List todielist = new ArrayList();
//			String todiestr ="('"+num.get(0)+"'";
//			for(int g=1;g<todie;g++){
//				todiestr = todiestr+",'"+num.get(g)+"'";
//			}
//			todiestr = todiestr+")";
//			strmap.put("id", todiestr);
//			oldCompanyDaoImpl.updateUserType(strmap);
//		}
//	}
//	public JSONObject addRecode(MTPackagerecord Packagerecord){
//		if(Packagerecord.getAccount_type().equals("年费用户")){
//			Packagerecord.setAccount_type("0");
//		}
//		if(Packagerecord.getAccount_type().equals("永久用户")){
//			Packagerecord.setAccount_type("1");
//		}
//		if(Packagerecord.getAccount_type().equals("试用用户")){
//			Packagerecord.setAccount_type("2");
//		}
//		if(Packagerecord.getType().equals("A版(轻便版)")){
//			Packagerecord.setType("0");
//		}
//		if(Packagerecord.getType().equals("B版(完整版)")){
//			Packagerecord.setType("1");
//		}
//		Date now = new Date();
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//		String time = format.format(now);
//		String nowDate = format1.format(now);
//		Packagerecord.setCreateTime(time);
//		if(nowDate.equals(Packagerecord.getStartTime())){
//			Packagerecord.setPackageStatus("1");
//		}else{
//			Packagerecord.setPackageStatus("0");
//		}
//		Boolean flag = CheckedRecode(Packagerecord);
//		if(flag){
//			return oldCompanyDaoImpl.addRecode(Packagerecord);
//		}else{
//			JSONObject obj = new JSONObject();
//			obj.put("unpass", -1);
//			return obj;
//		}
//	}
//	public Boolean CheckedRecode(MTPackagerecord Packagerecord){
//		List list = oldCompanyDaoImpl.CheckedRecode(Packagerecord);
//		String status =Packagerecord.getType();
//		Boolean flag = true;
//		for(int i =0;i<list.size();i++){
//			String sta = list.get(i)+"";
//			if(sta.equals(status)){
//				
//			}else{
//				flag = false;
//			}
//			
//		}
//		return flag;
//	}
//	@SuppressWarnings("rawtypes")
//	public List queryCompany(Map map) {
//		if(map.get("name")!=null){
//			String aaa = map.get("name")+"";
//			String bbb;
//			try {
//				bbb = URLDecoder.decode(aaa, "utf-8");
//				map.put("name", bbb);
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		List MTCompanylist = oldCompanyDaoImpl.queryCompany(map);
//		List list ;
//		List OBJList = new ArrayList();
//		for(int i =0;i<MTCompanylist.size();i++){
//			MTCompany emp = (MTCompany) MTCompanylist.get(i);
//			String id = emp.getId()+"";
//			Map mp = new HashMap();
//			System.out.println(mp);
//			mp.put("id",id);
//			list = oldCompanyDaoImpl.getVersionType(mp);
//			int countnum = 0;
//			int willlostcount = 0;
//			int num = OBJList.size();
//			if(list.size()!=0){
//				JSONObject obj = new JSONObject();
//				obj.put("id", emp.getId());
//				obj.put("cellphone", emp.getCellphone());
//				obj.put("create_time", emp.getCreate_time());
//				obj.put("name", emp.getName());
//				obj.put("remarks", emp.getRemarks());
//				obj.put("user_id",emp.getUser_id());                   
//				obj.put("user_name",emp.getUser_name());					
//				obj.put("user_realname",emp.getUser_realname());				
//				obj.put("password",emp.getPassword());					
//				obj.put("address",emp.getAddress());					
//				obj.put("contract_code",emp.getContract_code());//合同编号
//				obj.put("owner_firm_contact_name",emp.getOwner_firm_contact_name()); 	
//				obj.put("owner_firm_contact_position",emp.getOwner_firm_contact_position());	
//				obj.put("owner_firm_contact_mobile",emp.getOwner_firm_contact_mobile());	
//				obj.put("last_opreate_time",emp.getLast_opreate_time());		 	
//				for(int j =0;j<list.size();j++){
//					MTPackagerecord mtp = (MTPackagerecord)list.get(j);
//					Date now =new Date();
//					DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
//					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//					String str1 = df.format(now);
//					Calendar c = Calendar.getInstance();
//					c.add(Calendar.DATE, 30);
//					String str2 = df.format(c.getTime());
//					if(Integer.parseInt(mtp.getPackageStatus())==1){
//						countnum = countnum + mtp.getAccountCount();
//						try {
//							Date end = format.parse(mtp.getEndTime());
//							Date compare = format.parse(str2);
//							if(end.before(compare)){
//								willlostcount = willlostcount +mtp.getAccountCount();
//							}
//								
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//				if(Integer.parseInt(((MTPackagerecord)list.get(0)).getAccount_type())==0){
//					obj.put("account_type","年费用户");
//				}else if(Integer.parseInt(((MTPackagerecord)list.get(0)).getAccount_type())==1){
//					obj.put("account_type","永久用户");
//				}else if(Integer.parseInt(((MTPackagerecord)list.get(0)).getAccount_type())==2){
//					obj.put("account_type","试用用户");
//				}
//				if(Integer.parseInt(((MTPackagerecord)list.get(0)).getType())==0){
//					obj.put("versionType","A版(轻便版)");
//				}
//				if(Integer.parseInt(((MTPackagerecord)list.get(0)).getType())==1){
//					obj.put("versionType","B版(完整版)");
//				}
//				obj.put("accountCount",countnum);
//				if(willlostcount==0){
//					obj.put("comment","无");
//				}else{
//					obj.put("comment",willlostcount+"个账户即将到期");
//				}
//				OBJList.add(obj);
//			}else{
//				JSONObject obj = new JSONObject();
//				obj.put("id", emp.getId());
//				obj.put("accountCount", "0");
//				obj.put("comment", "无");
//				obj.put("cellphone", emp.getCellphone());
//				obj.put("create_time", emp.getCreate_time());
//				obj.put("name", emp.getName());
//				obj.put("remarks", emp.getRemarks());
//				obj.put("user_id",emp.getUser_id());                   
//				obj.put("user_name",emp.getUser_name());					
//				obj.put("user_realname",emp.getUser_realname());				
//				obj.put("password",emp.getPassword());					
//				obj.put("address",emp.getAddress());					
//				obj.put("contract_code",emp.getContract_code());				//合同编号
//				obj.put("owner_firm_contact_name",emp.getOwner_firm_contact_name()); 	
//				obj.put("owner_firm_contact_position",emp.getOwner_firm_contact_position());	
//				obj.put("owner_firm_contact_mobile",emp.getOwner_firm_contact_mobile());	
//				obj.put("last_opreate_time",emp.getLast_opreate_time());
//				obj.put("account_type","未添加套餐");
//				obj.put("versionType","未添加");
//				OBJList.add(obj);
//			}
//		}
//		return OBJList;
//	}
//	@SuppressWarnings("rawtypes")
//	public List pachageAdd(Map map) {
//		List list = new ArrayList();
//		if(map.get("name")!=null){
//			String aaa = map.get("name")+"";
//			String bbb;
//			try {
//				bbb = URLDecoder.decode(aaa, "utf-8");
//				map.put("name", bbb);
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		List MTCompanylist = oldCompanyDaoImpl.queryCompany(map);
//		List PachageAddlist ;
//		for(int i =0;i<MTCompanylist.size();i++){
//			MTCompany emp = (MTCompany) MTCompanylist.get(i);
//			String id = emp.getId()+"";
//			Map mp = new HashMap();
//			System.out.println(mp);
//			mp.put("id",id);
//			PachageAddlist = oldCompanyDaoImpl.pachageAdd(mp);
//			if(PachageAddlist.size()!=0){
//				for(int j =0;j<PachageAddlist.size();j++){
//					JSONObject obj = new JSONObject();
//					MTPackagerecord mtp = (MTPackagerecord)PachageAddlist.get(j);
//					if(Integer.parseInt(mtp.getType())==0){
//						mtp.setType("A版(轻便版)");
//					}else if(Integer.parseInt(mtp.getType())==1){
//						mtp.setType("B版(完整版)");
//					}
//					if(Integer.parseInt(mtp.getAccount_type())==0){
//						mtp.setAccount_type("年费用户");
//					}else if(Integer.parseInt(mtp.getAccount_type())==1){
//						mtp.setAccount_type("永久用户");
//					}else if(Integer.parseInt(mtp.getAccount_type())==2){
//						mtp.setAccount_type("试用用户");
//					}
//					if(Integer.parseInt(mtp.getPackageStatus())==0){
//						mtp.setPackageStatusV(0);
//						mtp.setPackageStatus("未开始");
//					}else if(Integer.parseInt(mtp.getPackageStatus())==1){
//						mtp.setPackageStatusV(1);
//						mtp.setPackageStatus("运行中");
//					}else if(Integer.parseInt(mtp.getPackageStatus())==2){
//						mtp.setPackageStatusV(2);
//						mtp.setPackageStatus("已过期");
//					}else if(Integer.parseInt(mtp.getPackageStatus())==3){
//						mtp.setPackageStatusV(3);
//						mtp.setPackageStatus("强制停止");
//					}else if(Integer.parseInt(mtp.getPackageStatus())==4){
//						mtp.setPackageStatusV(4);
//						mtp.setPackageStatus("已取消");
//					}else{
//						mtp.setPackageStatus("未知");
//						mtp.setPackageStatusV(-1);
//					}
//					obj.put("company", emp.getName());
//					if(mtp.getAccount_type()==null){
//						obj.put("account_type", "无");
//					}else{
//						obj.put("account_type", mtp.getAccount_type());
//					}
//					obj.put("id", mtp.getId());
//					obj.put("company_id", mtp.getCompany_id());
//					obj.put("packageStatus",mtp.getPackageStatus());
//					obj.put("packageStatusV", mtp.getPackageStatusV());
//					obj.put("createTime", mtp.getCreateTime());
//					obj.put("updateTime", mtp.getUpdateTime());
//					if(mtp.getStartTime()==null){
//						obj.put("startTime", "无");
//					}else{
//						obj.put("startTime", mtp.getStartTime());
//					}
//					obj.put("endTime", mtp.getEndTime());
//					obj.put("type", mtp.getType());
//					obj.put("interval",mtp.getInterval());
//					obj.put("accountCount",mtp.getAccountCount());
//					if(mtp.getStoptime()==null){
//						obj.put("stoptime", "无");
//					}else{
//						obj.put("stoptime", mtp.getStoptime());
//					}
//					if(mtp.getNumber()==null){
//						obj.put("number", "无");
//					}else{
//						obj.put("number", mtp.getNumber());
//					}
//					list.add(obj);
//				}
//			}else{
//				JSONObject obj = new JSONObject();
//				obj.put("company", emp.getName());
//				obj.put("account_type", "无");
//				obj.put("id","无");
//				obj.put("company_id", "无");
//				obj.put("packageStatus", "无");
//				obj.put("packageStatusV", -1);
//				obj.put("createTime", "无");
//				obj.put("updateTime", "无");
//				obj.put("startTime", "无");
//				obj.put("endTime", "无");
//				obj.put("type", "未添加");
//				obj.put("interval", "无");
//				obj.put("accountCount", "无");
//				obj.put("stoptime", "无");
//				obj.put("number", "无");
//				list.add(obj);
//			}
//		}
//		return list;
//	}
//
//	public boolean updateCompany(MTCompany company) {
//		return oldCompanyDaoImpl.updateCompany(company);
//	}
//
//	public JSONObject checkCompanyAccount(Map map) {
//		return oldCompanyDaoImpl.checkCompanyAccount(map);
//	}
//	public JSONObject editPsw(Map map) {
//		return oldCompanyDaoImpl.editPsw(map);
//	}
//	/**
//	 * 获取账户，可用账户总数、已占用账户个数、将要失效的账户数量
//	 * 实现接口方法
//	 * 描述：		
//	 * @param Company_id
//	 * @return
//	 */
//	public MTCompany getwilllostcount(String Company_id){
//		Map mp = new HashMap();
//		mp.put("id",Company_id);
//		Date now =new Date();
//		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String str1 = df.format(now);
//		Calendar c = Calendar.getInstance();
//		c.add(Calendar.DATE, 30);
//		String str2 = df.format(c.getTime());
//		int countnum =0;
//		int willlostcount =0;
//		MTCompany mt = new MTCompany();
//		LocalConfig configBean = ReadJson.getMpg();
//		if(Integer.valueOf(configBean.getProducttype())==0){//进入本地化
//			mt.setTotalCount(configBean.getAccountCount());
//			String end = configBean.getEndTime();
//			long sttime;
//			long edtime;
//			try {
//				sttime = format.parse(str2).getTime();
//				edtime = format.parse(end).getTime();
//				if(sttime>edtime){
//					mt.setLostCount(configBean.getAccountCount());
//				}else{
//					mt.setLostCount(0);
//				}
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			mt.setUsingCount(oldCompanyDaoImpl.getUsingCount(mp));	
//			return mt;
//		}else{
//			List list = oldCompanyDaoImpl.getVersionType(mp);	 
//			if(list.size()==0){
//				mt.setTotalCount(-1);
//				return mt;
//			}else{
//				for(int j =0;j<list.size();j++){
//					MTPackagerecord mtp = (MTPackagerecord)list.get(j);
//					if(Integer.parseInt(mtp.getPackageStatus())==1){
//						countnum = countnum + mtp.getAccountCount();
//						try {
//							Date end = format.parse(mtp.getEndTime());
//							Date compare = format.parse(str2);
//							if(end.before(compare)){
//								willlostcount = willlostcount +mtp.getAccountCount();
//							}
//							
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//				
////			MTCompany mt = new MTCompany();
//				mt.setTotalCount(countnum);
//				if(willlostcount==0){
////			obj.put("comment","无");
//					mt.setLostCount(-1);
//				}else{
//					mt.setLostCount(willlostcount);
//				}
//				mt.setUsingCount(oldCompanyDaoImpl.getUsingCount(mp));	
//				return mt;
//			}
//		}
//	}
//	/**
//	 * 获取运维公司用户列表
//	 * 实现接口方法
//	 * 描述：		
//	 * @param map
//	 * @return
//	 */
//	public JSONObject queryCompanyList(String name,String page,String rows,Integer id){
//		String company_id ="";
//		List<MTCompany> companyList = new ArrayList<MTCompany>();
//		List<MTCompany> cpList = new ArrayList<MTCompany>();
//		List<MTCompany> resList = new ArrayList<MTCompany>();
//		if(StringUtils.isNotBlank(name)){
//			try {
//				name = URLDecoder.decode(name, "utf-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			companyList = oldCompanyDaoImpl.getCompany(name,"");
////			company_id = companyList.get(0).getId()+"";
//		}
//		int end;
//		int start;
//		if(StringUtils.isNotBlank(page)){
//			end = Integer.parseInt(page)*Integer.parseInt(rows);
//			start = end-Integer.parseInt(rows);
//			end = end+1;
//		}else{
//			end = -1;
//			start = -1;
//		}
//		if(id!=null){
////			company_id = String.valueOf(id);
//			MTCompany mtcompany = new MTCompany();
//			mtcompany.setId(id);
//			companyList.add(mtcompany);
//		}
//		List<List<UserBean>> totalList = new ArrayList<List<UserBean>>();
//		int totalcount = 0;
//		if(companyList.size()!=0){
//			for(int i=0;i<companyList.size();i++){
//				company_id = companyList.get(i).getId()+"";
//				List<UserBean> Userlist = userMgrDaoImpl.CompanyManager(company_id,start,end);
//				List countList = userMgrDaoImpl.ManagerCount(company_id,start,end);
//				//TODO
//				totalcount = totalcount+Integer.parseInt(countList.get(0)+"");
//				totalList.add(Userlist);
//			}
//		}else{
//			List<UserBean> Userlist = userMgrDaoImpl.CompanyManager(company_id,start,end);
//			totalList.add(Userlist);
//			List countList = userMgrDaoImpl.ManagerCount(company_id,start,end);
//			totalcount = totalcount+Integer.parseInt(countList.get(0)+"");
//		}
//		JSONObject object = new JSONObject();
//		object.put("total", totalcount);
////		List<UserBean> pagelist = new ArrayList<UserBean>();
//		for(int k =0;k<totalList.size();k++){
//			List<UserBean> list = totalList.get(k);
//			for(int j=0;j<list.size();j++){
//				company_id = list.get(j).getCompany_id()+"";
////			UserBean user = 
//				cpList = oldCompanyDaoImpl.getCompany("",company_id);
//				MTCompany mtcy = getwilllostcount(company_id);
//				MTCompany mtCompany = new MTCompany();
//				List<MTPackagerecord> mp = oldCompanyDaoImpl.getUserPackageType(company_id);
//				
//				mtCompany.setName(cpList.get(0).getName());
//				mtCompany.setCreate_time(cpList.get(0).getCreate_time());
//				mtCompany.setTotalCount(mtcy.getTotalCount());
//				mtCompany.setLostCount(mtcy.getLostCount());
//				if(mp.size()!=0){
//					mtCompany.setVersionType(mp.get(0).getType());
//					mtCompany.setAccountType(Integer.parseInt(mp.get(0).getAccount_type()));
//				}else{
//					mtCompany.setVersionType("-1");
//					mtCompany.setAccountType(-1);
//				}
//				mtCompany.setRemarks(cpList.get(0).getRemarks());
//				mtCompany.setId(cpList.get(0).getId());
//				mtCompany.setUser_name(list.get(j).getName());
//				mtCompany.setUser_realname(list.get(j).getRealname());
//				mtCompany.setCellphone(list.get(j).getCellphone());
//				mtCompany.setContract_code(cpList.get(0).getContract_code());
//				mtCompany.setOwner_firm_contact_mobile(cpList.get(0).getOwner_firm_contact_mobile());
//				mtCompany.setAddress(cpList.get(0).getAddress());
//				mtCompany.setOwner_firm_contact_name(cpList.get(0).getOwner_firm_contact_name());
//				mtCompany.setOwner_firm_contact_position(cpList.get(0).getOwner_firm_contact_position());
//				
//				resList.add(mtCompany);
//			}
//		}
//		object.put("rows", resList);
//		return object;
//	}
//	/**
//	 * 获取指定用户的套餐列表
//	 * 实现接口方法
//	 * 描述：		
//	 * @param map
//	 * @return
//	 */
//	public JSONObject pachageList(Map map,int page,int rows){
//		String company_id = map.get("id")+"";
//		JSONObject object = new JSONObject();
//		List<MTCompany> cpList = oldCompanyDaoImpl.getCompany("",company_id);
//		int end = page*rows;
//		int start = end-rows;
//		List countList = oldCompanyDaoImpl.getPackageCount(company_id);
//		List<MTPackagerecord> pkList = oldCompanyDaoImpl.getPackage(company_id,start,end+1);
//		for(int i=0;i<pkList.size();i++){
//			pkList.get(i).setCompany_name(cpList.get(0).getName());
//		}
//		object.put("total", countList.get(0));
//		object.put("rows", pkList);
//		return object;
//	}
//	/**
//	 * 获取用户的套餐版本（3用户、5用户）
//	 * 描述：		
//	 * @param Packagerecord
//	 * @return
//	 */
//	public int getUserPackageType(String company_id){
//		MTPackagerecord Packagerecord = new MTPackagerecord();
//		Packagerecord.setCompany_id(Integer.parseInt(company_id));
//		List list = oldCompanyDaoImpl.CheckedRecode(Packagerecord);
//		int type;
//		if(list.size()==0){
//			type = 0;
//		}
//		type = Integer.parseInt(list.get(0)+"");
//		return type;
//	}
//	/**
//	 * 获取versiontype
//	 * 描述：		
//	 * @param packagerecord
//	 * @return
//	 */
//	public List getPackageVersionType(String company_id){
//		MTPackagerecord Packagerecord = new MTPackagerecord();
//		Packagerecord.setCompany_id(Integer.parseInt(company_id));
//		List list = oldCompanyDaoImpl.getPackageVersionType(Packagerecord);
//		return list; 
//	}
//	
//	/**
//	 * 网络化校验
//	 * 描述：		
//	 * @param localConfig
//	 * @return
//	 */
//	public LocalConfig checkedOnline(@RequestBody LocalConfig LocalConfig){
//		int systype = 0;
//		List<LocalConfig> mtplocal = oldCompanyDaoImpl.selectLocalInfo(LocalConfig);
//		CheckedResult result = new CheckedResult();
//		result.setConfigId(mtplocal.get(0).getId());
//		Date now = new Date();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String startTime = format.format(now);
//		result.setStartTime(startTime);
//		String remarks = "";
//		int i = 0;
//		if(LocalConfig.getIp().equals(mtplocal.get(0).getIp())){
//			i++;
//			remarks = remarks+"IP验证PASS，";
//		}else{
//			i--;
//			remarks = remarks+"IP验证ERROR，";
//		}
//		if(LocalConfig.getMac().equals(mtplocal.get(0).getMac())){
//			i++;
//			remarks = remarks+"MAC验证PASS，";
//		}else{
//			i--;
//			remarks = remarks+"MAC验证ERROR，";
//		}
//		if(LocalConfig.getAccountCount().equals(mtplocal.get(0).getAccountCount())){
//			i++;
//			remarks = remarks+"子用户数量验证PASS，";
//		}else{
//			i--;
//			remarks = remarks+"子用户数量验证ERROR，";
//		}
//		String str1 = LocalConfig.getEndTime().split(" ")[0];
//		String str2 = mtplocal.get(0).getEndTime().split(" ")[0];
//		if(str1.equals(str2)){
//			i++;
//			remarks = remarks+"套餐结束时间验证PASS。";
//		}else{
//			i--;
//			remarks = remarks+"套餐结束时间验证ERROR。";
//		}
//		result.setRemarks(remarks);
//		if(i==4){
//			//所有验证通过，type=0；表示验证通过。
//			result.setType(0);
//			systype = 0;
//		}else{
//			//表示验证不通过，一个或几个验证没有被通过，详情请查看remarks备注。
//			result.setType(1);
//			systype = 1;
//		}
//		oldCompanyDaoImpl.insertResultInfo(result);
//		LocalConfig.setSystype(systype);
//		return LocalConfig;
//	}
//	public CheckedResult getCheckedInfo(CheckedResult checkedResult){
//		CheckedResult CheckedResult = new CheckedResult();
//		List list = oldCompanyDaoImpl.getCheckedCount(checkedResult);
//		if(list.size()!=0){
//			CheckedResult.setTotal(Integer.valueOf(list.get(0)+""));
//		}else{
//			CheckedResult.setTotal(0);
//		}
//		CheckedResult.setList(oldCompanyDaoImpl.getCheckedInfo(checkedResult));
//		return CheckedResult;
//	}
//	public LocalConfig getLocalList(LocalConfig localConfig){
//		if(localConfig.getNumber()!=""&&localConfig.getNumber()!=null){
//			oldCompanyDaoImpl.stopLocalConfig(localConfig);
//		}
//		LocalConfig LocalConfig = new LocalConfig();
//		List list = oldCompanyDaoImpl.getLocalListCount(localConfig);
//		LocalConfig.setTotal(list.get(0)+"");
//		LocalConfig.setList(oldCompanyDaoImpl.getLocalList(localConfig));
//		return LocalConfig;
//	}
//	public LocalConfig insertLocal(LocalConfig localConfig){
//		List list = oldCompanyDaoImpl.checkNumber(localConfig);
//		if(list.size()==0){
//			oldCompanyDaoImpl.insertLocal(localConfig);
//			localConfig.setFlag(true);
//		}else{
//			localConfig.setFlag(false);
//		}
//		return localConfig;
//	}
//}
